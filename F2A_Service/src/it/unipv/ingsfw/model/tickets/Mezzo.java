package it.unipv.ingsfw.model.tickets;

import it.unipv.ingsfw.model.users.Corriere;
import it.unipv.ingsfw.model.users.DipendenteDAO;

public class Mezzo {
	
	private String idMezzo;
	private int capienza;
	private boolean disponibilita;
	
	/**
	 * @param idMezzo
	 * @param capienza
	 * @param disponibilita
	 */
	public Mezzo(String idMezzo, int capienza, boolean disponibilita) {
		super();
		this.idMezzo = idMezzo;
		this.capienza = capienza;
		this.disponibilita = disponibilita;
	}
	
	//AGGIUNTO IN DATA 19/02
	//Costruttore ALTERNATIVO
	public Mezzo (String idMezzo) {
		super();
		this.idMezzo = idMezzo;
		this.capienza = 0;
		this.disponibilita = false;
	}
	public String getIdMezzo() {
		return idMezzo;
	}

	public void setIdMezzo(String idMezzo) {
		this.idMezzo = idMezzo;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	//AGGIUNTO IN DATA 19/02
	public Mezzo getMezzoByID() {
		MezzoDAO dao = new MezzoDAO();
		Mezzo m = dao.selectById(this);
		return m;
	}
	
	//fare un metodo che interroga db per capire se il mezzo è disponibile o meno
	//ovvero se il suo id appare in almeno una delle tuple dei ticket venti stato "Preso in carico"
	
	@Override
	public String toString() {
		return  "Mezzo " + idMezzo + "\nCapienza: " + capienza + "\nDisponibilità: " + disponibilita;
	}
	
}
