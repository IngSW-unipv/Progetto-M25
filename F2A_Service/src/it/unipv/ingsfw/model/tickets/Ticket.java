package it.unipv.ingsfw.model.tickets;

import java.util.ArrayList;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.users.Corriere;

public class Ticket {
	
	private String idTicket;
	private TipologiaTicket tipologia;
	private StatoTicket stato;
	private Mezzo mezzo;
	private Itinerario itinerario;
	private Corriere corriere;
	private ArrayList <Capo> listaCapiRitOCon;
	//"DataPresaInCarico" e "dataTerminazione" sono presenti a DB e vengono aggiornate quando cambia lo stato del ticket
	//OSS: al momento non sussiste la necessità di averle come attributi nella suddetta classe
	
	/**
	 * @param idTicket
	 * @param tipologia
	 * @param stato
	 */
	public Ticket(String idTicket, TipologiaTicket tipologia, StatoTicket stato, Mezzo mezzo,Itinerario itinerario,Corriere corriere) {
		super();
		//Aggiunti in Fase di testing, ovviamente questo tipo di controllo non è fatto nei costruttori ove si vuole idTicket=null
		if (idTicket == null || idTicket.length() == 0) {
	        throw new IllegalArgumentException("idTicket non può essere vuoto.");
	    }
	    if (idTicket.length() > 4) {
	        throw new IllegalArgumentException("idTicket non può superare i 4 caratteri.");
	    }
		this.idTicket = idTicket;
		this.tipologia = tipologia;
		this.stato = stato;
		this.corriere=corriere;
		this.itinerario=itinerario;
		this.mezzo=mezzo;
		this.listaCapiRitOCon= new ArrayList<Capo>();
	}
	
	public ArrayList<Capo> getListaCapiRitOCon() {
		return listaCapiRitOCon;
	}

	public void setListaCapiRitOCon(ArrayList<Capo> listaCapiRitOCon) {
		this.listaCapiRitOCon = listaCapiRitOCon;
	}

	public Ticket (StatoTicket stato, Corriere corriere) {
		super();
		this.idTicket = null;
		this.tipologia = null;
		this.stato = stato;
		this.corriere=corriere;
		this.itinerario=null;
		this.mezzo=null;
		this.listaCapiRitOCon= new ArrayList<Capo>();
	}
	public Ticket() {
		// TODO Auto-generated constructor stub
		this.listaCapiRitOCon= new ArrayList<Capo>();
	}

	public Ticket(String idTicket) {
		super();
		if (idTicket == null || idTicket.length() == 0) {
	        throw new IllegalArgumentException("idTicket non può essere vuoto.");
	    }
	    if (idTicket.length() > 4) {
	        throw new IllegalArgumentException("idTicket non può superare i 4 caratteri.");
	    }
	    if (idTicket.length() < 4) {
	        throw new IllegalArgumentException("idTicket non può avere meno di 4 caratteri");
	    }
		this.idTicket = idTicket;
		this.tipologia = null;
		this.stato = null;
		this.corriere=null;
		this.itinerario=null;
		this.mezzo=null;
		this.listaCapiRitOCon= new ArrayList<Capo>();
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
		return  "\nTicket " + idTicket + "\nTipologia: " + tipologia + "\nStato: " + stato+"\n\n Info Corriere: "+corriere+"\n\n Info Itinerario:"+itinerario+"\n\n Info Mezzo:"+mezzo;
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
