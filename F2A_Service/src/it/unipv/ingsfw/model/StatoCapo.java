package it.unipv.ingsfw.model;

public enum StatoCapo {
	//IN_STORE, RITIRATO, IN_LAVORAZIONE, IN_CONSEGNA, CONSEGNATO, PRELEVATO
	IN_STORE("In Store"),
	RITIRATO("Ritirato"),
	IN_LAVORAZIONE("In Lavorazione"),
	IN_CONSEGNA("In consegna"),
	CONSEGNATO("Consegnato"),
	PRELEVATO("Prelevato")
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
