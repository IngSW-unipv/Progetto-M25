package it.unipv.ingsfw.model.lavorazioneCapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;

public class ObservableStazioneLavoroDAO implements IObservableStazioneLavoroDAO {
	
	//private String schema;
		private Connection conn;


		public ObservableStazioneLavoroDAO() {
			super();
		}


		@Override
		public ArrayList<ObservableStazioneLavoro> selectAll()
		{
			ArrayList<ObservableStazioneLavoro> result = new ArrayList<>();

			conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
			ObservableStazioneLavoro s;

			try
			{
				st1 = conn.createStatement();
				String query="SELECT * from STAZIONILAVORO";
				rs1=st1.executeQuery(query);

				while(rs1.next())
				{
					s = new ObservableStazioneLavoro(rs1.getString(1),TipologiaStazione.valueOf(rs1.getString(2)),StatoStazione.valueOf(rs1.getString(3)), rs1.getDouble(7));

					result.add(s);
				}
			}catch (Exception e){e.printStackTrace();}

			DBConnection.closeConnection(conn);
			return result;
		}
		
		@Override
		public ArrayList<ObservableStazioneLavoro> selectByStato(ObservableStazioneLavoro fornInput)
		{
			ArrayList<ObservableStazioneLavoro> result = new ArrayList<>();

			conn=DBConnection.startConnection(conn);
			PreparedStatement st1;
			ResultSet rs1;
			ObservableStazioneLavoro s;

			try
			{
				String query="SELECT * FROM STAZIONILAVORO WHERE STATO=?";

				st1 = conn.prepareStatement(query);
				st1.setString(1, fornInput.getStatoStazione().toString());
				
				rs1=st1.executeQuery(query);

				while(rs1.next())
				{
			
					s = new ObservableStazioneLavoro(rs1.getString(1),TipologiaStazione.valueOf(rs1.getString(2)),StatoStazione.valueOf(rs1.getString(3)), rs1.getDouble(7));

					result.add(s);
				}
			}catch (Exception e){e.printStackTrace();}

			DBConnection.closeConnection(conn);
			return result;
		}
		
		@Override
		public boolean insertStazione(ObservableStazioneLavoro s) {

			conn=DBConnection.startConnection(conn);
			PreparedStatement st1;
			PreparedStatement st2;
			ResultSet rs2;
			String idCatena = "";
			
			boolean esito=true;
			
			try
			{
				String query="SELECT IDCATENA FROM CATENELAVORAZIONE WHERE IDLAVAGGIO = ? LIMIT 1";
				st2 = conn.prepareStatement(query);
				st2.setString(1,s.getTipo().toString());
				
				rs2 = st2.executeQuery(query);
				
				while(rs2.next())
				{
			
					idCatena = rs2.getString(1);
				}

			}catch (Exception e){
				e.printStackTrace();
				esito=false;
			}
			
			try
			{
				String query="INSERT INTO STAZIONILAVORO (IDSTAZIONE,IDCATENA,TIPO,STATO,LIVELLOPRODOTTOLAVAGGIO) VALUES(?,?,?,?,?)";
				st1 = conn.prepareStatement(query);
				st1.setString(1,s.getIdStazione());
				st1.setString(2,idCatena);
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
