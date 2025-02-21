package it.unipv.ingsfw.model.negozio;

import java.sql.Connection;
import java.util.ArrayList;

import it.unipv.ingsfw.model.tickets.TicketDAO;

public class NegozioDAO implements INegozioDAO {
	
	Connection connessione;
	private static NegozioDAO instance = null;

	public NegozioDAO() {
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

	@Override
	public ArrayList<Negozio> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertNegozio(Negozio n) {
		// TODO Auto-generated method stub
		return false;
	}

}
