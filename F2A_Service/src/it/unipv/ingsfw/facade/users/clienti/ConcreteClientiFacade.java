package it.unipv.ingsfw.facade.users.clienti;

import java.util.ArrayList;

import it.unipv.ingsfw.facade.DaoFactory;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.model.users.IClienteDAO;

public class ConcreteClientiFacade implements IClientiFacade {
	
	private final IClienteDAO clienteDAO;

	public ConcreteClientiFacade() {
		clienteDAO = DaoFactory.getClienteDAO();
	}
	
	public ArrayList<Cliente> selectAll(){
		
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
