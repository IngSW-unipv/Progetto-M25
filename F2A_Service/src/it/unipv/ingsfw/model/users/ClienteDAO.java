package it.unipv.ingsfw.model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;

public class ClienteDAO implements IClienteDAO {
	private Connection conn;
	

	private static ClienteDAO instance = null;

	public ClienteDAO() {
		super();
		// this.schema = "PROVA";
		// conn=DBConnection.startConnection(conn,schema);
	}
	
	public static ClienteDAO getInstance() {
		if (instance == null) {
			instance = new ClienteDAO();
			System.out.println("Create new instance");
		} else
			System.out.println("Instance already available");
		return instance;
	}
	
	@Override
	public ArrayList<Cliente> selectAll() {
		ArrayList<Cliente> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Cliente c;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * from CLIENTI";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				 c = new Cliente (rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6));

				 result.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertCliente(Cliente c) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st2;

		boolean esito = true;

		try {
			String query = "INSERT INTO CLIENTI VALUES (?,?,?,?,?,?)";
			st2 = conn.prepareStatement(query);
			st2.setString(1, c.getIdCliente());
			st2.setString(2, c.getNome());
			st2.setString(3, c.getCognome());
			st2.setString(4, c.getCf());
			st2.setString(5, c.getEmail());
			st2.setString(6, c.getPassword());
			st2.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}	
	
	@Override
	public String selectIdByEmailPassword(Cliente input) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st3;
		ResultSet rs2;
		String id = "";
		
		try {
			String query = "SELECT IDCL FROM CLIENTI WHERE EMAIL=? AND PASSWORD=?";

			st3 = conn.prepareStatement(query);
			st3.setString(1, input.getEmail());
			st3.setString(2, input.getPassword());
			
			rs2 = st3.executeQuery();
			
			while(rs2.next()) {
				id = rs2.getString(1);
			}

		} catch (IndexOutOfBoundsException e1) {
			System.err.println("");
		} catch (Exception e1) {
			System.err.println("Errore in fase di autenticazione");
			e1.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return id;
	}
		
	@Override
	public Cliente selectClienteByEmailEPassword(Cliente input) {
		
		conn = DBConnection.startConnection(conn);
		PreparedStatement st4;
		ResultSet rs3;
		Cliente c = null;

		try {
			String query = "SELECT * FROM CLIENTI WHERE EMAIL=? AND PASSWORD=?";
			
			st4 = conn.prepareStatement(query);
			st4.setString(1, input.getEmail());
			st4.setString(2, input.getPassword());
			
			rs3 = st4.executeQuery();

			while (rs3.next()) {

				 c = new Cliente (rs3.getString(1), rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5),rs3.getString(6));

			}
		} catch (Exception e4) {
			e4.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return c;
	}
	
	@Override
	public String getNewIdCliente() {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st5;
		ResultSet rs4;
		String newIdCliente = "";

		try {
			String query = "SELECT IDCL FROM Clienti ORDER BY IDCL DESC LIMIT 1";
			st5 = conn.prepareStatement(query);

			rs4 = st5.executeQuery(query);

			while (rs4.next()) {

				newIdCliente = rs4.getString(1);
				String sub = newIdCliente.substring(3);
				 System.out.println(sub);
				int num = Integer.parseInt(sub) + 1;
				newIdCliente = String.format("CL%03d", num);
				 System.out.println(newIdCliente);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return newIdCliente;
	}

	
	
	
	public static void main(String[] args) {
		
		ClienteDAO c = new ClienteDAO();
		
		
		//estrazione di tutti i clienti registrati
		
		/*ArrayList<Cliente> c1= d.selectAll();
		 for (Cliente o2 : c1)
		 System.out.println(o2);
		*/
		
		
		//Inserimento nuovo cliente
		
		/*Cliente c2 = new Cliente("CL000", "Stefano", "Rossi", "RSSSTF23Y45R304I", "Stefano.Rossi@f2aservice.com", "Admin");
		System.out.println(c2);
		
        boolean esito = c.insertCliente(c2);
        
        if (esito==true) {
            System.out.println("Cliente inserito correttamente!");
        } else {
            System.out.println("Errore durante l'inserimento del cliente.");
        }
		*/			 
			
		
		//Ricerca idCliente tramite mail e password
		
		/*Cliente c3 = new Cliente("", "", "", "", "Stefano.Rossi@f2aservice.com", "Admin");
		String esito = c.selectIdByEmailPassword(c3);
        System.out.println(esito);
		*/		
		
		
		//Ricerca cliente tramite mail e password
		
		Cliente c4 = new Cliente("", "", "", "", "Stefano.Rossi@f2aservice.com", "Admin");
		
        Cliente Ctrovato = c.selectClienteByEmailEPassword(c4);
        
        System.out.println(Ctrovato);
		
		
		
		//Creazione nuovo IdCliente
		
		/*String nuovoIdCliente = c.getNewIdCliente();
        System.out.println(nuovoIdCliente);
		*/
		
	} 
	

}
