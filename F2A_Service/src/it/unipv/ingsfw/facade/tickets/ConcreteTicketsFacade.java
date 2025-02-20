package it.unipv.ingsfw.facade.tickets;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.tickets.ITicketDAO;
import it.unipv.ingsfw.model.tickets.Ticket;

public class ConcreteTicketsFacade implements ITicketsFacade {
	
	private final ITicketDAO ticketDAO;

	public ConcreteTicketsFacade() {
		ticketDAO=DaoFactory.getTicketDAO();
	}
	
	public ArrayList<Ticket> selectByStatoAndCorriere(Ticket input){
		
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

}
