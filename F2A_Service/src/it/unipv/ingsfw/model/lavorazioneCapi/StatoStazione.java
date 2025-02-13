package it.unipv.ingsfw.model.lavorazioneCapi;

public enum StatoStazione {
	READY("READY"),
	WORKING("WORKING"),
	MAINTENANCE("MAINTENANCE")
    ;

    private final String stato;

    StatoStazione(String stato) {
        this.stato = stato;
    }
    
    @Override
    public String toString() {
        return stato;
    }
}
