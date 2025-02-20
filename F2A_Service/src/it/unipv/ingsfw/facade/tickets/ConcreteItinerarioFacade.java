package it.unipv.ingsfw.facade.tickets;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.tickets.IItinerarioDAO;
import it.unipv.ingsfw.model.tickets.Itinerario;

public class ConcreteItinerarioFacade implements IItinerarioFacade{
	
	private final IItinerarioDAO itinerarioDAO;

	public ConcreteItinerarioFacade() {
		itinerarioDAO=DaoFactory.getItinerarioDAO();
	}
	
	public ArrayList<Itinerario> selectAll(){
		
		ArrayList<Itinerario> lista = new ArrayList<>();

		try {
			lista = itinerarioDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento degli itinerari presenti a db");
		}
		return lista;
		
	}
	public ArrayList<Itinerario> selectByStatoTappa(Itinerario input){
		
		ArrayList<Itinerario> lista = new ArrayList<>();

		try {
			lista = itinerarioDAO.selectByStatoTappa(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento degli itinerari per stato tappa presenti a db");
		}
		return lista;
		
	}
	public boolean insertItinerario(Itinerario i) {
		
		boolean esito = false;

		try {
			esito = itinerarioDAO.insertItinerario(i);
		} catch (Exception e) {
			System.err.println("Errore inserimento itinerario");
		}
		return esito;
		
	}
	public Itinerario selectById(Itinerario i) {
		
		Itinerario itinerario = null;

		try {
			itinerario = itinerarioDAO.selectById(i);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento degli itinerari per id presenti a db");
		}
		return itinerario;
		
	}

}
