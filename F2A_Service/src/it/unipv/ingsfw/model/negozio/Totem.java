package it.unipv.ingsfw.model.negozio;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.view.cliente.ClienteFrameRegLog;

public class Totem {
	
	private boolean statoSportello;
	private Cliente cliente;
	private ArrayList<Capo> listaCapiDepositati;
	
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
	
	public Totem() {
		
	}
	public boolean isStatoSportello() {
		return statoSportello;
	}

	public void setStatoSportello(boolean statoSportello) {
		this.statoSportello = statoSportello;
	}

	public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
	
    
    
	//verificaCredenzialiAccesso true se credenziali esistono (esiste Cliente)
	public boolean verificaCredenzialiAccesso() {
		Cliente c = F2aFacade.getInstance().getGestioneNegozioFacade().selectClienteByEmailEPassword(this.cliente);
		if(c!=null) 
			return true;
		return false;	
	}
	
	public ArrayList<Negozio> getNegoziAttivi(){	
		ArrayList<Negozio> n= F2aFacade.getInstance().getGestioneNegozioFacade().selectAllNegozio();
		return n;
	}
	
	public HashMap<Integer, String> getTipologiaLavaggi(){
		HashMap<Integer, String> lav = F2aFacade.getInstance().getLavaggioFacade().selectAll();
				return lav;
	}
	public boolean generaPrenotazioneCapo(Capo c) throws ParseException {
		boolean esito=F2aFacade.getInstance().getCapoFacade().insertCapo(c);
		return esito;
	}

    
    /*
	public static void main(String[] args) {
		Totem t = new Totem();
		Cliente cl= t.creaCliente("id", "del", "mio", "test", "sperando", "funzioni");
		System.out.println(cl);
	}*/
	
	/*
	public static void main(String[] args) {
		Totem t = new Totem();
		Cliente cl= t.creaClienteFittizio("sperando", "funzioni");
		System.out.println(cl);
	}*/
	
	
	/*
	public static void main(String[] args) {
		Totem t = new Totem();
		ArrayList<Negozio> n1 = t.getNegoziAttivi();
		System.out.println(n1);
	}*/
	
	
	
	/*
	public static void main(String[] args) {
		Totem t = new Totem();
		HashMap<Integer, String> lav =t.getTipologiaLavaggi();
		System.out.println(lav);
	}
	*/
	
	
	/*public static void main(String[] args) {

//test verificaCredenzialiAccesso
 
		
		
        // Dati di test (email e password da verificare)
        String email = "Stefano.Rossi@f2aservice.com";
        String password = "Admin";
        Totem t = new Totem();
        //Cliente cl= new Cliente(email, password);
        // Verifica le credenziali
        boolean risultato = t.verificaCredenzialiAccesso(email, password);

        // Output del risultato
        if (risultato) {
            System.out.println("Credenziali corrette. Accesso consentito.");
        } else {
            System.out.println("Credenziali errate. Accesso negato.");
        }
    }*/
	
    
}
	

