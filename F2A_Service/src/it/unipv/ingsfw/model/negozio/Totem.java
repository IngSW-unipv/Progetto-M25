package it.unipv.ingsfw.model.negozio;
import java.util.ArrayList;

import javax.swing.JFrame;

import it.unipv.ingsfw.model.Capo;
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
	
	// Metodo per creare un oggetto Cliente
    public Cliente creaCliente(Cliente c) {
        Cliente cl = new Cliente();
        return cl; 
    }
    
    
    
	/*public static void main(String[] args) {
		Totem t = new Totem();
		c=(String idCliente, String nome, String cognome, String cf, String email, String password);
		Cliente cl= t.creaCliente(c);
		System.out.println(cl);
	}
    */

	
}
