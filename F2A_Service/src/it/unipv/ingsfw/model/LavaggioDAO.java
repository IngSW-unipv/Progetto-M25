package it.unipv.ingsfw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import it.unipv.ingsfw.jdbc.DBConnection;

public class LavaggioDAO implements ILavaggioDAO {

	private Connection conn;

	public LavaggioDAO() {
		super();
	}

	@Override
	public HashMap<Integer, String> selectAll() {
		HashMap<Integer, String> result = new HashMap<>();

		conn = DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT IDLAVAGGIO,DESCRIZIONE from LAVAGGI";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				result.put(rs1.getInt(1), rs1.getString(2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	

	
	@Override
	public int getCostoLavaggio(String descrizione) {
		conn = DBConnection.startConnection(conn);
		PreparedStatement st3;
		ResultSet rs3;
		int c=0;
		
		try {
			String query = "SELECT COSTO FROM LAVAGGI WHERE DESCRIZIONE=?";

			st3 = conn.prepareStatement(query);
			st3.setString(1, descrizione);
			
			
			rs3 = st3.executeQuery();
			
			while(rs3.next()) {
				c =rs3.getInt(1);
			}

		} catch (SQLException e)
	        {e.printStackTrace();}
	        
		DBConnection.closeConnection(conn);
		return c;
		
	}
	
	@Override
	public int getCostoLavaggio(int idLavaggio) {
		conn = DBConnection.startConnection(conn);
		PreparedStatement st3;
		ResultSet rs3;
		int c=0;
		
		try {
			String query = "SELECT COSTO FROM LAVAGGI WHERE IDLAVAGGIO=?";

			st3 = conn.prepareStatement(query);
			st3.setInt(1, idLavaggio);
			
			
			rs3 = st3.executeQuery();
			
			while(rs3.next()) {
				c =rs3.getInt(1);
			}

		} catch (SQLException e)
	        {e.printStackTrace();}
	        
		DBConnection.closeConnection(conn);
		return c;
		
	}
	
	
	public static void main(String[] args) {
		
		LavaggioDAO l = new LavaggioDAO();
		
		
		//estrazione di tutti i costi per tipo lavaggio
		
		/*HashMap<String,Integer> l1= l.selectCosto();

	        // Stampa i risultati
	       if (l1 != null && !l1.isEmpty()) {
	            for (String descrizione : l1.keySet()) {
	                Integer costo = l1.get(descrizione);
	                System.out.println(descrizione + " - " + costo);
	            }
	        } else {
	            System.out.println("Nessun dato trovato.");
	        }*/
	    
	
	
	    //estrazione costo tramite descrizone lavaggio
		
	        /*String descrizione = "Lavaggio completo";
	        int costo = l.getCostoLavaggio(descrizione);
	        
	        System.out.println(costo);
	        */
		
		
	    //estrazione costo tramite descrizone lavaggio
		
        /*int id = 2;
        int costo = l.getCostoLavaggio(id);
        
        System.out.println(costo);
        */
		
		
		
		
	    }
}
