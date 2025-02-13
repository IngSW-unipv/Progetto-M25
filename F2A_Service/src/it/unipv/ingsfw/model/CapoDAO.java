package it.unipv.ingsfw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.users.Cliente;

public class CapoDAO implements ICapoDAO {
	
	//private String schema;
	private Connection conn;
	private SimpleDateFormat format;


	public CapoDAO() {
		super();
		//this.schema = "PROVA";
		//conn=DBConnection.startConnection(conn,schema);
		format = new SimpleDateFormat("YYYY-MM-DD");
	}


	@Override
	public ArrayList<Capo> selectAll()
	{
		ArrayList<Capo> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Negozio n;
		Cliente c0;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from CAPI";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				n = new Negozio(rs1.getString(6));
				c0 = new Cliente(rs1.getString(8));
				Capo c = new Capo(rs1.getString(1),StatoCapo.valueOf(rs1.getString(2)),TipoLavaggio.valueOf(rs1.getString(3)), rs1.getDate(4), rs1.getDate(5), n, rs1.getDouble(7), c0);

				result.add(c);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public ArrayList<Capo> selectByStatoCapo(Capo fornInput)
	{
		ArrayList<Capo> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Negozio n;
		Cliente c0;

		try
		{
			String query="SELECT * FROM CAPI WHERE STATO=?";

			st1 = conn.prepareStatement(query);
			st1.setString(1, fornInput.getStatoCapo().toString());
			
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				n = new Negozio(rs1.getString(6));
				c0 = new Cliente(rs1.getString(8));
				Capo c = new Capo(rs1.getString(1),StatoCapo.valueOf(rs1.getString(2)),TipoLavaggio.valueOf(rs1.getString(3)), rs1.getDate(4), rs1.getDate(5), n, rs1.getDouble(7), c0);

				result.add(c);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public boolean insertCapo(Capo c) throws ParseException {

		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		Date parsed;
		java.sql.Date dataBuona;
		
		boolean esito=true;

		try
		{
			String query="INSERT INTO CAPI (IDC,STATO,,DATARITIRO,PREZZOSCONTATO,DATACONSEGNAULTIMA,IDLAVAGGIO,IDNEGOZIOCONSEGNA) VALUES(?,?,?,?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1,c.getIdCapo());
			st1.setString(2,c.getStatoCapo().toString());
			
			parsed = c.getDataRitiro();
			dataBuona = new java.sql.Date(parsed.getTime());
			st1.setDate(3,dataBuona);
			
			st1.setDouble(4,c.getPrezzoScontato());
			
			parsed = c.getDataUltimaConsegna();
			dataBuona = new java.sql.Date(parsed.getTime());
			st1.setDate(5,dataBuona);
			
			st1.setString(6,c.getTipoLavaggio().toString());
			st1.setString(7,c.getNegozioConsegna().getIdTappa());
			
			st1.executeUpdate(query);

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}
	
	public static void main(String[] args) {
		//da testare inserimento corretto data
	}

}
