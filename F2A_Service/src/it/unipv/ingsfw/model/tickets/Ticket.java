package it.unipv.ingsfw.model.tickets;

import it.unipv.ingsfw.model.users.Corriere;

public class Ticket {
	
	private String idTicket;
	private TipologiaTicket tipologia;
	private StatoTicket stato;
	private Mezzo mezzo;
	private Itinerario itinerario;
	private Corriere corriere;
	//"DataPresaInCarico" e "dataTerminazione" sono presenti a DB e vengono aggiornate quando cambia lo stato del ticket
	//OSS: al momento non sussiste la necessità di averle come attributi nella suddetta classe
	
	/**
	 * @param idTicket
	 * @param tipologia
	 * @param stato
	 */
	public Ticket(String idTicket, TipologiaTicket tipologia, StatoTicket stato, Mezzo mezzo,Itinerario itinerario,Corriere corriere) {
		super();
		this.idTicket = idTicket;
		this.tipologia = tipologia;
		this.stato = stato;
		this.corriere=corriere;
		this.itinerario=itinerario;
		this.mezzo=mezzo;
	}
	
	public Ticket (StatoTicket stato, Corriere corriere) {
		super();
		this.idTicket = null;
		this.tipologia = null;
		this.stato = stato;
		this.corriere=corriere;
		this.itinerario=null;
		this.mezzo=null;
	}
	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public String getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(String idTicket) {
		this.idTicket = idTicket;
	}

	public TipologiaTicket getTipologia() {
		return tipologia;
	}

	public void setTipologia(TipologiaTicket tipologia) {
		this.tipologia = tipologia;
	}

	public StatoTicket getStato() {
		return stato;
	}

	public void setStato(StatoTicket stato) {
		this.stato = stato;
	}
	
	public void setCorriere(Corriere corriere) {
		this.corriere = corriere;
	}
	
	public Corriere getCorriere() {
		return corriere;
	}
	
	@Override
	public String toString() {
		return  "\nTicket " + idTicket + "\nTipologia: " + tipologia + "\nStato: " + stato+"\n\n Info Corriere: "+corriere+"\n \n Info Itinerario: "+itinerario;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}
	
}
