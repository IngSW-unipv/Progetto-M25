package it.unipv.ingsfw.model.negozio;

import java.util.ArrayList;
import it.unipv.ingsfw.model.Capo;

public class Negozio extends Tappa {

	public Totem totem;
	
	public Negozio(String idTappa, boolean stato, String indirizzo, Totem totem) {
		super(idTappa, stato, indirizzo);
		this.totem = totem;
	}
	
	public Negozio(String idTappa) {
		super(idTappa, false, null);
		this.totem = null;
	}

	public Totem getTotem() {
		return totem;
	}

	public void setTotem(Totem totem) {
		this.totem = totem;
	}
	
	@Override
	public String toString() {
		return "Negozio " + getIdTappa() + "\nIndirizzo: " + getIndirizzo() + "\n";
	}
	
    public Totem creaTotem(boolean statoSportello, ArrayList<Capo> listaCapiDepositati) {
        
        Totem totem1 = new Totem(statoSportello, listaCapiDepositati);
        return totem1;
    }
	
}
