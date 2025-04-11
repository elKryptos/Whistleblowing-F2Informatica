package f2.tironcinio.whistleblowing.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import f2.tironcinio.whistleblowing.daos.SegnalazioneDao;
import f2.tironcinio.whistleblowing.entities.Segnalazione;

@RestController
@CrossOrigin
public class StatisticheController {
	
	@Autowired
	SegnalazioneDao sDao;
	
	
	@GetMapping("/totaleSegnalazioni")
	public int numeroSegnalazioni(){
		int totaleSegnalazione = sDao.numeroTotale();
		return totaleSegnalazione;
	}
	
	@GetMapping("/segnalazioniChiuse")
	public int segnalazioniChiuse() {
		int totaleChiuse = sDao.numeroChiuse();
		return totaleChiuse;
	}
	
	
	@GetMapping("/segnalazioniAperte")
	public int segnalazioniAperte() {
		return sDao.numeroAperte();
	}
	
	@GetMapping("/urgenze")
	public int urgenteEChiuso() {
		int totaleUrgente = sDao.numeroUrgentiEChiusi();
		return totaleUrgente;
	}
	
	@GetMapping("/mediaChiusura")
	public int mediaChiusura() {
		int media = sDao.mediaChiusura();
		return media;
	}
	
	@GetMapping("/piuVeloce")
	public String piuVeloce() {
		Map<String, Object> result = (Map<String, Object>) sDao.piuVeloce();
		 String username = (String) result.get("username");
		 return username;
	}
	
	@GetMapping("/segnalazioniMeseStato/{mese}/{stato}")
	public ResponseEntity<Object> segnMeseStato (@PathVariable Boolean stato ,@PathVariable String mese){
		List<Segnalazione> s = sDao.segnMeseStato(mese, stato);
		return ResponseEntity.status(200).body(s);
	}
}
