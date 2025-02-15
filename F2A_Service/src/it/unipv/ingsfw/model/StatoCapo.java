package it.unipv.ingsfw.model;

public enum StatoCapo {
	//IN_STORE, RITIRATO, IN_LAVORAZIONE, IN_CONSEGNA, CONSEGNATO, PRELEVATO
	IN_STORE("IN_STORE"),
	RITIRATO("RITIRATO"),
	IN_LAVORAZIONE("IN_LAVORAZIONE"),
	IN_CONSEGNA("IN_CONSEGNA"),
	CONSEGNATO("CONSEGNATO"),
	PRELEVATO("PRELEVATO")
    ;

    private final String stato;

    StatoCapo(String stato) {
        this.stato = stato;
    }
    
    @Override
    public String toString() {
        return stato;
    }
}
