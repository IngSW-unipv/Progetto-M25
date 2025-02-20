package it.unipv.ingsfw.facade.tickets;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.tickets.IMezzoDAO;
import it.unipv.ingsfw.model.tickets.Mezzo;

public class ConcreteMezzoFacade implements IMezzoFacade {

	private final IMezzoDAO mezzoDAO;

	public ConcreteMezzoFacade() {
		mezzoDAO = DaoFactory.getMezzoDAO(); // attraverso il DAOFactory siamo in grado di prelevare il metodo
												// getInstance() della classe MezzoDAO prelevamento il percorso della
												// classe originale attraverso il file PROPERTIES
	}

	public ArrayList<Mezzo> selectAll(){
		
		ArrayList<Mezzo> lista = new ArrayList<>();

		try {
			lista = mezzoDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei mezzi presenti a db");
		}
		return lista;
		
	}

	public ArrayList<Mezzo> selectByCapienza(Mezzo input){
		
		ArrayList<Mezzo> lista = new ArrayList<>();

		try {
			lista = mezzoDAO.selectByCapienza(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei mezzi per capienza presenti a db");
		}
		return lista;
		
	}

	public boolean insertMezzo(Mezzo m) {
		
		boolean esito = false;

		try {
			esito = mezzoDAO.insertMezzo(m);
		} catch (Exception e) {
			System.err.println("Errore inserimento mezzo");
		}
		return esito;

		
	}

	public Mezzo selectById(Mezzo m) {
		
		Mezzo mezzo = null;

		try {
			mezzo = mezzoDAO.selectById(m);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei mezzi per id presenti a db");
		}
		return mezzo;
		
	}
}
