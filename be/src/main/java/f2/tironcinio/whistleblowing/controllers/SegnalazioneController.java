package f2.tironcinio.whistleblowing.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import f2.tironcinio.whistleblowing.daos.CommentoDao;
import f2.tironcinio.whistleblowing.daos.SegnalazioneDao;
import f2.tironcinio.whistleblowing.daos.UtenteDao;
import f2.tironcinio.whistleblowing.entities.Segnalazione;
import f2.tironcinio.whistleblowing.entities.Utente;
import f2.tironcinio.whistleblowing.responses.BackendResponse;
import f2.tironcinio.whistleblowing.responses.Priorita;
import f2.tironcinio.whistleblowing.responses.Ruolo;
import f2.tironcinio.whistleblowing.responses.SegnalazionePrecedente;
import f2.tironcinio.whistleblowing.utilities.JwtUtil;
import f2.tironcinio.whistleblowing.utilities.MailSegnazioneUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
@CrossOrigin
public class SegnalazioneController {

	@Autowired
	SegnalazioneDao sDao;
	@Autowired
	UtenteDao uDao;
	@Autowired
	CommentoDao cDao;
	@Autowired
	SpringTemplateEngine engine;

	@GetMapping("/segnalazione")
	public List<Segnalazione> allSegnalazioni() {
		return sDao.findAll();
	}

	@PostMapping("/creaSegnalazione")
	public ResponseEntity<Object> creaSegnalazione(@RequestBody Segnalazione segnalazioni) {

		Long scadenza = (long) 2160;

		String chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		StringBuilder codeBuilder = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int randomIndex = (int) (Math.random() * chars.length());

			codeBuilder.append(chars.charAt(randomIndex));
		}
		String cb = codeBuilder.toString();

		segnalazioni.setDataCreazione(System.currentTimeMillis());
		segnalazioni.setDataAggiornamento(System.currentTimeMillis());
		segnalazioni.setScadenza(System.currentTimeMillis() + (scadenza * 60 * 60 * 1000));
		segnalazioni.setMese(LocalDate.now().getMonth().toString());
		segnalazioni.setStato(false);
		segnalazioni.setCodiceSegnalazione(cb);
		segnalazioni.setPriorita(Priorita.Bassa);
		SegnalazionePrecedente gsp = segnalazioni.getSegnalazionePrecedente();
		segnalazioni.setPersoneInformate(gsp == SegnalazionePrecedente.No ? null : segnalazioni.getPersoneInformate());
		sDao.save(segnalazioni);

		Context ctx = new Context();
		ctx.setVariable("cb", cb);
		String mailBody = engine.process("segnalazioniTemplate", ctx);

		boolean mailSent = MailSegnazioneUtil.sendMail(mailBody);

		if (!mailSent) {
			ResponseEntity.status(500).body(new BackendResponse("Internal server error"));
		} else {
			ResponseEntity.status(200).body(new BackendResponse("La mail è stata inviata"));
		}

		return ResponseEntity.status(200).body(new BackendResponse(cb));

	}

	@GetMapping("/infoSegnalazione/{codiceSegnalazione}")
	public ResponseEntity<Object> infoSegnalazione(@PathVariable String codiceSegnalazione) {

		if (codiceSegnalazione == null)

			return ResponseEntity.status(400).body(new BackendResponse("la segnalazione non è stata trovata"));

		return ResponseEntity.status(200).body(sDao.dettagliSegnalazione(codiceSegnalazione));
	}

	@GetMapping("/listSegnalazioni")
	public ResponseEntity<Object> listaSegnalazioni() {

		// controllare il token

		return ResponseEntity.status(200).body(sDao.findAll());
	}

	@PostMapping("/listSegnalazioniRecipient")
	public ResponseEntity<Object> listaSegnalazioniRecipient(@RequestBody String email) {

		// controllare token

		Utente u = uDao.findByEmail(email);

		if (u != null) {
			return ResponseEntity.status(200).body(sDao.segnalazioniUtente(u.getId()));
		} else {
			return ResponseEntity.status(400).body(new BackendResponse("Utente non trovato"));
		}
	}

	@GetMapping("/segnFiltrate/{stato}")
	public ResponseEntity<Object> segnByStato(@PathVariable Boolean stato) {

		return ResponseEntity.status(200).body(sDao.segnByStato(stato));
	}

	@PostMapping("/segnFiltrateRecipient/{stato}")
	public ResponseEntity<Object> segnRecipientByStato(@PathVariable Boolean stato, @RequestBody String email) {

		// controllare token

		Utente u = uDao.findByEmail(email);

		if (u != null) {
			return ResponseEntity.status(200).body(sDao.segnalazioniUtenteByStato(u.getId(), stato));
		} else {
			return ResponseEntity.status(400).body(new BackendResponse("Utente non trovato"));
		}

	}

	@GetMapping("/segnFiltrate")
	public ResponseEntity<Object> segnDaAssegnare() {

		// controllare il token

		return ResponseEntity.status(200).body(sDao.segnDaAssegnare());
	}

//	@GetMapping("/segnByStato/{stato}")
//	public ResponseEntity<Object> listaSegnalazioniChiuse(@PathVariable Boolean stato){
//		
//		return ResponseEntity.status(200).body(sDao.segnByStato(true));
//	}

	@PostMapping("/chiusuraSegnalazione")
	public ResponseEntity<BackendResponse> chiusuraSegn(@RequestBody String codiceSegnalazione, @RequestHeader("Authorization") String auth) {

		Segnalazione s = sDao.dettagliSegnalazione(codiceSegnalazione);
		String token = auth.substring(7);
		Jws<Claims> claims = JwtUtil.verifyToken(token);
		if (claims == null)
			return ResponseEntity.status(400).body(new BackendResponse("Token non valido"));
		

		if (s != null && claims.getBody().get("ruolo").equals(Ruolo.Recipient.toString()) ) {
			s.setStato(true);
			s.setDataChiusura(System.currentTimeMillis());
			sDao.save(s);
			return ResponseEntity.status(200).body(new BackendResponse("Segnalazione chiusa"));
		} else {
			return ResponseEntity.status(400).body(new BackendResponse("Il codice della segnalazione non è corretto o non sei autorizzato a cambiare lo stato della segnalazione"));
		}

	}

	@PostMapping("/assegnazioneSegnalazione/{codiceSegnalazione}")
	public ResponseEntity<Object> assSegn(@RequestBody Integer id, @PathVariable String codiceSegnalazione,
			@RequestHeader("Authorization") String auth) {

		Utente u = uDao.findById(id).get();
		Segnalazione s = sDao.dettagliSegnalazione(codiceSegnalazione);
		String token = auth.substring(7);
		Jws<Claims> claims = JwtUtil.verifyToken(token);

		if (claims == null)
			return ResponseEntity.status(400).body(new BackendResponse("Token non valido"));

		if (!claims.getBody().get("ruolo").equals(Ruolo.Admin.toString())) {
			return ResponseEntity.status(400).body(new BackendResponse("Non sei l'admin"));
		} else {

			s.setUtente(u);
			sDao.save(s);
			return ResponseEntity.status(200).body(new BackendResponse("Segnalazione assegnata correttamente"));
		}
	}

//	API per settare la priorita di una segnalazione
	@PostMapping("/priorita/{codice}")
	public ResponseEntity<Object> setPriorita(@RequestBody Map<String, Priorita> level, @PathVariable String codice,
			@RequestHeader("Authorization") String token) {

		System.out.println(codice);

//		Prendo il token dagli headers delle request e verifico la sua validità
//		Se non è valido restituisco un client error
		Jws<Claims> claims = JwtUtil.verifyToken(token.substring(7));
		if (claims == null)
			return ResponseEntity.status(400).body(new BackendResponse("Token non valido"));

//		Se il token è valido controllo il ruolo dell'utente
//		Se non è un admin restituisco un client error
		if (!claims.getBody().get("ruolo").equals(Ruolo.Admin.toString())) {
			return ResponseEntity.status(400).body(new BackendResponse("Non sei l'admin"));
		} else {
//			Se è un admin prendo la segnalazione dal db
			Segnalazione s = sDao.findByCodice(codice);

//			Se la segnalazione è presente setto la priorita e la restituisco
//			Altrimenti restituisco un client error per la segnalazione not found
			if (s != null) {
				s.setPriorita(level.get("priorita"));
				sDao.save(s);
				return ResponseEntity.status(200).body(s);
			} else {
				return ResponseEntity.status(400)
						.body(new BackendResponse("Non esiste una segnalazione con questo codice"));
			}
		}
	}
	
}
