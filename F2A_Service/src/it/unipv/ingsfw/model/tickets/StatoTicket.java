package it.unipv.ingsfw.model.tickets;

public enum StatoTicket {
	//ASSEGNATO, PRESO_IN_CARICO, COMPLETATO
	//AGGIUNTO IN DATA 19/02
	ASSEGNATO("ASSEGNATO"),
	PRESO_IN_CARICO("PRESA_IN_CARICO"),
	COMPLETATO("COMPLETATO");

    private final String tipologia;

    StatoTicket(String tipologia) {
        this.tipologia = tipologia;
    }
    
    @Override
    public String toString() {
        return tipologia;
    }
}
