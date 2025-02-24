package it.unipv.ingsfw.facade.capo;

import java.text.ParseException;
import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.ICapoDAO;

public class ConcreteCapoFacade implements ICapoFacade {

	private final ICapoDAO capoDAO;

	public ConcreteCapoFacade() {
		capoDAO = DaoFactory.getCapoDAO();
	}

	public ArrayList<Capo> selectAll() {

		ArrayList<Capo> lista = new ArrayList<>();

		try {
			lista = capoDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei capi presenti a db");
		}
		return lista;

	}

	public ArrayList<Capo> selectCapoByStatoETipo(Capo input) {

		ArrayList<Capo> lista = new ArrayList<>();

		try {
			lista = capoDAO.selectCapoByStatoETipo(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei capi per stato e tipo presenti a db");
		}
		return lista;

	}

	public ArrayList<Capo> selectCapiDaRitirareByTappa(Capo input) {

		ArrayList<Capo> lista = new ArrayList<>();

		try {
			lista = capoDAO.selectCapiDaRitirareByTappa(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei capi da ritirare per tappa");
		}
		return lista;

	}

	public ArrayList<Capo> selectCapiDaConsegnareByTappa(Capo input) {

		ArrayList<Capo> lista = new ArrayList<>();

		try {
			lista = capoDAO.selectCapiDaConsegnareByTappa(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei capi da ritirare per tappa");
		}
		return lista;

	}

	public boolean updateStatoCapo(Capo inputSet) {

		boolean esito = false;

		try {
			esito = capoDAO.updateStatoCapo(inputSet);
		} catch (Exception e) {
			System.err.println("Errore update stato capo");
		}
		return esito;

	}

	public boolean updateStatoCapoByTappa(Capo inputSet) {

		boolean esito = false;

		try {
			esito = capoDAO.updateStatoCapoByTappa(inputSet);
		} catch (Exception e) {
			System.err.println("Errore update stato capo per tappa");
		}
		return esito;

	}

	public String getNewIdCapo() {

		String id = "";

		try {
			id = capoDAO.getNewIdCapo();
		} catch (Exception e) {
			System.err.println("Errore generazione nuovo id capo");
		}
		return id;

	}

	public boolean insertCapo(Capo c) throws ParseException {

		boolean esito = false;

		try {
			esito = capoDAO.insertCapo(c);
		} catch (Exception e) {
			System.err.println("Errore inserimento capo");
		}
		return esito;

	}
	
	public String getStatoCapoById(Capo c) {
		String esito="";

	try {
		esito = capoDAO.getStatoCapoById(c);
	} catch (Exception e) {
		System.err.println("Errore recupero stato capo");
	}
	return esito;
		
	}

}
