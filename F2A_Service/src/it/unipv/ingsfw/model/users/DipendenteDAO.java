package it.unipv.ingsfw.model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;

public class DipendenteDAO implements IDipendenteDAO {

	private Connection conn;

	public DipendenteDAO() {
		super();
		// this.schema = "PROVA";
		// conn=DBConnection.startConnection(conn,schema);
	}

	@Override
	public ArrayList<Dipendente> selectAll() {
		ArrayList<Dipendente> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Dipendente d;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * from DIPENDENTI";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				// d = new (rs1.getString(1));

				// result.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<Corriere> selectCorrieri() {
		ArrayList<Corriere> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		PreparedStatement st2;
		ResultSet rs2;
		Corriere c;

		try {

			String query = "SELECT * FROM DIPENDENTI WHERE TIPO='CORRIERE'";

			st1 = conn.prepareStatement(query);
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				c = new Corriere(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
						rs1.getString(5), rs1.getString(6), rs1.getInt(8));
				//MODIFICATO 18/02

				//controllo quali corrieri hanno almeno un ticket non completato
				/**String query2 = "SELECT * FROM DIPENDENTI D WHERE D.IDDIPENDENTE = '" + rs1.getString(1)
						+ "' AND EXISTS (SELECT * FROM TICKET T WHERE D.IDDIPENDENTE = T.IDDIPENDENTE AND T.STATO <> 'COMPLETATO')";

				st2 = conn.prepareStatement(query2);
				rs2 = st2.executeQuery(query2); 

				while (rs2.next()) **/

					//c.setStatoCorriere(StatoCorriere.OCCUPATO);

				result.add(c);
			}

		} catch (ClassCastException e) {
			System.out.println("Errore in fase di casting del dipendente");
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	//uso corriere fittizio per passare l'id del corriere
	//AGGIUNTO IN DATA 18/02
	
	@Override
	public Corriere selectCorriereById(Corriere cF) {
		String IdDip = cF.getIdDipendente();
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;

		try {

			String query = "SELECT * FROM DIPENDENTI WHERE TIPO='CORRIERE' and IdDipendente='"+IdDip+"'";

			st1 = conn.prepareStatement(query);
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				cF = new Corriere(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
						rs1.getString(5), rs1.getString(6), rs1.getInt(8));
			}

		} catch (ClassCastException e) {
			System.out.println("Errore in fase di casting del dipendente");
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return cF;
	}

	@Override
	public ArrayList<Corriere> selectCorrieriLiberi() {
		ArrayList<Corriere> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Corriere c;

		try {

			String query = "SELECT * FROM DIPENDENTE D WHERE D.TIPO = 'CORRIERE' AND NOT EXISTS (SELECT * FROM TICKET T WHERE D.IDDIPENDENTE = T.IDDIPENDENTE AND S.STATO <> 'COMPLETATO');";
			st1 = conn.prepareStatement(query);
			rs1 = st1.executeQuery(query);
			while (rs1.next()) {
				c = new Corriere(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
						rs1.getString(5), rs1.getString(6), rs1.getInt(8), StatoCorriere.LIBERO);
				result.add(c);
			}

		} catch (ClassCastException e) {
			System.out.println("Errore in fase di casting del dipendente");
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<Operatore> selectResponsabiliStazioneNonAssegnati() {

		ArrayList<Operatore> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Operatore o;

		try {

			String query = "SELECT * FROM DIPENDENTI D WHERE D.TIPO='OPERATORE' AND D.TIPOOPERATORE='RESPONSABILE_STAZIONE' AND NOT EXISTS (SELECT * FROM ASSEGNAZIONI A WHERE A.IDDIPENDENTE = D.IDDIPENDENTE AND A.DATAFINEASSEGNAZIONE IS NULL)";
			st1 = conn.prepareStatement(query);
			rs1 = st1.executeQuery(query);
			while (rs1.next()) {
				o = new Operatore(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
						rs1.getString(5), rs1.getString(6), rs1.getInt(8), TipoOperatore.valueOf(rs1.getString(9)));
				result.add(o);
			}

		} catch(IndexOutOfBoundsException e) {
			System.err.println("Nessun responsabile libero");
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public ArrayList<Operatore> selectManutentoriNonAssegnati() {

		ArrayList<Operatore> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Operatore o;

		try {

			String query = "SELECT * FROM DIPENDENTI D WHERE D.TIPO='OPERATORE' AND D.TIPOOPERATORE='MANUTENTORE' AND NOT EXISTS (SELECT * FROM ASSEGNAZIONI A WHERE A.IDDIPENDENTE = D.IDDIPENDENTE AND A.DATAFINEASSEGNAZIONE IS NULL)";
			st1 = conn.prepareStatement(query);
			rs1 = st1.executeQuery(query);
			while (rs1.next()) {
				o = new Operatore(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
						rs1.getString(5), rs1.getString(6), rs1.getInt(8), TipoOperatore.valueOf(rs1.getString(9)));
				result.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public ArrayList<Operatore> selectByTipoOperatore(Operatore input) {
		ArrayList<Operatore> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Operatore o;

		try {
			// TIPO_OPERATORE --> MANUTENTORE O RESPONSABILE_STAZIONE
			String query = "SELECT * FROM DIPENDENTI WHERE TIPOOPERATORE='" + input.getTipoOperatore() + "'";

			st1 = conn.prepareStatement(query);
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				o = new Operatore(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
						rs1.getString(5), rs1.getString(6), rs1.getInt(8), TipoOperatore.valueOf(rs1.getString(9)));

				result.add(o);
			}

		} catch (ClassCastException e) {
			System.err.println("Errore in fase di casting del dipendente");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	@Override
	public String getNewIdDipendente() {
		
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		String newIdDipendente = "";

		try {
			String query = "SELECT IDDIPENDENTE FROM DIPENDENTI ORDER BY IDDIPENDENTE DESC LIMIT 1";
			st1 = conn.prepareStatement(query);

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				newIdDipendente = rs1.getString(1);
				String sub = newIdDipendente.substring(3);
				//System.out.println(sub);
				int num = Integer.parseInt(sub) + 1;
				newIdDipendente = String.format("D%03d", num);
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
		return newIdDipendente;
	}

	@Override
	public boolean insertDipendente(Dipendente d) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO DIPENDENTI VALUES(?,?,?,?,?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, d.getIdDipendente());
			st1.setString(2, d.getNome());
			st1.setString(3, d.getCognome());
			st1.setString(4, d.getCf());
			st1.setString(5, d.getEmail());
			st1.setString(6, d.getPassword());
			st1.setInt(8, d.getStipendio());

			try {

				Operatore o = (Operatore) d;
				st1.setString(7, "OPERATORE"); // tipo
				st1.setString(9, o.getTipoOperatore().toString()); // tipooperatore
				st1.executeUpdate();

			} catch (ClassCastException e) {
				System.err.println("Errore in fase di casting del dipendente");
				Corriere c = (Corriere) d;
				st1.setString(7, "CORRIERE"); // tipo
				st1.setString(9, null); // tipooperatore
				st1.executeUpdate();
			}

		} catch (ClassCastException e) {
			System.err.println("Errore in fase di casting del dipendente");
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	public boolean selectByEmailPassword(Dipendente input) {

		boolean result = true;
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;

		try {
			String query = "SELECT * FROM DIPENDENTI WHERE EMAIL=? AND PASSWORD=?";

			st1 = conn.prepareStatement(query);
			st1.setString(1, input.getEmail());
			st1.setString(2, input.getPassword());
			
			rs1 = st1.executeQuery();
			
			result = rs1.absolute(1);
			//System.out.println("reeeeeeeeeeees: " + result);

		} catch (IndexOutOfBoundsException e1) {
			System.err.println("");
			result = false;
		}catch (ClassCastException e1) {
			System.err.println("Errore in fase di casting del dipendente");
			result = false;
		} catch (Exception e1) {
			System.err.println("Errore in fase di autenticazione");
			e1.printStackTrace();
			result = false;
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public String selectIdByEmailPassword(Dipendente input) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		String id = "";
		
		try {
			String query = "SELECT IDDIPENDENTE FROM DIPENDENTI WHERE EMAIL=? AND PASSWORD=?";

			st1 = conn.prepareStatement(query);
			st1.setString(1, input.getEmail());
			st1.setString(2, input.getPassword());
			
			rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				id = rs1.getString(1);
			}

		} catch (IndexOutOfBoundsException e1) {
			System.err.println("");
		}catch (ClassCastException e1) {
			System.err.println("Errore in fase di casting del dipendente");
		} catch (Exception e1) {
			System.err.println("Errore in fase di autenticazione");
			e1.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return id;
	}
	
	@Override
	public TipoOperatore selectTipoOperatoreById(Dipendente input) {
		
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		TipoOperatore tipo = null;
		
		try {
			String query = "SELECT TIPOOPERATORE FROM DIPENDENTI WHERE IDDIPENDENTE=?";

			st1 = conn.prepareStatement(query);
			st1.setString(1, input.getIdDipendente());
			
			rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				tipo = TipoOperatore.valueOf(rs1.getString(1));
				System.out.println(tipo);
				return tipo;
			}

		} catch (IndexOutOfBoundsException e1) {
			System.err.println("");
		}catch (ClassCastException e1) {
			System.err.println("Errore in fase di casting del dipendente");
		} catch (Exception e1) {
			System.err.println("Errore in fase di autenticazione");
			e1.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return tipo;
	}

	public static void main(String[] args) {
		DipendenteDAO d = new DipendenteDAO();

		/*
		 * Operatore o = new Operatore("D001", "Andrea", "Bigoli", "BGLAND23Y45R207U",
		 * "andrea.bigoli@f2aservice.com", "ciaomamma", 1200,
		 * TipoOperatore.MANUTENTORE); boolean t = d.insertDipendente(o); if (t)
		 * System.out.println(o);
		 * 
		 * Operatore fittizio = new Operatore(null, null, null, null, null, null, 0,
		 * TipoOperatore.MANUTENTORE); ArrayList<Operatore> lista =
		 * d.selectByTipoOperatore(fittizio);
		 * 
		 * for (Operatore o1 : lista) System.out.println(o1);
		 */

		/*
		 * Corriere c = new Corriere("D002", "Filippo", "Andreini", "BGLAND23Y45R207T",
		 * "filippo.andreini@f2aservice.com", "ciaopapa", 1200, StatoCorriere.LIBERO);
		 * boolean t = d.insertDipendente(c); if (t) System.out.println(c);
		 */

		// Corriere fittizio = new Corriere(null, null, null, null, null, null, 0);
		
		/*ArrayList<Corriere> listaCorrieri = d.selectCorrieri();

		for (Corriere o1 : listaCorrieri)
			System.out.println(o1);
		
		System.out.println("--------------------------------------------------------");
		ArrayList<Operatore> listaResponsabiliLiberi = d.selectResponsabiliStazioneNonAssegnati();

		for (Operatore o2 : listaResponsabiliLiberi)
			System.out.println(o2);*/
		
		Operatore o = new Operatore("fabio.colombo@f2aservice.com", "1234");
		//System.out.println(d.selectByEmailPassword(o));
		System.out.println(d.selectTipoOperatoreById(new Operatore(d.selectIdByEmailPassword(o))));

	}

	@Override
	public Operatore selectOperatoreByEmailPassword(Dipendente input) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
