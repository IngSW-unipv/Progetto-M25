package it.unipv.ingsfw.facade.gestioneTicket;

import java.util.ArrayList;

import it.unipv.ingsfw.model.tickets.Itinerario;

public interface IItinerarioFacade {

	public ArrayList<Itinerario> selectAllItinerario();
	public ArrayList<Itinerario> selectItinerarioByStatoTappa(Itinerario fornInput);
	public boolean insertItinerario(Itinerario i);
	public Itinerario selectItinerarioById(Itinerario i);
}
