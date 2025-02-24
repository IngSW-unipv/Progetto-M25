package it.unipv.ingsfw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
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
import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.users.Cliente;

public class CapoDAO implements ICapoDAO {

	// private String schema;
	private Connection conn;
	private SimpleDateFormat format;

	public CapoDAO() {
		super();
		// this.schema = "PROVA";
		// conn=DBConnection.startConnection(conn,schema);
		format = new SimpleDateFormat("YYYY-MM-DD");
	}

	@Override
	public ArrayList<Capo> selectAll() {
		ArrayList<Capo> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Negozio negDep;
		Negozio negCon;
		Cliente cl;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * from CAPI";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				negDep = new Negozio(rs1.getString(6));
				negCon = new Negozio(rs1.getString(7));
				cl = new Cliente(rs1.getString(9));
				Capo c = new Capo(rs1.getString(1), StatoCapo.valueOf(rs1.getString(2)),
						TipoLavaggio.valueOf(rs1.getString(3)), rs1.getDate(4), rs1.getDate(5), negDep, negCon,
						rs1.getDouble(8), cl);

				result.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<Capo> selectCapoByStatoETipo(Capo input) {
		
		ArrayList<Capo> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Negozio negDep;
		Negozio negCon;
		Cliente cl;

		try {
			String query = "SELECT * FROM CAPI C WHERE C.STATO=? AND C.IDLAVAGGIO =? AND NOT EXISTS (SELECT * FROM LAVORAZIONI L WHERE L.IDC = C.IDC) ORDER BY DATAULTIMACONSEGNA LIMIT 50";

			st1 = conn.prepareStatement(query);
			st1.setString(1, input.getStatoCapo().toString());
			st1.setInt(2, Integer.parseInt(input.getTipoLavaggio().toString()));

			rs1 = st1.executeQuery();

			while (rs1.next()) {
				negDep = new Negozio(rs1.getString(6));
				negCon = new Negozio(rs1.getString(7));
				cl = new Cliente(rs1.getString(9));
				Capo c = new Capo(rs1.getString(1), StatoCapo.valueOf(rs1.getString(2)),
						input.getTipoLavaggio(), rs1.getDate(4), rs1.getDate(5), negDep, negCon,
						rs1.getDouble(8), cl);

				result.add(c);
			}
		} catch (NumberFormatException e) {
			System.err.println("Parsing non andato a buon fine");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public boolean updateStatoCapo(Capo inputSet){
		
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		boolean esito = true;

		try {
			String query = "UPDATE CAPI SET STATO =? WHERE IDC = ?";

			st1 = conn.prepareStatement(query);
			st1.setString(1, inputSet.getStatoCapo().toString());
			st1.setString(2, inputSet.getIdCapo());

			st1.executeUpdate();

		} catch(SQLTimeoutException e) {
			System.err.println("Timeout");
			e.printStackTrace();
			esito = false;
		}catch (NumberFormatException e) {
			System.err.println("Parsing non andato a buon fine");
			e.printStackTrace();
			esito = false;
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}
	@Override
	public boolean updateStatoCapoByTappa(Capo inputSet){
		
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		boolean esito = true;

		try {
			String query = null;
			
			if(inputSet.getNegozioConsegna()==null) {
				query = "UPDATE CAPI SET STATO =? WHERE IDNEGOZIODEPOSITO = ?";
				st1 = conn.prepareStatement(query);
				st1.setString(1, inputSet.getStatoCapo().toString());
				st1.setString(2, inputSet.getNegozioDeposito().getIdTappa());
				st1.executeUpdate();
			}
			else if(inputSet.getNegozioDeposito()==null) {
				query = "UPDATE CAPI SET STATO =? WHERE IDNEGOZIOCONSEGNA = ?";
				st1 = conn.prepareStatement(query);
				st1.setString(1, inputSet.getStatoCapo().toString());
				st1.setString(2, inputSet.getNegozioConsegna().getIdTappa());
				st1.executeUpdate();
			}

		} catch(SQLTimeoutException e) {
			System.err.println("Timeout");
			e.printStackTrace();
			esito = false;
		}catch (NumberFormatException e) {
			System.err.println("Parsing non andato a buon fine");
			e.printStackTrace();
			esito = false;
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}
	@Override
	public String getNewIdCapo() {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		String newIdCapo = "";

		try {
			String query = "SELECT IDC FROM CAPI ORDER BY IDC DESC LIMIT 1";
			st1 = conn.prepareStatement(query);

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				newIdCapo = rs1.getString(1);
				String sub = newIdCapo.substring(1);
				//System.out.println(sub);
				int num = Integer.parseInt(sub) + 1;
				newIdCapo = String.format("C%03d", num);
				//System.out.println(newIdStazione);

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return newIdCapo;
	}

	@Override
	public boolean insertCapo(Capo c) throws ParseException {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		Date parsed;
		java.sql.Date dataBuona;

		boolean esito = true;

		try {
			String query = "INSERT INTO CAPI VALUES(?,?,?,?,?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, c.getIdCapo());
			st1.setString(2, c.getStatoCapo().toString());
			st1.setString(3, c.getTipoLavaggio().toString());
			
			//gestione inserimento data nulla che altrimenti scatenerebbe una eccezione
			
			parsed = c.getDataRitiro();
			if(parsed != null) {
				dataBuona = new java.sql.Date(parsed.getTime());
				st1.setDate(4, dataBuona);
			}else {
				st1.setDate(4, null);
			}
			
			parsed = c.getDataUltimaConsegna();
			dataBuona = new java.sql.Date(parsed.getTime());
			st1.setDate(5, dataBuona);

			st1.setString(6, c.getNegozioDeposito().getIdTappa());
			st1.setString(7, c.getNegozioConsegna().getIdTappa());
			st1.setDouble(8, c.getPrezzoScontato());
			st1.setString(9, c.getCliente().getIdCliente());
			
			
			st1.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	@Override
	public ArrayList<Capo> selectCapiDaRitirareByTappa(Capo input) {
		
		ArrayList<Capo> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		//negozio deposito = negozio ritiro != negozio consegna
		Negozio negDep;
		Cliente cl;

		try {
			String query = "SELECT * FROM CAPI C WHERE C.IDNEGOZIODEPOSITO=? AND C.STATO='IN_STORE'";

			st1 = conn.prepareStatement(query);
			st1.setString(1, input.getNegozioDeposito().getIdTappa());

			rs1 = st1.executeQuery();

			while (rs1.next()) {
				negDep = new Negozio(rs1.getString(6));
				Negozio negCons = new Negozio(rs1.getString(7));
				cl = new Cliente(rs1.getString(9));
				Capo c = new Capo(rs1.getString(1), StatoCapo.valueOf(rs1.getString(2)),
						input.getTipoLavaggio(), rs1.getDate(4), rs1.getDate(5), negDep, negCons,
						rs1.getDouble(8), cl);
				result.add(c);
			}
		} catch (NumberFormatException e) {
			System.err.println("Parsing non andato a buon fine");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public ArrayList<Capo> selectCapiDaConsegnareByTappa(Capo input) {
		
		ArrayList<Capo> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		
		Negozio negCons;
		Cliente cl;

		try {
			String query = "SELECT * FROM CAPI C WHERE C.IDNEGOZIOCONSEGNA=? AND C.STATO='IN_CONSEGNA'";

			st1 = conn.prepareStatement(query);
			st1.setString(1, input.getNegozioConsegna().getIdTappa());
			rs1 = st1.executeQuery();

			while (rs1.next()) {
				Negozio negDep = new Negozio(rs1.getString(6));
				negCons = new Negozio(rs1.getString(7));
				cl = new Cliente(rs1.getString(9));
				Capo c = new Capo(rs1.getString(1), StatoCapo.valueOf(rs1.getString(2)),
						input.getTipoLavaggio(), rs1.getDate(4), rs1.getDate(5), negDep, negCons,
						rs1.getDouble(8), cl);
				result.add(c);
			}
		} catch (NumberFormatException e) {
			System.err.println("Parsing non andato a buon fine");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public String getStatoCapoById(Capo c) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		String stato = null;

		try {
			String query = "SELECT stato FROM Capi WHERE IDC = ?;";
			st1 = conn.prepareStatement(query);
			st1.setString(1, c.getIdCapo());
			rs1 = st1.executeQuery();

            if (rs1.next()) {
                stato = rs1.getString("stato");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return stato;
	}

	
	
	public static void main(String[] args) throws ParseException {
		// da testare inserimento corretto data

		/*CapoDAO c = new CapoDAO();
		ArrayList<Capo> capi = c.selectCapoByStatoETipo(new Capo(StatoCapo.IN_LAVORAZIONE, TipoLavaggio.BIANCHI));

		//for (Capo c0 : capi)
			//System.out.println(c0);
		
		String string = "2025-02-18";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(string);
		java.sql.Date sql = new java.sql.Date(parsed.getTime());
		boolean t = c.insertCapo(new Capo(c.getNewIdCapo(),StatoCapo.IN_STORE, TipoLavaggio.BIANCHI, null, sql, new Negozio("N002"), new Negozio("N003"), 25.6, new Cliente("CL001")));
		System.out.println(t);*/
		
		CapoDAO c =new CapoDAO();
		//String id=c.getNewIdCapo();
		//System.out.println(id);
		
		String Stato =c.getStatoCapoById(new Capo("C013"));
		System.out.println(Stato);
	}

}
