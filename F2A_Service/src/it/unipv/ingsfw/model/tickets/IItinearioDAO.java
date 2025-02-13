package it.unipv.ingsfw.model.tickets;

import java.util.ArrayList;

public interface IItinearioDAO {
	
	public ArrayList<Itinerario> selectAll();
	public ArrayList<Itinerario> selectByStatoTappa(Itinerario fornInput);
	public boolean insertItinerario(Itinerario i);

}
