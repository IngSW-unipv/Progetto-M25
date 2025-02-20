package it.unipv.ingsfw.facade.lavorazioneCapi;

import java.util.ArrayList;
import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.lavorazioneCapi.ILavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;

public class ConcreteLavorazioneFacade implements ILavorazioneFacade {

	private final ILavorazioneDAO lavorazioneDAO;

	public ConcreteLavorazioneFacade() {
		lavorazioneDAO = DaoFactory.getLavorazioneDAO();
	}

	public boolean addLavorazione(ObservableStazioneLavoro s, Capo c) {

		boolean esito = false;

		try {
			esito = lavorazioneDAO.addLavorazione(s, c);
		} catch (Exception e) {
			System.err.println("Errore inserimento lavorazione");
		}
		return esito;
	}

	public boolean updateLavorazione(ObservableStazioneLavoro s, Capo c) {

		boolean esito = false;

		try {
			esito = lavorazioneDAO.updateLavorazione(s, c);
		} catch (Exception e) {
			System.err.println("Errore aggiornamento lavorazione");
		}
		return esito;

	}

	public boolean addLavorazioneSospesa(ObservableStazioneLavoro s, Capo c) {

		boolean esito = false;

		try {
			esito = lavorazioneDAO.addLavorazioneSospesa(s, c);
		} catch (Exception e) {
			System.err.println("Errore aggiornamento lavorazione sospesa");
		}
		return esito;

	}

	public ArrayList<Capo> checkPresenzaCapiInStazione(ObservableStazioneLavoro s) {

		ArrayList<Capo> lista = new ArrayList<>();

		try {
			lista = lavorazioneDAO.checkPresenzaCapiInStazione(s);
		} catch (Exception e) {
			System.err.println("Errore nel controllo dei capi presenti a db");
		}
		return lista;

	}
}
