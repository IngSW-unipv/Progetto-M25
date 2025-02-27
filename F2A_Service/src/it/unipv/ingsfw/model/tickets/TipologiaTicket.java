
package it.unipv.ingsfw.model.tickets;
//MODIFICATO IN DATA 19/02
public enum TipologiaTicket {
	//RITIRO, CONSEGNA
	RITIRO("RITIRO"),
	CONSEGNA("CONSEGNA")
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
