package it.unipv.ingsfw.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import it.unipv.ingsfw.model.users.Cliente;


public class ConnectionTest {
	
	private String schema;
	private Connection conn;
	
	public ConnectionTest() {
		//this.schema = "f2a_service";
		conn=DBConnection.startConnection(conn);
	}
	
	
	public ArrayList<Cliente> selectAll (){
		
		ArrayList<Cliente> result = new ArrayList<Cliente>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		//System.out.println("aaa");

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from clienti ";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Cliente f = new Cliente(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5),rs1.getString(6));

				result.add(f);
				System.out.println(f);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	public static void main(String []args) {
		
		ConnectionTest af=new ConnectionTest();
		af.selectAll();
	}
	
}

