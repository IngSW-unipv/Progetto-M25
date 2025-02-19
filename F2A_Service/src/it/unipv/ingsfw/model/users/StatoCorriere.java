package it.unipv.ingsfw.model.users;

public enum StatoCorriere {
	//LIBERO, OCCUPATO
	LIBERO("LIBERO"),
	OCCUPATO("OCCUPATO")
    ;

    private final String tipologia;

    StatoCorriere(String tipologia) {
        this.tipologia = tipologia;
    }
    
    @Override
    public String toString() {
        return tipologia;
    }
}
