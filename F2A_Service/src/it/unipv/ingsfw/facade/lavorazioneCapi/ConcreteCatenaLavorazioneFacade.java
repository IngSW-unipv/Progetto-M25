package it.unipv.ingsfw.facade.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.ICatenaLavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;

public class ConcreteCatenaLavorazioneFacade implements ICatenaLavorazioneFacade {

	private final ICatenaLavorazioneDAO catenaLavorazioneDAO;

	public ConcreteCatenaLavorazioneFacade() {
		catenaLavorazioneDAO=DaoFactory.getCatenaLavorazioneDAO();
	}
	
	public ArrayList<CatenaLavorazione> selectAll(){
		
		ArrayList<CatenaLavorazione> lista = new ArrayList<>();

		try {
			lista = catenaLavorazioneDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle catene presenti a db");
		}
		return lista;
		
	}
	public ArrayList<CatenaLavorazione> selectByTipoLavaggio(CatenaLavorazione input){
		
		ArrayList<CatenaLavorazione> lista = new ArrayList<>();

		try {
			lista = catenaLavorazioneDAO.selectByTipoLavaggio(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle catene presenti a db");
		}
		return lista;
		
	}
	public boolean insertCatena(CatenaLavorazione c) {
		
		boolean esito = false;

		try {
			esito = catenaLavorazioneDAO.insertCatena(c);
		} catch (Exception e) {
			System.err.println("Errore inserimento catena");
		}
		return esito;
		
	}
	public boolean checkCatenaAlreadyExists(CatenaLavorazione c) {
		
		boolean esito = false;

		try {
			esito = catenaLavorazioneDAO.checkCatenaAlreadyExists(c);
		} catch (Exception e) {
			System.err.println("Errore controllo esistenza catena");
		}
		return esito;
	}
	public ArrayList<ObservableStazioneLavoro> selectStazioniByCatena(CatenaLavorazione input){
		
		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = catenaLavorazioneDAO.selectStazioniByCatena(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni a db data la catena");
		}
		return lista;
		
	}
	public CatenaLavorazione selectCatenaByStazione(ObservableStazioneLavoro input) {
		
		CatenaLavorazione catena = null;

		try {
			catena = catenaLavorazioneDAO.selectCatenaByStazione(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento della catena presente a db");
		}
		return catena;
		
	}
	public String getNewIdCatena() {
		
		String id = "";

		try {
			id = catenaLavorazioneDAO.getNewIdCatena();
		} catch (Exception e) {
			System.err.println("Errore prelevamento ID della catena");
		}
		return id;
		
	}
}
