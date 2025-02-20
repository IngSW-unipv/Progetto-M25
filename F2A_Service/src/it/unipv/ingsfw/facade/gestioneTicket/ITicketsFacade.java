package it.unipv.ingsfw.facade.gestioneTicket;

import java.util.ArrayList;

import it.unipv.ingsfw.model.tickets.Ticket;

public interface ITicketsFacade {
	
	public ArrayList<Ticket> selectTicketByStatoAndCorriere(Ticket fornInput);
	public boolean insertTicket(Ticket t);

}
