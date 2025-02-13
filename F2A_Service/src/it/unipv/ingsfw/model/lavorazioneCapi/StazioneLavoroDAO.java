package it.unipv.ingsfw.model.lavorazioneCapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;

public class StazioneLavoroDAO implements IStazioneLavoroDAO {
	
	//private String schema;
		private Connection conn;


		public StazioneLavoroDAO() {
			super();
		}


		@Override
		public ArrayList<StazioneLavoro> selectAll()
		{
			ArrayList<StazioneLavoro> result = new ArrayList<>();

			conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
			StazioneLavoro s;

			try
			{
				st1 = conn.createStatement();
				String query="SELECT * from STAZIONILAVORO";
				rs1=st1.executeQuery(query);

				while(rs1.next())
				{
					s = new StazioneLavoro(rs1.getString(1),TipologiaStazione.valueOf(rs1.getString(2)),StatoStazione.valueOf(rs1.getString(3)), rs1.getDouble(7));

					result.add(s);
				}
			}catch (Exception e){e.printStackTrace();}

			DBConnection.closeConnection(conn);
			return result;
		}
		
		@Override
		public ArrayList<StazioneLavoro> selectByStato(StazioneLavoro fornInput)
		{
			ArrayList<StazioneLavoro> result = new ArrayList<>();

			conn=DBConnection.startConnection(conn);
			PreparedStatement st1;
			ResultSet rs1;
			StazioneLavoro s;

			try
			{
				String query="SELECT * FROM STAZIONILAVORO WHERE STATO=?";

				st1 = conn.prepareStatement(query);
				st1.setString(1, fornInput.getStatoStazione().toString());
				
				rs1=st1.executeQuery(query);

				while(rs1.next())
				{
			
					s = new StazioneLavoro(rs1.getString(1),TipologiaStazione.valueOf(rs1.getString(2)),StatoStazione.valueOf(rs1.getString(3)), rs1.getDouble(7));

					result.add(s);
				}
			}catch (Exception e){e.printStackTrace();}

			DBConnection.closeConnection(conn);
			return result;
		}
		
		@Override
		public boolean insertStazione(StazioneLavoro s) {

			conn=DBConnection.startConnection(conn);
			PreparedStatement st1;
			
			boolean esito=true;

			try
			{
				String query="INSERT INTO STAZIONILAVORO (IDSTAZIONE,IDCATENA,TIPO,STATO,LIVELLOPRODOTTOLAVAGGIO) VALUES(?,?,?,?,?)";
				st1 = conn.prepareStatement(query);
				st1.setString(1,s.getIdStazione());
				st1.setString(2,null);
				st1.setString(3,s.getTipo().toString());
				st1.setString(4,s.getStatoStazione().toString());
				st1.setDouble(5,s.getLivelloProdottoLavaggio());
				
				st1.executeUpdate(query);

			}catch (Exception e){
				e.printStackTrace();
				esito=false;
			}

			DBConnection.closeConnection(conn);
			return esito;

		}

	}
