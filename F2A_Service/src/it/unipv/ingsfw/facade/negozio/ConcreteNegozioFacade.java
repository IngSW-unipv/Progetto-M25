package it.unipv.ingsfw.facade.negozio;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.negozio.INegozioDAO;
import it.unipv.ingsfw.model.negozio.Negozio;

public class ConcreteNegozioFacade implements INegozioFacade {
	
	private final INegozioDAO negozioDAO;

	public ConcreteNegozioFacade() {
		negozioDAO = DaoFactory.getNegozioDAO();
	}
	
	public ArrayList<Negozio> selectAll(){
		
		ArrayList<Negozio> lista = new ArrayList<>();

		try {
			lista = negozioDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei negozi presenti a db");
		}
		return lista;
		
	}
	public boolean insertNegozio(Negozio n) {
		
		boolean esito = false;

		try {
			esito = negozioDAO.insertNegozio(n);
		} catch (Exception e) {
			System.err.println("Errore inserimento nuovo negozio");
		}
		return esito;
		
	}

}
