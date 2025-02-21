package it.unipv.ingsfw.facade.gestioneNegozio;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.negozio.INegozioDAO;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.model.users.IClienteDAO;

//facade per macro caso d'uso di Andrea Daglio
public class ConcreteGestioneNegozioFacade implements INegozioFacade,IClientiFacade {
	
	private final INegozioDAO negozioDAO;
	private final IClienteDAO clienteDAO;

	public ConcreteGestioneNegozioFacade() {
		negozioDAO = DaoFactory.getNegozioDAO();
		clienteDAO = DaoFactory.getClienteDAO();
	}
	
	public ArrayList<Negozio> selectAllNegozio(){
		
		ArrayList<Negozio> lista = new ArrayList<>();

		try {
			lista = negozioDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei negozi presenti a db");
		}
		return lista;
		
	}
	public boolean insertNegozio(Negozio n) {
		
		boolean esito = false;

		try {
			esito = negozioDAO.insertNegozio(n);
		} catch (Exception e) {
			System.err.println("Errore inserimento nuovo negozio");
		}
		return esito;
		
	}
	
	public ArrayList<Cliente> selectAllCliente(){
		
		ArrayList<Cliente> lista = new ArrayList<>();

		try {
			lista = clienteDAO.selectAll();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei clienti presenti a db");
		}
		return lista;
		
	}
	public Cliente selectClienteByEmailEPassword(Cliente input) {
		
		Cliente cliente = null;

		try {
			cliente = clienteDAO.selectClienteByEmailEPassword(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dei clienti presenti a db");
		}
		return cliente;
		
	}
	public String selectIdClienteByEmailPassword(Cliente input) {
		
		String id = "";

		try {
			id = clienteDAO.selectIdByEmailPassword(input);
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento dell'id");
		}
		return id;
		
	}
	public boolean insertCliente(Cliente c) {
		
		boolean check = false;

		try {
			check = clienteDAO.insertCliente(c);
		} catch (Exception e) {
			System.err.println("Errore nell'inserimento");
		}
		return check;
		
	}
	
	public String getNewIdCliente() {
		
		String id = "";

		try {
			id = clienteDAO.getNewIdCliente();
		} catch (Exception e) {
			System.err.println("Errore nel prelevamento del nuovo id");
		}
		return id;
	}

}
