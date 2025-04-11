package f2.tironcinio.whistleblowing.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import f2.tironcinio.whistleblowing.responses.Ruolo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "utenti")
public class Utente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(length = 60)
	String nome, cognome, password, codiceRecupero, token, username;
	
	@Column(unique = true, nullable = false)
	String email;
	
	
	@Enumerated(EnumType.STRING)
	Ruolo ruolo;
	
	Long timer;
	Boolean sent;
	
	@OneToMany(mappedBy = "utente")
	@JsonIgnore
	List <Commento> commenti;
	
	@OneToMany(mappedBy = "utente")
	@JsonIgnore
	List <Segnalazione> segnalazioni;
	
	public Utente() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodiceRecupero() {
		return codiceRecupero;
	}

	public void setCodiceRecupero(String codiceRecupero) {
		this.codiceRecupero = codiceRecupero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Long getTimer() {
		return timer;
	}

	public void setTimer(Long timer) {
		this.timer = timer;
	}
	
	public Boolean getSent() {
		return sent;
	}

	public void setSent(Boolean sent) {
		this.sent = sent;
	}

	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}

	public List<Segnalazione> getSegnalazioni() {
		return segnalazioni;
	}

	public void setSegnalazioni(List<Segnalazione> segnalazioni) {
		this.segnalazioni = segnalazioni;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
