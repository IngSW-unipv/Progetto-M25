package it.unipv.ingsfw.model.tickets;
import java.util.ArrayList;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.Tappa;

public class Itinerario {
	//modificato 19/02
	private String idItinerario;
	private ArrayList<Tappa> listaTappeNegozi;
	
	/**
	 * @param idItinerario
	 * @param listaTappeNegozi
	 */
	public Itinerario(String idItinerario, ArrayList<Tappa> listaTappeNegozi) {
		super();
		this.idItinerario = idItinerario;
		this.listaTappeNegozi = listaTappeNegozi;
	}

	//AGGIUNTO IN DATA 19/02
	//Costruttore ALTERNATIVO
	public Itinerario (String idItinerario) {
		super();
		this.idItinerario = idItinerario;
		this.listaTappeNegozi = null;
	}
	
	public String getIdItinerario() {
		return idItinerario;
	}

	public void setIdItinerario(String idItinerario) {
		this.idItinerario = idItinerario;
	}

	public ArrayList<Tappa> getListaTappeNegozi() {
		return listaTappeNegozi;
	}

	public void setListaTappeNegozi(ArrayList<Tappa> listaTappeNegozi) {
		this.listaTappeNegozi = listaTappeNegozi;
	}
	
	//AGGIUNTO IN DATA 19/02
	public Itinerario getItinerarioByID() {
		ItinerarioDAO dao = new ItinerarioDAO();
		Itinerario i = dao.selectById(this);
		return i;
	}
	
	@Override
	public String toString() {
		String itinerario = "Itinerario " + idItinerario + "\nTappe:\n";
		
		for(Tappa n: listaTappeNegozi)
			itinerario += n.toString();
		
		return  itinerario;
		
	}
	
	
	//public Negozio getTappaCorrente() {
	//}

}
