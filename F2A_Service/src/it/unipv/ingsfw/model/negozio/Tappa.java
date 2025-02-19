package it.unipv.ingsfw.model.negozio;

public abstract class Tappa {
	
	private String idTappa;
	private StatoTappa stato;    //di default "NON_ATTRAVERSATA"
	private String indirizzo;
	
	
	/**
	 * @param idTappa
	 * @param stato
	 * @param indirizzo
	 */
	public Tappa(String idTappa, StatoTappa stato, String indirizzo) {
		super();
		this.idTappa = idTappa;
		this.setStato(StatoTappa.NON_ATTRAVERSATA);
		this.indirizzo = indirizzo;
	}


	public String getIdTappa() {
		return idTappa;
	}


	public void setIdTappa(String idTappa) {
		this.idTappa = idTappa;
	}



	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public StatoTappa getStato() {
		return stato;
	}


	public void setStato(StatoTappa stato) {
		this.stato = stato;
	}
}
