package it.unipv.ingsfw.facade.users.dipendenti;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.users.Corriere;
import it.unipv.ingsfw.model.users.Dipendente;
import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.IDipendenteDAO;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.model.users.TipoOperatore;

public class ConcreteDipendentiFacade {

	private final IDipendenteDAO dipendenteDAO;

	public ConcreteDipendentiFacade() {
		dipendenteDAO=DaoFactory.getDipendenteDAO();
	}

	public ArrayList<Dipendente> selectAll() {

		ArrayList<Dipendente> lista = new ArrayList<>();

		try {
			lista = dipendenteDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei dipendenti presenti a db");
		}
		return lista;
	}

	public ArrayList<Corriere> selectCorrieri() {

		ArrayList<Corriere> lista = new ArrayList<>();

		try {
			lista = dipendenteDAO.selectCorrieri();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei corrieri presenti a db");
		}
		return lista;

	}

	public ArrayList<Corriere> selectCorrieriLiberi() {

		ArrayList<Corriere> lista = new ArrayList<>();

		try {
			lista = dipendenteDAO.selectCorrieriLiberi();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei corrieri liberi presenti a db");
		}
		return lista;

	}

	public ArrayList<Operatore> selectResponsabiliStazioneNonAssegnati() {

		ArrayList<Operatore> lista = new ArrayList<>();

		try {
			lista = dipendenteDAO.selectResponsabiliStazioneNonAssegnati();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei corrieri liberi presenti a db");
		}
		return lista;

	}

	public ArrayList<Operatore> selectManutentoriNonAssegnati() {

		ArrayList<Operatore> lista = new ArrayList<>();

		try {
			lista = dipendenteDAO.selectManutentoriNonAssegnati();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei manutentori non assegnati presenti a db");
		}
		return lista;

	}

	public ArrayList<Operatore> selectByTipoOperatore(Operatore input) {

		ArrayList<Operatore> lista = new ArrayList<>();

		try {
			lista = dipendenteDAO.selectByTipoOperatore(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei manutentori non assegnati presenti a db");
		}
		return lista;

	}
	
	public Corriere selectCorriereByEmailPassword(Corriere input) {

		try {
			input = dipendenteDAO.selectCorriereByEmailPassword(input);
		} catch (Exception e) {
			System.err.println("Errore prelevamento del dipendente");
		}
		return input;

	}

	public boolean selectByEmailPassword(Dipendente input) {

		boolean esito = false;

		try {
			esito = dipendenteDAO.selectByEmailPassword(input);
		} catch (Exception e) {
			System.err.println("Errore prelevamento del dipendente");
		}
		return esito;

	}

	public String selectIdByEmailPassword(Dipendente input) {

		String esito = "";

		try {
			esito = dipendenteDAO.selectIdByEmailPassword(input);
		} catch (Exception e) {
			System.err.println("Errore prelevamento ID del dipendente");
		}
		return esito;

	}

	public String getNewIdDipendente() {

		String esito = "";

		try {
			esito = dipendenteDAO.getNewIdDipendente();
		} catch (Exception e) {
			System.err.println("Errore prelevamento nuovo ID");
		}
		return esito;

	}

	public boolean insertDipendente(Dipendente d) {

		boolean esito = false;

		try {
			esito = dipendenteDAO.insertDipendente(d);
		} catch (Exception e) {
			System.err.println("Errore inserimento del dipendente");
		}
		return esito;

	}

	public TipoOperatore selectTipoOperatoreById(Dipendente input) {

		TipoOperatore esito = null;

		try {
			esito = dipendenteDAO.selectTipoOperatoreById(input);
		} catch (Exception e) {
			System.err.println("Errore prelevamento tipologia dipendente");
		}
		return esito;

	}
	
	//aggiunto in data 22/02
	public String selectTipoDipendenteById(Dipendente input) {
		String ris = null;
		try {
			ris = dipendenteDAO.selectTipoDipendenteById(input);
		} catch (Exception e) {
			System.err.println("Errore prelevamento tipologia dipendente");
		}
		return ris;
	}

	public Operatore selectOperatoreByEmailPassword(Dipendente input) {

		Operatore esito = null;

		try {
			esito = dipendenteDAO.selectOperatoreByEmailPassword(input);
		} catch (Exception e) {
			System.err.println("Errore prelevamento operatore tramite credenziali");
		}
		return esito;

	}

	public Corriere selectCorriereById(Corriere cF) {

		Corriere esito = null;

		try {
			esito = dipendenteDAO.selectCorriereById(cF);
		} catch (Exception e) {
			System.err.println("Errore prelevamento corriere");
		}
		return esito;

	}

}
