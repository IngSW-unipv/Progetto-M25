package it.unipv.ingsfw.facade.lavaggio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.ILavaggioDAO;

public class ConcreteLavaggioFacade implements ILavaggioFacade {
	
	private final ILavaggioDAO lavaggioDAO;

	public ConcreteLavaggioFacade() {
		lavaggioDAO = DaoFactory.getLavaggioDAO();
	}
	
	public HashMap<Integer,String> selectAll(){
		
		HashMap<Integer,String> map = new HashMap<>();

		try {
			map = lavaggioDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei lavaggi presenti a db");
		}
		return map;
		
	}
	public int getCostoLavaggio(String descrizione) {
		
		int esito = 0;

		try {
			esito = lavaggioDAO.getCostoLavaggio(descrizione);
		} catch (Exception e) {
			System.err.println("Errore prelevamento costo lavaggio");
		}
		return esito;
		
	}
	public int getCostoLavaggio(int idLavaggio) {
		
		int esito = 0;

		try {
			esito = lavaggioDAO.getCostoLavaggio(idLavaggio);
		} catch (Exception e) {
			System.err.println("Errore prelevamento costo lavaggio");
		}
		return esito;
		
		
	}

}
