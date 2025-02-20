package it.unipv.ingsfw.facade.tickets;

import java.util.ArrayList;

import it.unipv.ingsfw.model.tickets.Ticket;

public interface ITicketsFacade {
	
	public ArrayList<Ticket> selectByStatoAndCorriere(Ticket fornInput);
	public boolean insertTicket(Ticket t);

}
