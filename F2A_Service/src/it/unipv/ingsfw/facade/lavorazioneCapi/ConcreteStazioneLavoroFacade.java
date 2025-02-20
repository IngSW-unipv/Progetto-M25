package it.unipv.ingsfw.facade.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.IObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.users.Operatore;

public class ConcreteStazioneLavoroFacade implements IStazioneLavoroFacade {

	
	private final IObservableStazioneLavoroDAO observableStazioneLavoroDAO;

	public ConcreteStazioneLavoroFacade() {
		observableStazioneLavoroDAO=DaoFactory.getObservableStazioneLavoroDAO();
	}
	
public ArrayList<ObservableStazioneLavoro> selectAll(){
		
		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni presenti a db");
		}
		return lista;
		
		
	}
	public ArrayList<ObservableStazioneLavoro> selectByStato(ObservableStazioneLavoro input){
		
		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectByStato(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni by stato presenti a db");
		}
		return lista;
		
	}
	public boolean insertStazioneWithUnknownCatena(ObservableStazioneLavoro s) {
		
		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.insertStazioneWithUnknownCatena(s);
		} catch (Exception e) {
			System.err.println("Errore inserimento stazione");
		}
		return esito;
		
	}
	public boolean insertStazioneWithKnownCatena(ObservableStazioneLavoro s, CatenaLavorazione c) {
		
		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.insertStazioneWithKnownCatena(s, c);
		} catch (Exception e) {
			System.err.println("Errore inserimento a db della stazione nota la catena");
		}
		return esito;
		
	}
	public String selectIdCatenaByStazione(ObservableStazioneLavoro s) {
		
		String id = "";

		try {
			id = observableStazioneLavoroDAO.selectIdCatenaByStazione(s);
		} catch (Exception e) {
			System.err.println("Errore prelevamento ID della catena nota la stazione");
		}
		return id;
		
	}
	public boolean assegnazioneResponsabileStazioneLibero(ObservableStazioneLavoro s) {
		
		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.assegnazioneResponsabileStazioneLibero(s);
		} catch (Exception e) {
			System.err.println("Errore assegnamento responsabile a stazione libera");
		}
		return esito;
		
	}
	public boolean assegnazioneManutentoreLibero(ObservableStazioneLavoro s) {
		
		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.assegnazioneManutentoreLibero(s);
		} catch (Exception e) {
			System.err.println("Errore assegnamento manutentore a stazione guasta");
		}
		return esito;
		
	}
	public int getIdLastAssegnazione() {
		
		int id = 0;

		try {
			id = observableStazioneLavoroDAO.getIdLastAssegnazione();
		} catch (Exception e) {
			System.err.println("Errore prelevamento ID della last assegnazione");
		}
		return id;
		
	}
	public String getNewIdStazione() {
		
		String id = "";

		try {
			id = observableStazioneLavoroDAO.getNewIdStazione();
		} catch (Exception e) {
			System.err.println("Errore prelevamento new id stazione");
		}
		return id;
		
	}
	public ArrayList<ObservableStazioneLavoro> selectStazioniReadyNonAssegnate(){
		
		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectStazioniReadyNonAssegnate();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni ready non assegnate presenti a db");
		}
		return lista;
		
	}
	public ArrayList<ObservableStazioneLavoro> selectStazioniMaintenanceNonAssegnate(){
		
		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectStazioniMaintenanceNonAssegnate();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni in maintenance non assegnate presenti a db");
		}
		return lista;
		
	}
	public ObservableStazioneLavoro selectStazioniReadyNonAssegnatePerTipo(ObservableStazioneLavoro s) {
		
		ObservableStazioneLavoro stazione = null;

		try {
			stazione = observableStazioneLavoroDAO.selectStazioniReadyNonAssegnatePerTipo(s);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento delle stazioni in maintenance non assegnate presenti a db");
		}
		return stazione;
		
	}
	public boolean assegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o) {
		
		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.assegnazioneOperatoreNoto(s, o);
		} catch (Exception e) {
			System.err.println("Errore assegnamento operatore noto");
		}
		return esito;
		
	}
	public boolean changeStatoStazione(ObservableStazioneLavoro s) {
		
		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.changeStatoStazione(s);
		} catch (Exception e) {
			System.err.println("Errore cambiamento stato stazione");
		}
		return esito;
		
	}
	public ArrayList<ObservableStazioneLavoro> selectStazioniByOperatore(Operatore o){
		
		ArrayList<ObservableStazioneLavoro> lista = new ArrayList<>();

		try {
			lista = observableStazioneLavoroDAO.selectStazioniByOperatore(o);
		} catch (Exception e) {
			System.err.println("Errore nella selezione delle stazioni noto l'operatore");
		}
		return lista;
		
	}
	
	public boolean chiusuraAssegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o) {
		
		boolean esito = false;

		try {
			esito = observableStazioneLavoroDAO.chiusuraAssegnazioneOperatoreNoto(s, o);
		} catch (Exception e) {
			System.err.println("Errore aggiornamento chiusura assegnazione");
		}
		return esito;
		
	}
}
