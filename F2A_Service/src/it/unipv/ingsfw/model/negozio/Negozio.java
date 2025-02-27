package it.unipv.ingsfw.model.negozio;

import java.util.ArrayList;
import it.unipv.ingsfw.model.Capo;

public class Negozio extends Tappa {

	private Totem totem;
	
	/**public Negozio(String idTappa, StatoTappa stato, String indirizzo, Totem totem) {
		super(idTappa, stato, indirizzo);
		this.totem = totem;
	}**/
	
	public Negozio(String idTappa, StatoTappa stato, String indirizzo) {
		super(idTappa, stato, indirizzo);
		this.totem = null;
	}
	
	public Negozio(String idTappa) {
		super(idTappa, StatoTappa.NON_ATTRAVERSATA, null);
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
	
    public Totem creaTotem(ArrayList<Capo> listaCapiDepositati) {
        
        Totem totem1 = new Totem(listaCapiDepositati);
        return totem1;
    }
	
}
