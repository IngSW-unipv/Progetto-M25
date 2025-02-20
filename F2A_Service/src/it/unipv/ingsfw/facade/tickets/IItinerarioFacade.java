package it.unipv.ingsfw.facade.tickets;

import java.util.ArrayList;

import it.unipv.ingsfw.model.tickets.Itinerario;

public interface IItinerarioFacade {

	public ArrayList<Itinerario> selectAll();
	public ArrayList<Itinerario> selectByStatoTappa(Itinerario fornInput);
	public boolean insertItinerario(Itinerario i);
	public Itinerario selectById(Itinerario i);
}
