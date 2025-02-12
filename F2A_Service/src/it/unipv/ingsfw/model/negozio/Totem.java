package it.unipv.ingsfw.model.negozio;
import java.util.ArrayList;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.users.Cliente;

public class Totem {
	
	private boolean statoSportello;
	private ArrayList<Cliente> listaClienti;
	private ArrayList<Capo> listaCapiDepositati;
	
	/**
	 * @param statoSportello
	 * @param listaClienti
	 * @param listaCapiDepositati
	 */
	public Totem(boolean statoSportello, ArrayList<Cliente> listaClienti, ArrayList<Capo> listaCapiDepositati) {
		super();
		this.statoSportello = statoSportello;
		this.listaClienti = listaClienti;
		this.listaCapiDepositati = listaCapiDepositati;
	}

	public boolean isStatoSportello() {
		return statoSportello;
	}

	public void setStatoSportello(boolean statoSportello) {
		this.statoSportello = statoSportello;
	}

	public ArrayList<Cliente> getListaClienti() {
		return listaClienti;
	}

	public void setListaClienti(ArrayList<Cliente> listaClienti) {
		this.listaClienti = listaClienti;
	}

	public ArrayList<Capo> getListaCapiDepositati() {
		return listaCapiDepositati;
	}

	public void setListaCapiDepositati(ArrayList<Capo> listaCapiDepositati) {
		this.listaCapiDepositati = listaCapiDepositati;
	}
	
	@Override
	public String toString() {
		String totem = "Totem\nStatoSportello " + statoSportello;
		
		for(Capo c : listaCapiDepositati)
			totem += c.toString();
		return totem;
	}
	
	
}
