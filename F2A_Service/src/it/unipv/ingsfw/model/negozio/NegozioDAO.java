package it.unipv.ingsfw.model.negozio;

import java.sql.Connection;

import it.unipv.ingsfw.model.tickets.TicketDAO;

public class NegozioDAO implements INegozioDAO {
	
	Connection connessione;
	private static NegozioDAO instance = null;

	private NegozioDAO() {
		super();
	}
	
	public static NegozioDAO getInstance() {
		if (instance == null) {
			instance = new NegozioDAO();
			System.out.println("Create new instance");
		} else
			System.out.println("Instance already available");
		return instance;
	}

}
