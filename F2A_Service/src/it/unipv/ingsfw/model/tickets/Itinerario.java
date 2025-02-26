package it.unipv.ingsfw.model.tickets;

import java.util.ArrayList;
import java.util.Iterator;

import it.unipv.ingsfw.model.negozio.StatoTappa;
import it.unipv.ingsfw.model.negozio.Tappa;

public class Itinerario {
	// modificato 19/02
	private String idItinerario;
	private ArrayList<Tappa> listaTappeNegozi;
	private ArrayList<Tappa> listaTappeNonAttraversate;

	/**
	 * @param idItinerario
	 * @param listaTappeNegozi
	 */
	public Itinerario(String idItinerario, ArrayList<Tappa> listaTappeNegozi) {
		super();
		this.idItinerario = idItinerario;
		this.listaTappeNegozi = listaTappeNegozi;
		this.listaTappeNonAttraversate = listaTappeNegozi;
		// ogni volta che creo un istanza di itinerario, tutte le tappe ad esso
		// associate vengono poste a "NON_ATTRAVERSATA"
		setListaTappeNonAttraversate(listaTappeNegozi);
	}

	public ArrayList<Tappa> getListaTappeNonAttraversate() {
		return listaTappeNonAttraversate;
	}

	public void setListaTappeNonAttraversate(ArrayList<Tappa> listaTappeNonAttraversate) {
		for (Tappa tappa : listaTappeNonAttraversate) {
			tappa.setStato(StatoTappa.NON_ATTRAVERSATA);
		}
		this.listaTappeNonAttraversate = listaTappeNonAttraversate;
	}

	// AGGIUNTO IN DATA 19/02
	// Costruttore ALTERNATIVO
	public Itinerario(String idItinerario) {
		super();
		this.idItinerario = idItinerario;
		this.listaTappeNegozi = null;
	}

	public Itinerario() {
		// TODO Auto-generated constructor stub
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

	// AGGIUNTO IN DATA 19/02
	public Itinerario getItinerarioByID() {
		ItinerarioDAO dao = new ItinerarioDAO();
		Itinerario i = dao.selectById(this);
		return i;
	}


	@Override
	public String toString() {
		String itinerario = "\n ID Itinerario: " + idItinerario + "\n TAPPE:";
		int i = 1;
		//sistemato in fase di test aggiunto controllo su null
		if (listaTappeNegozi==null) {
			return itinerario;
		}
		for (Tappa n : listaTappeNegozi) {
			itinerario += "\n-Tappa " + i + ": " + n.toString();
			i++;
		}
		return itinerario;
	}

	public Tappa getTappaCorrente() {
		Iterator<Tappa> iteratore = listaTappeNonAttraversate.iterator();
		Tappa t = null;
		while (iteratore.hasNext()) {
			t = iteratore.next();
			if (t.getStato().toString().equalsIgnoreCase("NON_ATTRAVERSATA")) {
				break;
			} else {
				// se la tappa è stata attraversata la rimuove dall'array, e riprende
				iteratore.remove();
				System.out.println(listaTappeNonAttraversate.size());
			}
			System.out.println(t);
		}
		return t;

	}

}
