package it.unipv.ingsfw.model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;

public class DipendenteDAO implements IDipendenteDAO {
	
	private Connection conn;


	public DipendenteDAO() {
		super();
		//this.schema = "PROVA";
		//conn=DBConnection.startConnection(conn,schema);
	}


	@Override
	public ArrayList<Dipendente> selectAll()
	{
		ArrayList<Dipendente> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Dipendente d;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from CAPI";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				
				//d = new (rs1.getString(1));

				//result.add(c);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public ArrayList<Dipendente> selectById(Dipendente fornInput)
	{
		ArrayList<Dipendente> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Dipendente d;

		try
		{
			//TIPO DIPENDENTE --> CORRIERE O OPERATORE
			String query="SELECT * FROM DIPENDENTI WHERE TIPO=?";

			st1 = conn.prepareStatement(query);
			//st1.setString(1, fornInput.getStatoCapo().toString());
			
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				
				//d = new (rs1.getString(1));

				//result.add(d);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public boolean insertDipendente(Dipendente d) {

		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		
		boolean esito=true;

		try
		{
			String query="INSERT INTO DIPENDENTI () VALUES(?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1,d.getIdDipendente());
			
			
			st1.executeUpdate(query);

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}
}
