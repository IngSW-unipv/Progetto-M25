package it.unipv.ingsfw.model.Tickets;

public class Mezzo {
	
	private String idMezzo;
	private int capienza;
	private boolean disponibilita;
	
	/**
	 * @param idMezzo
	 * @param capienza
	 * @param disponibilita
	 */
	public Mezzo(String idMezzo, int capienza, boolean disponibilita) {
		super();
		this.idMezzo = idMezzo;
		this.capienza = capienza;
		this.disponibilita = disponibilita;
	}

	public String getIdMezzo() {
		return idMezzo;
	}

	public void setIdMezzo(String idMezzo) {
		this.idMezzo = idMezzo;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	@Override
	public String toString() {
		return  "Mezzo " + idMezzo + "\nCapienza: " + capienza + "\nDisponibilità: " + disponibilita;
	}
	
}
