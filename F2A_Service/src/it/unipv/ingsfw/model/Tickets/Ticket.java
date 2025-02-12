package it.unipv.ingsfw.model.Tickets;

public class Ticket {
	
	private String idTicket;
	private TipologiaTicket tipologia;
	private StatoTicket stato;
	
	/**
	 * @param idTicket
	 * @param tipologia
	 * @param stato
	 */
	public Ticket(String idTicket, TipologiaTicket tipologia, StatoTicket stato) {
		super();
		this.idTicket = idTicket;
		this.tipologia = tipologia;
		this.stato = stato;
	}

	public String getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(String idTicket) {
		this.idTicket = idTicket;
	}

	public TipologiaTicket getTipologia() {
		return tipologia;
	}

	public void setTipologia(TipologiaTicket tipologia) {
		this.tipologia = tipologia;
	}

	public StatoTicket getStato() {
		return stato;
	}

	public void setStato(StatoTicket stato) {
		this.stato = stato;
	}
	
	@Override
	public String toString() {
		return  "\nTicket " + idTicket + "\nTipologia: " + tipologia + "\nStato: " + stato;
	}
	
}
