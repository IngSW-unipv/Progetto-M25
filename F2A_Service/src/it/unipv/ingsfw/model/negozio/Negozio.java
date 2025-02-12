package it.unipv.ingsfw.model.negozio;

public class Negozio extends Tappa {

	public Totem totem;
	
	public Negozio(String idTappa, boolean stato, String indirizzo, Totem totem) {
		super(idTappa, stato, indirizzo);
		this.totem = totem;
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
	
}
