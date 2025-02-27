package it.unipv.ingsfw.model.tickets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.ingsfw.facade.F2aFacade;

public class Mezzo {
	
	private String idMezzo;
	private int capienza;
	private boolean disponibilita;
	private static final int CAPACITA_MINIMA = 10;
    private static final int CAPACITA_MASSIMA = 500;
	
	/**
	 * @param idMezzo
	 * @param capienza
	 * @param disponibilita
	 */
    
	public Mezzo(String idMezzo, int capienza, boolean disponibilita) {
		super();
		Pattern pattern = Pattern.compile("M\\d{3}");
        Matcher matcher = pattern.matcher(idMezzo);
        if(!matcher.matches()) {
            this.idMezzo = null;
        } else {
            this.idMezzo = idMezzo;
        }
		setCapienza(capienza);
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
        if (capienza < CAPACITA_MINIMA || capienza > CAPACITA_MASSIMA) {
            throw new IllegalArgumentException("La capienza deve essere compresa tra " + CAPACITA_MINIMA + " e " + CAPACITA_MASSIMA);
        }
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
		Mezzo m = F2aFacade.getInstance().getGestioneTicketsFacade().selectMezzoById(this);
		return m;
	}
	
	//fare un metodo che interroga db per capire se il mezzo è disponibile o meno
	//ovvero se il suo id appare in almeno una delle tuple dei ticket venti stato "Preso in carico"
	
	@Override
	public String toString() {
		return  "\n Id Mezzo: " + idMezzo + "\n Capienza: " + capienza;
	}
	
}
