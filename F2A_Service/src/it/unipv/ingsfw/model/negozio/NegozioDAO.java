package it.unipv.ingsfw.model.negozio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.tickets.TicketDAO;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.model.users.ClienteDAO;

public class NegozioDAO implements INegozioDAO {
	private Connection conn;
	
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
		ArrayList<Negozio> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Negozio n;

		try {
			st1 = conn.createStatement();
			String query = "SELECT IDN, INDIRIZZO from NEGOZI";
			
			
			rs1 = st1.executeQuery(query);
			while (rs1.next()) {

				 n = new Negozio (rs1.getString(1),null,rs1.getString(2));

				 result.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;	
	}
	

	@Override
	public boolean insertNegozio(Negozio n) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	public static void main(String[] args) {
		
		NegozioDAO n = new NegozioDAO();
	//estrazione id, indirizzo di tutti i negozi
	
	ArrayList<Negozio> n1= n.selectAll();
	 for (Negozio o1 : n1)
	 System.out.println(o1);
	}
	*/
}
