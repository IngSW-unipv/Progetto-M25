package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.F2aFacade;

public class SingletonSedeLavorazione {

	private String idSede;
	private ArrayList<CatenaLavorazione> listaCatene;
	private static SingletonSedeLavorazione instance = null;

	/**
	 * @param idSede
	 * @param listaCatene
	 */
	private SingletonSedeLavorazione() {
		this.idSede = "S001";
		this.listaCatene = new ArrayList<CatenaLavorazione>();
	}

	// Allow construction only once
	// STATIC SENNO' NON INVOCABILE
	public static SingletonSedeLavorazione getInstance() {
		if (instance == null) {
			instance = new SingletonSedeLavorazione();
			System.out.println("Create new instance");
		} else
			System.out.println("Instance already available");
		return instance;
	}

	public String getIdSede() {
		return idSede;
	}

	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}

	public ArrayList<CatenaLavorazione> getListaCatene() {
		return listaCatene;
	}

	public void setListaCatene(ArrayList<CatenaLavorazione> listaCatene) { 
		this.listaCatene = listaCatene; 
	}
	 

	// deve restituire un booleano
	public boolean addCatenaLavorazione(CatenaLavorazione catena) {
		//CatenaLavorazioneDAO cl = new CatenaLavorazioneDAO();
		if (F2aFacade.getInstance().getLavorazioneCapiFacade().insertCatena(catena)) {
			listaCatene.add(catena);
			return true;
		}
		return false;
	}

}
