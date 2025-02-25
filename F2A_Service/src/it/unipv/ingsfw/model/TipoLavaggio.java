package it.unipv.ingsfw.model;

public enum TipoLavaggio {
	//BIANCHI, COLORATI, SINTETICI, COTONE, LANA, PELLE, PIUMINI, DELICATI
	BIANCHI("1"),
	COLORATI("2"),
	SINTETICI("3"),
	COTONE("4"),
	LANA("5"),
	PELLE("6"),
	PIUMINI("7"),
	DELICATI("8")
    ;

    private final String tipoLav;

    TipoLavaggio(String tipoLav) {
        this.tipoLav = tipoLav;
    }
    
    public String getTipoLav() {
		return tipoLav;
	}

	public static TipoLavaggio fromTipoLav(String tipoLavaggio) {
        for (TipoLavaggio tipo : values()) {
            if (tipo.getTipoLav().equalsIgnoreCase(tipoLavaggio)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo lavaggio non valido: " + tipoLavaggio);
    }
    
    @Override
    public String toString() {
        return tipoLav;
    }
}
