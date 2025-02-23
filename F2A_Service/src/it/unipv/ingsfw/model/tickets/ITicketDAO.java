package it.unipv.ingsfw.model.tickets;

import java.util.ArrayList;

public interface ITicketDAO {

	public ArrayList<Ticket> selectByStatoAndCorriere(Ticket fornInput);
	public ArrayList<String> selectIDTipoTicketByStatoAndCorriere(Ticket fornInput);
	public boolean insertTicket(Ticket t);
	public Ticket selectTicketById(Ticket ticketFit);
	public boolean updateStatoTicket(Ticket ticket);
}
