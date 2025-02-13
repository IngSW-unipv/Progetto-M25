package it.unipv.ingsfw.model.lavorazioneCapi;

public enum TipologiaStazione {
	LAVAGGIO("LAVAGGIO"),
	STIRATURA("STIRATURA"),
	ASCIUGATURA("ASCIUGATURA")
    ;

    private final String tipo;

    TipologiaStazione(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return tipo;
    }
}
