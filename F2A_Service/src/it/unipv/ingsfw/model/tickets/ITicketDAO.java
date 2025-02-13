package it.unipv.ingsfw.model.tickets;

import java.util.ArrayList;

public interface ITicketDAO {
	
	public ArrayList<Ticket> selectAll();
	public ArrayList<Ticket> selectByStato(Ticket fornInput);
	public boolean insertTicket(Ticket t);
}
