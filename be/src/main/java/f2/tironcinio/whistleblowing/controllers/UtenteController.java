package f2.tironcinio.whistleblowing.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import f2.tironcinio.whistleblowing.daos.SegnalazioneDao;
import f2.tironcinio.whistleblowing.daos.UtenteDao;
import f2.tironcinio.whistleblowing.entities.Utente;
import f2.tironcinio.whistleblowing.responses.BackendResponse;
import f2.tironcinio.whistleblowing.responses.ControlDTO;
import f2.tironcinio.whistleblowing.responses.Ruolo;
import f2.tironcinio.whistleblowing.utilities.JwtUtil;
import f2.tironcinio.whistleblowing.utilities.MailUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class UtenteController {
	@Autowired
	UtenteDao uDao;
	@Autowired
	SegnalazioneDao sDao;
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	SpringTemplateEngine engine;
	
	
	@GetMapping("/registrazione")
	public List<Utente> allUtenti() {
		return uDao.findAll();
	}
	
	@PostMapping("/registrazione")
	public ResponseEntity<Object> init(@RequestBody Utente utente, @RequestHeader("Authorization") String auth) {
		String token = auth.substring(7);
		Jws<Claims> claims = JwtUtil.verifyToken(token);
		
		if (claims != null) {
			if ( claims.getBody().get("ruolo").equals(Ruolo.Admin.toString())) {
				if (utente.getEmail() == null) {
					return ResponseEntity.status(400).body(new BackendResponse("Inserire un indirizzo email"));
				}

				Utente byEmail = uDao.findByEmail(utente.getEmail());
				if (byEmail != null) {
					return ResponseEntity.status(400).body(new BackendResponse("Email già esistente"));
				}

				try {
					utente.setPassword(encoder.encode(utente.getPassword()));
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(500).body(new BackendResponse("Password non hashata o non inserita!"));
				}

				try {
					uDao.save(utente);
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(500).body(new BackendResponse("Utente non salvato!"));
				}

				return ResponseEntity.status(200).body(new BackendResponse("Utente registrato correttamente!"));
			} else {
				return ResponseEntity.status(200).body(new BackendResponse("Non sei un admin"));
			}

		} else {
			return ResponseEntity.status(200).body(new BackendResponse("Token non valido"));
		}
	}
		
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Utente utente, HttpSession session){
		
		Utente u = uDao.findByEmail(utente.getEmail());
		
		if (u == null) {
			return ResponseEntity.status(400).body(new BackendResponse("Utente inesistente"));
		}
		
		if (!encoder.matches(utente.getPassword(), u.getPassword())){
			return ResponseEntity.status(400).body(new BackendResponse("Credenziali non valide"));
		}
		
		session.setAttribute(u.getEmail(), true);
		String userToken = JwtUtil.tokenGenerator(u.getNome(), u.getCognome(), u.getEmail(), u.getRuolo());
		
		return ResponseEntity.status(200).body(new BackendResponse(userToken));
	}
	
	@GetMapping("/private")
	public ResponseEntity<Object> privateArea(HttpSession session, @RequestHeader("Authorization") String auth){
		
		String token = auth.substring(7);
		
		Jws<Claims> claims = JwtUtil.verifyToken(token);
		System.out.println(claims);
		
		if(claims == null) {
			return ResponseEntity.status(400).body(new BackendResponse("Token non valido!"));
		}
		
//		Boolean isLoggedIn = (Boolean) session.getAttribute(claims.getBody().get("email").toString());
		
//		if(isLoggedIn == null) {
//			return ResponseEntity.status(400).body(new BackendResponse("User non loggato!"));
//		}
		
		return ResponseEntity.status(200).body(new BackendResponse("User loggato correttamente, hai accesso ai dati privati!"));
	}


	@PostMapping("/control")
	public ResponseEntity<Object> passwordReset(@RequestBody ControlDTO request) {

	    if (request != null && (request.getUsername() != null || request.getEmail() != null)) {
	        if (request.getUsername() != null) {

	        	Utente utente = uDao.findByUsername(request.getUsername());
	            if (utente != null) {
	                UUID uuid = UUID.randomUUID();
	                Long attuale = System.currentTimeMillis();
	                Long expiration = attuale + 900000;
	                
	                String chars ="0123456789";
	                StringBuilder codeBuilder = new StringBuilder();
	                for(int i = 0; i < 6; i++) {
	                	int randomIndex = (int)(Math.random() * chars.length());
	                	codeBuilder.append(chars.charAt(randomIndex));
	                }
	                
	                String code = codeBuilder.toString(); 

	                utente.setCodiceRecupero(uuid.toString());
	                utente.setTimer(expiration);
	                utente.setToken(code);
	                
	                Context ctx = new Context();
	                ctx.setVariable("nome", utente.getNome());
	                ctx.setVariable("cognome", utente.getCognome());
	                ctx.setVariable("codiceRecupero", utente.getCodiceRecupero());
	                ctx.setVariable("token", utente.getToken());
	                
	                String mailBody = engine.process("templateMail", ctx);
	                
	                boolean mailSent = MailUtil.mailSender(utente.getEmail(), utente.getNome(), utente.getCognome(), mailBody);
	                if (!mailSent) {
	                	utente.setSent(false);
	                	}else {
	                		utente.setSent(true);
	                	}
	                uDao.save(utente);

	                return ResponseEntity.status(200).body(new BackendResponse("Utente trovato, mail inviata a " + utente.getEmail() + " " + uuid.toString() + " - " + utente.getToken()));
	            } else {
	                return ResponseEntity.status(200).body(new BackendResponse("Utente non trovato, riprova! "));
	            }
	        } else { 
	            Utente utente = uDao.findByEmail(request.getEmail());
	            if (utente != null) {

	                UUID uuid = UUID.randomUUID();
	                Long attuale = System.currentTimeMillis();
	                Long expiration = attuale + 900000;
	                
	                utente.setCodiceRecupero(uuid.toString());
	                utente.setTimer(expiration);
	                
	                String chars = "0123456789";
	                StringBuilder codeBuilder = new StringBuilder();
	                
	                for (int i = 0; i < 6; i++) {
	                    int randomIndex = (int) (Math.random() * chars.length());
	                    codeBuilder.append(chars.charAt(randomIndex));
	                }

	                String codice = codeBuilder.toString();
	        		utente.setToken(codice);
	        		
	        		
	                Context ctx = new Context();
	                ctx.setVariable("nome", utente.getNome());
	                ctx.setVariable("cognome", utente.getCognome());
	                ctx.setVariable("codiceRecupero", utente.getCodiceRecupero());
	                ctx.setVariable("token", utente.getToken());
	                
	                String mailBody = engine.process("templateMail", ctx);
	                
	                boolean mailSent = MailUtil.mailSender(utente.getEmail(), utente.getNome(), utente.getCognome(), mailBody);
	                if (!mailSent) {
	                	utente.setSent(false);
	                	}else {
	                		utente.setSent(true);
	                	}
	                uDao.save(utente);
	                
	                return ResponseEntity.status(200).body(new BackendResponse("Utente trovato, mail inviata a " + request.getEmail() + " " + uuid.toString() + " - " + utente.getToken()));
	            } else {
	                return ResponseEntity.status(200).body(new BackendResponse("Utente non trovato, riprova!"));
	            }
	        }
	    } else {
	        return ResponseEntity.status(400).body(new BackendResponse("Username o email non possono essere vuoti."));
	    }
	}


	@PostMapping("/reset/{codiceRecupero}") 
	public ResponseEntity<Object> resetPassword(@PathVariable String codiceRecupero, @RequestBody ControlDTO request) {

		Utente user = uDao.findByToken(request.getToken());
	    if  (user != null && System.currentTimeMillis() < user.getTimer()) {
	    	
	        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
	            try {
	                user.setPassword(encoder.encode(request.getPassword()));        
	            } catch(Exception e) {
	                e.printStackTrace();
	                return ResponseEntity.status(500).body(new BackendResponse("Password non hashata!"));
	            } 
	        } else {
	            return ResponseEntity.status(400).body(new BackendResponse("Nuova password non fornita o non valida."));
	        }

	        user.setCodiceRecupero(null);
	        user.setTimer(null);
	        user.setToken(null);
	        user.setSent(false);
	        
	        uDao.save(user);
	        
	        return ResponseEntity.status(200).body(new BackendResponse("Password resettata con successo."));
	    } else {

	        return ResponseEntity.status(400).body(new BackendResponse("Token di reset della password non valido o scaduto."));
	    }
	}
	
}
