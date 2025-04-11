package f2.tironcinio.whistleblowing.entities;

import java.util.List;


import f2.tironcinio.whistleblowing.responses.Priorita;
import f2.tironcinio.whistleblowing.responses.SegnalazionePrecedente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "segnalazioni")
public class Segnalazione {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Integer id;
	Long dataCreazione, dataChiusura, dataAggiornamento, scadenza;
	Boolean stato = false;
	Boolean confermaLettura = false;
	
	@Enumerated(EnumType.STRING)
	Priorita priorita;
	
	@Enumerated(EnumType.STRING)
	SegnalazionePrecedente segnalazionePrecedente;
	 
	String codiceSegnalazione, titolo, dove, quando, personeInformate, scopo, coinvolgimento;
	
	@Column(length = 5000)
	String descrizione;
	
	Boolean cronReport;
	
	String mese;
	
	@ManyToOne
	@JoinColumn(name = "utente_id", referencedColumnName = "id")
	Utente utente;
	
	@OneToMany(mappedBy = "segnalazione")
	List <Commento> commenti;
	
	public Segnalazione() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Long dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Long getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(Long dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public Long getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(Long dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public Long getScadenza() {
		return scadenza;
	}

	public void setScadenza(Long scadenza) {
		this.scadenza = scadenza;
	}

	public Boolean getStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public Boolean getConfermaLettura() {
		return confermaLettura;
	}

	public void setConfermaLettura(Boolean confermaLettura) {
		this.confermaLettura = confermaLettura;
	}

	public Priorita getPriorita() {
		return priorita;
	}

	public void setPriorita(Priorita priorita) {
		this.priorita = priorita;
	}

	public SegnalazionePrecedente getSegnalazionePrecedente() {
		return segnalazionePrecedente;
	}

	public void setSegnalazionePrecedente(SegnalazionePrecedente segnalazionePrecedente) {
		this.segnalazionePrecedente = segnalazionePrecedente;
	}

	public String getCoinvolgimento() {
		return coinvolgimento;
	}

	public void setCoinvolgimento(String coinvolgimento) {
		this.coinvolgimento = coinvolgimento;
	}

	public String getCodiceSegnalazione() {
		return codiceSegnalazione;
	}

	public void setCodiceSegnalazione(String codiceSegnalazione) {
		this.codiceSegnalazione = codiceSegnalazione;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDove() {
		return dove;
	}

	public void setDove(String dove) {
		this.dove = dove;
	}

	public String getQuando() {
		return quando;
	}

	public void setQuando(String quando) {
		this.quando = quando;
	}

	public String getPersoneInformate() {
		return personeInformate;
	}

	public void setPersoneInformate(String personeInformate) {
		this.personeInformate = personeInformate;
	}

	public String getScopo() {
		return scopo;
	}

	public void setScopo(String scopo) {
		this.scopo = scopo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Boolean getCronReport() {
		return cronReport;
	}

	public void setCronReport(Boolean cronReport) {
		this.cronReport = cronReport;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}

	public String getMese() {
		return mese;
	}

	public void setMese(String mese) {
		this.mese = mese;
	}
	
}