package it.unipv.ingsfw.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazioneDAO;

public class LavaggioDAO implements ILavaggioDAO{

	
	
private Connection conn;
	
	public LavaggioDAO() {
		super();
	}


	@Override
	public HashMap<Integer,String> selectAll()
	{
		HashMap<Integer,String> result = new HashMap<>();

		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT IDLAVAGGIO,DESCRIZIONE from LAVAGGI";
			rs1=st1.executeQuery(query);
			
			

			while(rs1.next())
			{
				
				result.put(rs1.getInt(1), rs1.getString(2));

			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}

}
