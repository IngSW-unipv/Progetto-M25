package it.unipv.ingsfw.model.users;

public enum TipoOperatore {
	//MANUTENTORE, RESPONSABILE_STAZIONE
	MANUTENTORE("Manutentore"),
	RESPONSABILE_STAZIONE("Responsabile stazione")
    ;

    private final String tipologia;

    TipoOperatore(String tipologia) {
        this.tipologia = tipologia;
    }
    
    @Override
    public String toString() {
        return tipologia;
    }
}
