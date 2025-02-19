package it.unipv.ingsfw.model.negozio;

public enum StatoTappa {
	
	//CREATA IN DATA 19/02/2025
	ATTRAVERSATA("ATTRAVERSATA"),
	NON_ATTRAVERSATA("NON_ATTRAVERSATA");

	private final String stato;

	StatoTappa(String stato) {
		this.stato = stato;
	}
	
	 @Override
	 public String toString() {
		 return stato;
	 }
}
