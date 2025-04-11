package f2.tironcinio.whistleblowing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import f2.tironcinio.whistleblowing.daos.CommentoDao;
import f2.tironcinio.whistleblowing.daos.SegnalazioneDao;
import f2.tironcinio.whistleblowing.daos.UtenteDao;
import f2.tironcinio.whistleblowing.entities.Commento;
import f2.tironcinio.whistleblowing.entities.Segnalazione;
import f2.tironcinio.whistleblowing.entities.Utente;
import f2.tironcinio.whistleblowing.responses.BackendResponse;

@RestController
@CrossOrigin
public class CommentoController {

	@Autowired
	CommentoDao cDao;
	@Autowired
	SegnalazioneDao sDao;
	@Autowired
	UtenteDao uDao;

	@GetMapping("/commento")
	public List<Commento> allCommenti() {
		return cDao.findAll();
	}

	@PostMapping("/commento/{id}")
	public ResponseEntity<Object> commento(@RequestBody Commento commento, @PathVariable Integer id) {

		if (commento.getTesto().isEmpty()) {
			return ResponseEntity.status(500).body(new BackendResponse("Campo vuoto"));
		} else {
			commento.setDataCreazione(System.currentTimeMillis());
			Segnalazione s = sDao.findById(id).get();
			s.setDataAggiornamento(System.currentTimeMillis());
			commento.setSegnalazione(s);
			
			if(commento.getUtente() != null) {
				Utente u = uDao.findByEmail(commento.getUtente().getEmail());
				commento.setUtente(u);
			}
			
			cDao.save(commento);

			return ResponseEntity.status(200).body(commento);
		}
	}
}
