package it.unipv.ingsfw.model.lavorazioneCapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.LavaggioDAO;
import it.unipv.ingsfw.model.TipoLavaggio;

public class CatenaLavorazioneDAO implements ICatenaLavorazioneDAO{
	
	private Connection conn;
	
	public CatenaLavorazioneDAO() {
		super();
	}


	@Override
	public ArrayList<CatenaLavorazione> selectAll()
	{
		ArrayList<CatenaLavorazione> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		CatenaLavorazione s;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from CATENELAVORAZIONI";
			rs1=st1.executeQuery(query);
			
			

			while(rs1.next())
			{
				s = new CatenaLavorazione(rs1.getString(1),TipoLavaggio.valueOf(rs1.getString(2)));

				result.add(s);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public ArrayList<CatenaLavorazione> selectByTipoLavaggio(CatenaLavorazione input)
	{
		ArrayList<CatenaLavorazione> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		CatenaLavorazione s;
		
		
		try
		{
			LavaggioDAO lav = new LavaggioDAO();
			HashMap<Integer,String> listaTipiLavaggio = lav.selectAll();
			
			String query="SELECT * FROM CATENELAVORAZIONE WHERE IDLAVAGGIO =" + Integer.parseInt(input.getTipoLavaggio().toString());
			st1 = conn.prepareStatement(query);
			System.out.println(input.getTipoLavaggio().toString());
			
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
		
				s = new CatenaLavorazione(rs1.getString(1),TipoLavaggio.valueOf(listaTipiLavaggio.get(rs1.getInt(2))));

				result.add(s);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	public boolean checkCatenaAlreadyExists(CatenaLavorazione c) {
		
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		String query;
		
		boolean esito=true;
		
		try
		{
			query="SELECT * FROM CATENELAVORAZIONE WHERE IDCATENA = '" + c.getIdCatena() + "'";
			st1 = conn.prepareStatement(query);
			
			st1.executeQuery();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}
	
	@Override
	public boolean insertCatena(CatenaLavorazione c) {
		
		//if(checkCatenaAlreadyExists(c)) {
			
			conn=DBConnection.startConnection(conn);
			PreparedStatement st1;
			String query;
			
			boolean esito=true;
			
			try
			{
				query="INSERT INTO CATENELAVORAZIONE (IDCATENA,IDLAVAGGIO) VALUES (?,?)";
				st1 = conn.prepareStatement(query);
				st1.setString(1,c.getIdCatena());
				st1.setString(2,c.getTipoLavaggio().toString());
				
				st1.executeUpdate();
	
			}catch(SQLIntegrityConstraintViolationException e) {
				System.err.println("Catena già esistente --> Inserimento automatico con codice corretto");
				insertCatena(new CatenaLavorazione(getNewIdCatena(), c.getTipoLavaggio()));
				esito=false;
			}catch (Exception e){
				e.printStackTrace();
				esito=false;
			}
	
			DBConnection.closeConnection(conn);
			return esito;
		//}
		//System.err.println("Catena già esistente");
		//return false;

	}
	
	public ArrayList<ObservableStazioneLavoro> selectStazioniByCatena(CatenaLavorazione input){
		
		ArrayList<ObservableStazioneLavoro> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		ObservableStazioneLavoro s;
		
		
		try
		{
			String query="SELECT * FROM STAZIONILAVORO WHERE IDCATENA ='" + input.getIdCatena() + "'";
			st1 = conn.prepareStatement(query);
			
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
		
				s = new ObservableStazioneLavoro(rs1.getString(1), TipologiaStazione.valueOf(rs1.getString(3)),
						StatoStazione.valueOf(rs1.getString(4)), rs1.getDouble(5));

				result.add(s);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	public String getNewIdCatena() {

		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		String newIdCatena = "";
		
		
		try
		{
			String query="SELECT IDCATENA FROM CATENELAVORAZIONE ORDER BY IDCATENA DESC LIMIT 1";
			st1 = conn.prepareStatement(query);
			
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
		
				newIdCatena = rs1.getString(1);
				String sub = newIdCatena.substring(3);
				//System.out.println(sub);
				int num = Integer.parseInt(sub)+ 1;
				newIdCatena = String.format("CAT%03d",num);
				//System.out.println(newIdCatena);

			}
		}catch (NumberFormatException e){
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return newIdCatena;
	}
	
	public static void main(String[] args) {
		CatenaLavorazioneDAO cl = new CatenaLavorazioneDAO();
		
		/*CatenaLavorazione cl0 = new CatenaLavorazione(null, TipoLavaggio.BIANCHI);
		ArrayList<CatenaLavorazione> listaCatene = cl.selectByTipoLavaggio(cl0);
		
		for(CatenaLavorazione c : listaCatene)
			System.out.println(c);
		
		CatenaLavorazione cl1 = new CatenaLavorazione("CAT001", null);
		ArrayList<ObservableStazioneLavoro> listaStazioni = cl.selectStazioniByCatena(cl1);
		
		for(ObservableStazioneLavoro o : listaStazioni)
			System.out.println(o);
		
		CatenaLavorazione cl2 = new CatenaLavorazione("CAT002", TipoLavaggio.PELLE);
		boolean t = cl.insertCatena(cl2);
		if(t)
			System.out.println(cl2);*/
		
		/*CatenaLavorazione cl2 = new CatenaLavorazione("CAT002", TipoLavaggio.PELLE);
		//System.out.println(cl.checkCatenaAlreadyExists(cl2));
		boolean t = cl.insertCatena(cl2);
		if(t)
			System.out.println(cl2);
		
		CatenaLavorazione cl3 = new CatenaLavorazione("CAT003", TipoLavaggio.COTONE);
		//System.out.println(cl.checkCatenaAlreadyExists(cl2));
		boolean t1 = cl.insertCatena(cl3);
		if(t1)
			System.out.println(cl3);*/
		
		cl.getNewIdCatena();
		
		
	}

}
