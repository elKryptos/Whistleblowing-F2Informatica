package f2.tironcinio.whistleblowing.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "commenti")
public class Commento {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(length = 1000)
	String testo;
	
	Long dataCreazione;
	
	@ManyToOne
	@JoinColumn(name = "utente_id", referencedColumnName = "id")
	Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "segnalazione_id", referencedColumnName = "id")
	@JsonIgnore
	Segnalazione segnalazione;
	
	public Commento() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Long getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Long dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Segnalazione getSegnalazione() {
		return segnalazione;
	}

	public void setSegnalazione(Segnalazione segnalazione) {
		this.segnalazione = segnalazione;
	}
	
}
