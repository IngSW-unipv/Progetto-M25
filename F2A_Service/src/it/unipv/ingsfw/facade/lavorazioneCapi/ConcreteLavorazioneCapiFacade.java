package it.unipv.ingsfw.facade.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.ICatenaLavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ILavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.IObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.users.Operatore;

public class ConcreteLavorazioneCapiFacade implements ICatenaLavorazioneFacade, IStazioneLavoroFacade, ILavorazioneFacade {

	private final IObservableStazioneLavoroDAO observableStazioneLavoroDAO;
	private final ICatenaLavorazioneDAO catenaLavorazioneDAO;
	private final ILavorazioneDAO lavorazioneDAO;

	public ConcreteLavorazioneCapiFacade() {
		observableStazioneLavoroDAO = DaoFactory.getObservableStazioneLavoroDAO();
		catenaLavorazioneDAO = DaoFactory.getCatenaLavorazioneDAO();
		lavorazioneDAO = DaoFactory.getLavorazioneDAO();
	}

	@Override
	public ArrayList<ObservableStazioneLavoro> selectAllStazioni() {

		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni presenti a db");
		}
		return lista;

	}

	@Override
	public ArrayList<ObservableStazioneLavoro> selectByStato(ObservableStazioneLavoro input) {

		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectByStato(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni by stato presenti a db");
		}
		return lista;

	}

	@Override
	public boolean insertStazioneWithUnknownCatena(ObservableStazioneLavoro s) {

		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.insertStazioneWithUnknownCatena(s);
		} catch (Exception e) {
			System.err.println("Errore inserimento stazione");
		}
		return esito;

	}

	@Override
	public boolean insertStazioneWithKnownCatena(ObservableStazioneLavoro s, CatenaLavorazione c) {

		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.insertStazioneWithKnownCatena(s, c);
		} catch (Exception e) {
			System.err.println("Errore inserimento a db della stazione nota la catena");
		}
		return esito;

	}

	@Override
	public String selectIdCatenaByStazione(ObservableStazioneLavoro s) {

		String id = "";

		try {
			id = observableStazioneLavoroDAO.selectIdCatenaByStazione(s);
		} catch (Exception e) {
			System.err.println("Errore prelevamento ID della catena nota la stazione");
		}
		return id;

	}

	@Override
	public boolean assegnazioneResponsabileStazioneLibero(ObservableStazioneLavoro s) {

		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.assegnazioneResponsabileStazioneLibero(s);
		} catch (Exception e) {
			System.err.println("Errore assegnamento responsabile a stazione libera");
		}
		return esito;

	}

	@Override
	public boolean assegnazioneManutentoreLibero(ObservableStazioneLavoro s) {

		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.assegnazioneManutentoreLibero(s);
		} catch (Exception e) {
			System.err.println("Errore assegnamento manutentore a stazione guasta");
		}
		return esito;

	}

	@Override
	public int getIdLastAssegnazione() {

		int id = 0;

		try {
			id = observableStazioneLavoroDAO.getIdLastAssegnazione();
		} catch (Exception e) {
			System.err.println("Errore prelevamento ID della last assegnazione");
		}
		return id;

	}

	@Override
	public String getNewIdStazione() {

		String id = "";

		try {
			id = observableStazioneLavoroDAO.getNewIdStazione();
		} catch (Exception e) {
			System.err.println("Errore prelevamento new id stazione");
		}
		return id;

	}

	@Override
	public ArrayList<ObservableStazioneLavoro> selectStazioniReadyNonAssegnate() {

		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectStazioniReadyNonAssegnate();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni ready non assegnate presenti a db");
		}
		return lista;

	}

	@Override
	public ArrayList<ObservableStazioneLavoro> selectStazioniMaintenanceNonAssegnate() {

		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectStazioniMaintenanceNonAssegnate();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni in maintenance non assegnate presenti a db");
		}
		return lista;

	}

	@Override
	public ObservableStazioneLavoro selectStazioniReadyNonAssegnatePerTipo(ObservableStazioneLavoro s) {

		ObservableStazioneLavoro stazione = null;

		try {
			stazione = observableStazioneLavoroDAO.selectStazioniReadyNonAssegnatePerTipo(s);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni in maintenance non assegnate presenti a db");
		}
		return stazione;

	}

	@Override
	public boolean assegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o) {

		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.assegnazioneOperatoreNoto(s, o);
		} catch (Exception e) {
			System.err.println("Errore assegnamento operatore noto");
		}
		return esito;

	}

	@Override
	public boolean changeStatoStazione(ObservableStazioneLavoro s) {

		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.changeStatoStazione(s);
		} catch (Exception e) {
			System.err.println("Errore cambiamento stato stazione");
		}
		return esito;

	}

	@Override
	public ArrayList<ObservableStazioneLavoro> selectStazioniByOperatore(Operatore o) {

		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectStazioniByOperatore(o);
		} catch (Exception e) {
			System.err.println("Errore nella selezione delle stazioni noto l'operatore");
		}
		return lista;

	}

	@Override
	public boolean chiusuraAssegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o) {

		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.chiusuraAssegnazioneOperatoreNoto(s, o);
		} catch (Exception e) {
			System.err.println("Errore aggiornamento chiusura assegnazione");
		}
		return esito;

	}

	@Override
	public ArrayList<CatenaLavorazione> selectAllCatene() {

		ArrayList<CatenaLavorazione> lista = new ArrayList<>();

		try {
			lista = catenaLavorazioneDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle catene presenti a db");
		}
		return lista;

	}

	@Override
	public ArrayList<CatenaLavorazione> selectByTipoLavaggio(CatenaLavorazione input) {

		ArrayList<CatenaLavorazione> lista = new ArrayList<>();

		try {
			lista = catenaLavorazioneDAO.selectByTipoLavaggio(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle catene presenti a db");
		}
		return lista;

	}

	@Override
	public boolean insertCatena(CatenaLavorazione c) {

		boolean esito = false;

		try {
			esito = catenaLavorazioneDAO.insertCatena(c);
		} catch (Exception e) {
			System.err.println("Errore inserimento catena");
		}
		return esito;

	}

	@Override
	public boolean checkCatenaAlreadyExists(CatenaLavorazione c) {

		boolean esito = false;

		try {
			esito = catenaLavorazioneDAO.checkCatenaAlreadyExists(c);
		} catch (Exception e) {
			System.err.println("Errore controllo esistenza catena");
		}
		return esito;
	}

	@Override
	public ArrayList<ObservableStazioneLavoro> selectStazioniByCatena(CatenaLavorazione input) {

		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = catenaLavorazioneDAO.selectStazioniByCatena(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni a db data la catena");
		}
		return lista;

	}

	@Override
	public CatenaLavorazione selectCatenaByStazione(ObservableStazioneLavoro input) {

		CatenaLavorazione catena = null;

		try {
			catena = catenaLavorazioneDAO.selectCatenaByStazione(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento della catena presente a db");
		}
		return catena;

	}

	@Override
	public String getNewIdCatena() {

		String id = "";

		try {
			id = catenaLavorazioneDAO.getNewIdCatena();
		} catch (Exception e) {
			System.err.println("Errore prelevamento ID della catena");
		}
		return id;

	}

	@Override
	public boolean addLavorazione(ObservableStazioneLavoro s, Capo c) {

		boolean esito = false;

		try {
			esito = lavorazioneDAO.addLavorazione(s, c);
		} catch (Exception e) {
			System.err.println("Errore inserimento lavorazione");
		}
		return esito;
	}

	@Override
	public boolean updateLavorazione(ObservableStazioneLavoro s, Capo c) {

		boolean esito = false;

		try {
			esito = lavorazioneDAO.updateLavorazione(s, c);
		} catch (Exception e) {
			System.err.println("Errore aggiornamento lavorazione");
		}
		return esito;

	}

	@Override
	public boolean addLavorazioneSospesa(ObservableStazioneLavoro s, Capo c) {

		boolean esito = false;

		try {
			esito = lavorazioneDAO.addLavorazioneSospesa(s, c);
		} catch (Exception e) {
			System.err.println("Errore aggiornamento lavorazione sospesa");
		}
		return esito;

	}

	@Override
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
