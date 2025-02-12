package it.unipv.ingsfw.model.negozio;

public abstract class Tappa {
	
	private String idTappa;
	private boolean stato;
	private String indirizzo;
	
	
	/**
	 * @param idTappa
	 * @param stato
	 * @param indirizzo
	 */
	public Tappa(String idTappa, boolean stato, String indirizzo) {
		super();
		this.idTappa = idTappa;
		this.stato = stato;
		this.indirizzo = indirizzo;
	}


	public String getIdTappa() {
		return idTappa;
	}


	public void setIdTappa(String idTappa) {
		this.idTappa = idTappa;
	}


	public boolean isStato() {
		return stato;
	}


	public void setStato(boolean stato) {
		this.stato = stato;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
}
