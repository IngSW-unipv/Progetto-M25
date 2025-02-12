package it.unipv.ingsfw.model.Tickets;

public enum TipologiaTicket {
	//RITIRO, CONSEGNA
	RITIRO("Ritiro"),
	CONSEGNA("Consegna")
    ;

    private final String tipologia;

    TipologiaTicket(String tipologia) {
        this.tipologia = tipologia;
    }
    
    @Override
    public String toString() {
        return tipologia;
    }
}
