package it.unipv.ingsfw.facade.gestioneTicket;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.tickets.IItinerarioDAO;
import it.unipv.ingsfw.model.tickets.IMezzoDAO;
import it.unipv.ingsfw.model.tickets.ITicketDAO;
import it.unipv.ingsfw.model.tickets.Itinerario;
import it.unipv.ingsfw.model.tickets.Mezzo;
import it.unipv.ingsfw.model.tickets.Ticket;

public class ConcreteGestioneTicketsFacade implements ITicketsFacade, IItinerarioFacade, IMezzoFacade {
	
	private final ITicketDAO ticketDAO;
	private final IItinerarioDAO itinerarioDAO;
	private final IMezzoDAO mezzoDAO;
	
	public ConcreteGestioneTicketsFacade() {
		ticketDAO=DaoFactory.getTicketDAO();
		itinerarioDAO=DaoFactory.getItinerarioDAO();
		mezzoDAO = DaoFactory.getMezzoDAO();
	}
	
	public ArrayList<Ticket> selectTicketByStatoAndCorriere(Ticket input){
		
		ArrayList<Ticket> lista = new ArrayList<>();

		try {
			lista = ticketDAO.selectByStatoAndCorriere(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei ticket da db");
		}
		return lista;
		
	}
	public boolean insertTicket(Ticket t) {
		
		boolean esito = false;

		try {
			esito = ticketDAO.insertTicket(t);
		} catch (Exception e) {
			System.err.println("Errore inserimento nuovo ticket");
		}
		return esito;
		
	}

	public ArrayList<Itinerario> selectAllItinerario(){
		
		ArrayList<Itinerario> lista = new ArrayList<>();

		try {
			lista = itinerarioDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento degli itinerari presenti a db");
		}
		return lista;
		
	}
	public ArrayList<Itinerario> selectItinerarioByStatoTappa(Itinerario input){
		
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
	public Itinerario selectItinerarioById(Itinerario i) {
		
		Itinerario itinerario = null;

		try {
			itinerario = itinerarioDAO.selectById(i);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento degli itinerari per id presenti a db");
		}
		return itinerario;
		
	}

public ArrayList<Mezzo> selectAllMezzo(){
		
		ArrayList<Mezzo> lista = new ArrayList<>();

		try {
			lista = mezzoDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei mezzi presenti a db");
		}
		return lista;
		
	}

	public ArrayList<Mezzo> selectMezzoByCapienza(Mezzo input){
		
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

	public Mezzo selectMezzoById(Mezzo m) {
		
		Mezzo mezzo = null;

		try {
			mezzo = mezzoDAO.selectById(m);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei mezzi per id presenti a db");
		}
		return mezzo;
		
	}





}
