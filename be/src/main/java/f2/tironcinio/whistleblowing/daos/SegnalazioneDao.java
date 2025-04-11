package f2.tironcinio.whistleblowing.daos;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import f2.tironcinio.whistleblowing.entities.Segnalazione;

public interface SegnalazioneDao extends JpaRepository<Segnalazione, Integer> {

	@Query(value = "select * from segnalazioni where titolo = :titolo", nativeQuery = true)
	List<Segnalazione> ricercaTitolo(String titolo);

	@Query(value = "SELECT * FROM segnalazioni WHERE codice_segnalazione = :codiceSegnalazione", nativeQuery = true)
	Segnalazione dettagliSegnalazione(String codiceSegnalazione);

	@Query(value = "SELECT * FROM segnalazioni WHERE utente_id IS NULL", nativeQuery = true)
	List<Segnalazione> segnDaAssegnare();

	@Query(value = "select * from segnalazioni where stato = :stato", nativeQuery = true)
	List<Segnalazione> segnByStato(Boolean stato);

	@Query(value = "select * from segnalazioni where utente_id = :id and stato = :stato", nativeQuery = true)
	List<Segnalazione> segnalazioniUtenteByStato(Integer id, Boolean stato);

	@Query(value = "select * from segnalazioni where utente_id = :id", nativeQuery = true)
	List<Segnalazione> segnalazioniUtente(Integer id);

	@Query(value = "select * from segnalazioni where codice_segnalazione = :codiceSegnalazione", nativeQuery = true)
	Segnalazione findByCodice(String codiceSegnalazione);

	@Query(value = "select * from segnalazioni where priorita != \"Urgente\" AND ((scadenza - :now) / (1000 * 60 * 60 * 24)) <= 7", nativeQuery = true)
	List<Segnalazione> findUrgentReports(Long now);
	
	@Query(value = "select count(s.codice_segnalazione) as totale from whistleblowing.segnalazioni s", nativeQuery = true)
	int numeroTotale();

	@Query(value = "select count(s.codice_segnalazione) as totale from whistleblowing.segnalazioni s where s.stato = 1", nativeQuery = true)
	int numeroChiuse();

	@Query(value = "select count(s.codice_segnalazione) as totale from whistleblowing.segnalazioni s where s.stato = 0 and s.priorita = 'Urgente'", nativeQuery = true)
	int numeroUrgentiEChiusi();

	@Query(value = "select round(avg((s.data_chiusura - s.data_creazione)/(1000*60*60))) as media from whistleblowing.segnalazioni s where s.data_creazione < s.data_chiusura", nativeQuery = true)
	int mediaChiusura();

	@Query(value = "select count(s.codice_segnalazione) as totale from whistleblowing.segnalazioni s where s.stato = 0", nativeQuery = true)
	int numeroAperte();

	@Query(value = "select s.utente_id, u.username, u.nome, round(avg((s.data_chiusura - s.data_creazione)/(1000*60*60))) as tempo_medio_chiusura from whistleblowing.segnalazioni s join whistleblowing.utenti u on s.utente_id = u.id where stato = 1 group by utente_id order by tempo_medio_chiusura asc limit 1", nativeQuery = true)
	Map <String, Object> piuVeloce();
	
	@Query(value = "select * from segnalazioni where mese=:mese  and stato= :stato", nativeQuery = true)
	List<Segnalazione> segnMeseStato (String mese , Boolean stato);
	
}
