package it.unipv.ingsfw.model.negozio;
import java.util.ArrayList;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.users.Cliente;

public class Totem {
	
	private boolean statoSportello;
	//private ArrayList<Cliente> listaClienti;
	private ArrayList<Capo> listaCapiDepositati;
	
	
	
	
	// ArrayList<Cliente> listaClienti,  (tolto da costruttore di totem vediamo se serve)
	
	
	
	
	
	/**
	 * @param statoSportello
	 * @param listaClienti
	 * @param listaCapiDepositati
	 */
	public Totem(boolean statoSportello,  ArrayList<Capo> listaCapiDepositati) {
		super();
		this.statoSportello = statoSportello;
		//this.listaClienti = listaClienti;
		this.listaCapiDepositati = listaCapiDepositati;
	}

	public boolean isStatoSportello() {
		return statoSportello;
	}

	public void setStatoSportello(boolean statoSportello) {
		this.statoSportello = statoSportello;
	}

	//public ArrayList<Cliente> getListaClienti() {
	//	return listaClienti;
	//}

	//public void setListaClienti(ArrayList<Cliente> listaClienti) {
	//	this.listaClienti = listaClienti;
	//}

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
	
	// Metodo per creare un oggetto Cliente
    public static Cliente creaCliente(String idCliente, String nome, String cognome, String telefono, String email, String password) {
        Cliente cliente = new Cliente(idCliente, nome, cognome, telefono, email, password);
        return cliente; 
    }
    
    
    //Metodo per creare IdCliente
    public static String creaIdCliente(String nome, String cognome, String telefono) {
        // Verifica che le stringhe abbiano la lunghezza sufficiente per evitare errori
        if (nome.length() < 2 || cognome.length() < 2 || telefono.length() < 5) {
            throw new IllegalArgumentException("Le stringhe non hanno la lunghezza sufficiente.");
        }
        String parte1 = nome.substring(0, 2); // Prendi le prime due lettere della prima stringa
        String parte2 = cognome.substring(cognome.length() - 2);// Prendi le ultime due lettere della seconda stringa
        String parte3 = telefono.length() >= 5 ? telefono.substring(telefono.length() - 5) : telefono;
        // Prendi le ultime 5 cifre della terza stringa
        return parte1 + parte2 + parte3;// Combina le tre parti per creare l'ID
    }
	
	
}
