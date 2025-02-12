package it.unipv.ingsfw.model.Tickets;
import java.util.ArrayList;
import it.unipv.ingsfw.model.negozio.Negozio;

public class Itinerario {
	
	private String idItinerario;
	private ArrayList<Negozio> listaTappeNegozi;
	
	/**
	 * @param idItinerario
	 * @param listaTappeNegozi
	 */
	public Itinerario(String idItinerario, ArrayList<Negozio> listaTappeNegozi) {
		super();
		this.idItinerario = idItinerario;
		this.listaTappeNegozi = listaTappeNegozi;
	}

	public String getIdItinerario() {
		return idItinerario;
	}

	public void setIdItinerario(String idItinerario) {
		this.idItinerario = idItinerario;
	}

	public ArrayList<Negozio> getListaTappeNegozi() {
		return listaTappeNegozi;
	}

	public void setListaTappeNegozi(ArrayList<Negozio> listaTappeNegozi) {
		this.listaTappeNegozi = listaTappeNegozi;
	}
	
	@Override
	public String toString() {
		String itinerario = "Itinerario " + idItinerario + "\nTappe:\n";
		
		for(Negozio n: listaTappeNegozi)
			itinerario += n.toString();
		
		return  itinerario;
		
	}
	
	
	//public Negozio getTappaCorrente() {
	//}

}
