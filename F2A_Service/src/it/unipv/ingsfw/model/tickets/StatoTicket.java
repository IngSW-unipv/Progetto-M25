package it.unipv.ingsfw.model.tickets;

public enum StatoTicket {
	//ASSEGNATO, PRESO_IN_CARICO, COMPLETATO
	ASSEGNATO("Assegnato"),
	PRESO_IN_CARICO("Preso in carico"),
	COMPLETATO("Completato")
    ;

    private final String tipologia;

    StatoTicket(String tipologia) {
        this.tipologia = tipologia;
    }
    
    @Override
    public String toString() {
        return tipologia;
    }
}
