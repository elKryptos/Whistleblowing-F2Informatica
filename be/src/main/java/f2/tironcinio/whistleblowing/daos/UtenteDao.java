package f2.tironcinio.whistleblowing.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import f2.tironcinio.whistleblowing.entities.Utente;

public interface UtenteDao extends JpaRepository <Utente, Integer> {
	
	@Query(value= "select * from whistleblowing.utenti where username = :username", nativeQuery =true)
	Utente findByUsername(String username);
	
	@Query(value= "select * from whistleblowing.utenti where username = :username", nativeQuery =true)
	List<Utente> listByUsername(String username);
	
	@Query(value= "select * from whistleblowing.utenti where email = :email", nativeQuery =true)
	Utente findByEmail(String email);
	
	@Query(value= "select * from whistleblowing.utenti where token = :token", nativeQuery = true)
	Utente findByToken(String token);
	
	@Query(value="select * from whistleblowing.utenti where timer < (17147473430000 + (60 * 60 * 1000)) and sent = 0", nativeQuery = true)
	List<Utente> expiringReports(Long ts);
	
	@Query(value= "select * from whistleblowing.utenti where ruolo = :ruolo", nativeQuery =true)
	List<Utente> findByRuolo(String ruolo);
	
	@Query(value= "select * from whistleblowing.utenti where ruolo = :ruolo", nativeQuery =true)
	Utente ByRuolo(String ruolo);
	
	@Query(value= "select * from whistleblowing.utenti where codiceRecupero = :codiceRecupero", nativeQuery =true)
	Utente findByCodice(String codiceRecupero);
	
	
	
}
