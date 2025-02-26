package it.unipv.ingsfw.model.lavorazioneCapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.Operatore;

public class ObservableStazioneLavoroDAO implements IObservableStazioneLavoroDAO {

	// private String schema;
	private Connection conn;

	public ObservableStazioneLavoroDAO() {
		super();
	}

	@Override
	public ArrayList<ObservableStazioneLavoro> selectAll() {
		ArrayList<ObservableStazioneLavoro> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		ObservableStazioneLavoro s;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * from STAZIONILAVORO";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				s = new ObservableStazioneLavoro(rs1.getString(1), TipologiaStazione.valueOf(rs1.getString(3)),
						StatoStazione.valueOf(rs1.getString(4)), rs1.getDouble(5));

				result.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<ObservableStazioneLavoro> selectByStato(ObservableStazioneLavoro input) {
		ArrayList<ObservableStazioneLavoro> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		ObservableStazioneLavoro s;

		try {
			String query = "SELECT * FROM STAZIONILAVORO WHERE STATO=?";

			st1 = conn.prepareStatement(query);
			st1.setString(1, input.getStatoStazione().toString());

			rs1 = st1.executeQuery();

			while (rs1.next()) {

				s = new ObservableStazioneLavoro(rs1.getString(1), TipologiaStazione.valueOf(rs1.getString(3)),
						StatoStazione.valueOf(rs1.getString(4)), rs1.getDouble(5));

				result.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertStazioneWithUnknownCatena(ObservableStazioneLavoro s) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		PreparedStatement st2;
		ResultSet rs2;
		String idCatena = "";
		String query1;
		String query2;

		boolean esito = true;

		try {
			query1 = "SELECT IDCATENA FROM CATENELAVORAZIONE C WHERE NOT EXISTS (SELECT * FROM STAZIONILAVORO S WHERE C.IDCATENA = S.IDCATENA AND S.TIPO = '"
					+ s.getTipo().toString() + "')";
			st2 = conn.prepareStatement(query1);
			// st2.setString(1,s.getTipo().toString());

			rs2 = st2.executeQuery(query1);

			while (rs2.next()) {

				idCatena = rs2.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		try {

			query2 = "INSERT INTO STAZIONILAVORO (IDSTAZIONE,IDCATENA,TIPO,STATO,LIVELLOPRODOTTOLAVAGGIO) VALUES (?,?,?,?,?)";
			st1 = conn.prepareStatement(query2);
			st1.setString(1, s.getIdStazione());
			st1.setString(2, idCatena);
			st1.setString(3, s.getTipo().toString());
			st1.setString(4, s.getStatoStazione().toString());
			st1.setDouble(5, s.getLivelloProdottoLavaggio());

			/*
			 * query2 =
			 * "INSERT INTO STAZIONILAVORO (IDSTAZIONE,IDCATENA,TIPO,STATO,LIVELLOPRODOTTOLAVAGGIO) VALUES ('"
			 * + s.getIdStazione() + "','" + idCatena + "','" + s.getTipo().toString() +
			 * "','" + s.getStatoStazione().toString() + "', " +
			 * s.getLivelloProdottoLavaggio() + ")"; st1 = conn.prepareStatement(query2);
			 */

			st1.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("Stazione già esistente");
			esito = false;
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	@Override
	public boolean insertStazioneWithKnownCatena(ObservableStazioneLavoro s, CatenaLavorazione c) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			String query = "INSERT INTO STAZIONILAVORO (IDSTAZIONE,IDCATENA,TIPO,STATO,LIVELLOPRODOTTOLAVAGGIO) VALUES (?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, s.getIdStazione());
			st1.setString(2, c.getIdCatena());
			st1.setString(3, s.getTipo().toString());
			st1.setString(4, s.getStatoStazione().toString());
			st1.setDouble(5, s.getLivelloProdottoLavaggio());

			/*
			 * query2 =
			 * "INSERT INTO STAZIONILAVORO (IDSTAZIONE,IDCATENA,TIPO,STATO,LIVELLOPRODOTTOLAVAGGIO) VALUES ('"
			 * + s.getIdStazione() + "','" + idCatena + "','" + s.getTipo().toString() +
			 * "','" + s.getStatoStazione().toString() + "', " +
			 * s.getLivelloProdottoLavaggio() + ")"; st1 = conn.prepareStatement(query2);
			 */

			st1.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("Stazione già esistente");
			esito = false;
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	public int getIdLastAssegnazione() {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;

		int id = 0;

		try {

			String query = "SELECT IDASSEGNAZIONE FROM ASSEGNAZIONI ORDER BY IDASSEGNAZIONE DESC LIMIT 1";
			st1 = conn.prepareStatement(query);
			rs1 = st1.executeQuery();

			while (rs1.next())
				id = rs1.getInt(1);

		} catch (SQLException e) {
			System.err.println("Errore di esecuzione");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return id;
	}

	public String selectIdCatenaByStazione(ObservableStazioneLavoro s) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;

		String cat = "";

		try {

			String query = "SELECT IDCATENA FROM STAZIONILAVORO WHERE IDSTAZIONE = '" + s.getIdStazione() + "'";
			st1 = conn.prepareStatement(query);
			rs1 = st1.executeQuery();

			while (rs1.next())
				cat = rs1.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return cat;
	}

	public boolean assegnazioneResponsabileStazioneLibero(ObservableStazioneLavoro s) {

		DipendenteDAO dip = new DipendenteDAO();
		ArrayList<Operatore> listaResponsabiliStazioneLiberi = dip.selectResponsabiliStazioneNonAssegnati();
		int idNewAssegnazione = getIdLastAssegnazione() + 1;

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			// System.out.println(date);

			String query = "INSERT INTO ASSEGNAZIONI VALUES (?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, idNewAssegnazione);
			st1.setString(2, s.getIdStazione());
			st1.setString(3, listaResponsabiliStazioneLiberi.get(0).getIdDipendente());
			st1.setDate(4, date);
			st1.setDate(5, null);

			st1.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("IdAssegnazione già esistente");
			esito = false;
		} catch (SQLException e) {
			System.err.println("Errore in fase di elaborazione query");
			e.printStackTrace();
			esito = false;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Nessun operatore al momento libero");
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}

	public boolean assegnazioneManutentoreLibero(ObservableStazioneLavoro s) {

		DipendenteDAO dip = new DipendenteDAO();
		ArrayList<Operatore> listaManutentoriLiberi = dip.selectManutentoriNonAssegnati();
		int idNewAssegnazione = getIdLastAssegnazione() + 1;

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			// System.out.println(date);

			String query = "INSERT INTO ASSEGNAZIONI VALUES (?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, idNewAssegnazione);
			st1.setString(2, s.getIdStazione());
			st1.setString(3, listaManutentoriLiberi.get(0).getIdDipendente());
			st1.setDate(4, date);
			st1.setDate(5, null);

			st1.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("Assegnazione già esistente");
			esito = false;
		} catch (SQLException e) {
			System.err.println("Errore in fase di elaborazione query");
			// e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Nessun manutentore al momento libero");
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	public ArrayList<ObservableStazioneLavoro> selectStazioniReadyNonAssegnate() {

		ArrayList<ObservableStazioneLavoro> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		ObservableStazioneLavoro s;

		try {
			String query = "SELECT * FROM STAZIONILAVORO S WHERE S.STATO = 'READY' AND NOT EXISTS (SELECT * FROM ASSEGNAZIONI A WHERE S.IDSTAZIONE = A.IDSTAZIONE AND A.DATAFINEASSEGNAZIONE IS NULL)";
			st1 = conn.prepareStatement(query);

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				s = new ObservableStazioneLavoro(rs1.getString(1), TipologiaStazione.valueOf(rs1.getString(3)),
						StatoStazione.valueOf(rs1.getString(4)), rs1.getDouble(5));

				result.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public ArrayList<ObservableStazioneLavoro> selectStazioniMaintenanceNonAssegnate() {

		ArrayList<ObservableStazioneLavoro> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		ObservableStazioneLavoro s;

		try {
			String query = "SELECT * FROM STAZIONILAVORO S WHERE S.STATO = 'MAINTENANCE' AND NOT EXISTS (SELECT * FROM ASSEGNAZIONI A WHERE S.IDSTAZIONE = A.IDSTAZIONE AND A.DATAFINEASSEGNAZIONE IS NULL)";
			st1 = conn.prepareStatement(query);

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				s = new ObservableStazioneLavoro(rs1.getString(1), TipologiaStazione.valueOf(rs1.getString(3)),
						StatoStazione.valueOf(rs1.getString(4)), rs1.getDouble(5));

				result.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public String getNewIdStazione() {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		String newIdStazione = "";

		try {
			String query = "SELECT IDSTAZIONE FROM STAZIONILAVORO ORDER BY IDSTAZIONE DESC LIMIT 1";
			st1 = conn.prepareStatement(query);

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				newIdStazione = rs1.getString(1);
				String sub = newIdStazione.substring(3);
				// System.out.println(sub);
				int num = Integer.parseInt(sub) + 1;
				newIdStazione = String.format("S%03d", num);
				// System.out.println(newIdStazione);

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return newIdStazione;
	}

	public ObservableStazioneLavoro selectStazioniReadyNonAssegnatePerTipo(ObservableStazioneLavoro s) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		ObservableStazioneLavoro s0 = null;

		try {
			String query = "SELECT * FROM STAZIONILAVORO S WHERE S.STATO = 'READY' AND TIPO='" + s.getTipo().toString()
					+ "' AND NOT EXISTS (SELECT * FROM ASSEGNAZIONI A WHERE S.IDSTAZIONE = A.IDSTAZIONE AND A.DATAFINEASSEGNAZIONE IS NULL) LIMIT 1";
			st1 = conn.prepareStatement(query);

			rs1 = st1.executeQuery();

			while (rs1.next()) {

				s0 = new ObservableStazioneLavoro(rs1.getString(1), TipologiaStazione.valueOf(rs1.getString(3)),
						StatoStazione.valueOf(rs1.getString(4)), rs1.getDouble(5));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return s0;
	}

	@Override
	public boolean assegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o) {

		int idNewAssegnazione = getIdLastAssegnazione() + 1;
		//System.out.println(idNewAssegnazione);
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			// System.out.println(date);

			String query = "INSERT INTO ASSEGNAZIONI VALUES (?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, idNewAssegnazione);
			st1.setString(2, s.getIdStazione());
			st1.setString(3, o.getIdDipendente());
			st1.setDate(4, date);
			st1.setDate(5, null);
			System.out.println(st1);
			st1.executeUpdate();

		}catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("IdAssegnazione già esistente");
			esito = false;
		} catch (SQLException e) {
			System.err.println("Errore in fase di elaborazione query");
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
	public boolean changeStatoStazione(ObservableStazioneLavoro s) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			String query = "UPDATE STAZIONILAVORO SET STATO=? WHERE IDSTAZIONE =?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, s.getStatoStazione().toString());
			st1.setString(2, s.getIdStazione());

			st1.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			// System.err.println("");
			esito = false;
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}
	
	@Override
	public ArrayList<ObservableStazioneLavoro> selectStazioniByOperatore(Operatore o) {
		
		ArrayList<ObservableStazioneLavoro> result = new ArrayList<>();
		
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		ObservableStazioneLavoro s0 = null;

		try {
			String query = "SELECT * FROM STAZIONILAVORO WHERE IDSTAZIONE IN (SELECT IDSTAZIONE FROM ASSEGNAZIONI WHERE IDDIPENDENTE =? AND DATAFINEASSEGNAZIONE IS NULL)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, o.getIdDipendente());

			rs1 = st1.executeQuery();

			while (rs1.next()) {

				s0 = new ObservableStazioneLavoro(rs1.getString(1), TipologiaStazione.valueOf(rs1.getString(3)),
						StatoStazione.valueOf(rs1.getString(4)), rs1.getDouble(5));
				result.add(s0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
		
	}
	
	@Override
	public boolean chiusuraAssegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o) {

		//System.out.println(idNewAssegnazione);
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			// System.out.println(date);

			String query = "UPDATE ASSEGNAZIONI SET DATAFINEASSEGNAZIONE=? WHERE IDSTAZIONE =? AND IDDIPENDENTE =?";
			st1 = conn.prepareStatement(query);
			
			st1.setDate(1, date);
			st1.setString(2, s.getIdStazione());
			st1.setString(3, o.getIdDipendente());
			
			st1.executeUpdate();

		}catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("IdAssegnazione già esistente");
			esito = false;
		} catch (SQLException e) {
			System.err.println("Errore in fase di elaborazione query");
			e.printStackTrace();
			esito = false;
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}

	public static void main(String[] args) throws IndexOutOfBoundsException{
		ObservableStazioneLavoroDAO st = new ObservableStazioneLavoroDAO();
		//boolean t = st.assegnazioneResponsabileStazioneLibero(st.selectStazioniReadyNonAssegnate().get(0));
		/*
		if (t)
			System.out.println(st.selectStazioniReadyNonAssegnate().get(0));
		
		 * ObservableStazioneLavoro s = new ObservableStazioneLavoro("S001",
		 * TipologiaStazione.ASCIUGATURA, StatoStazione.READY, 100.0); boolean t =
		 * st.insertStazioneWithUnknownCatena(s); if (t) System.out.println(s);
		 * 
		 * ArrayList<ObservableStazioneLavoro> obs = st.selectAll(); for
		 * (ObservableStazioneLavoro o : obs) System.out.println(o);
		 

		ObservableStazioneLavoro s = st
				.selectStazioniReadyNonAssegnatePerTipo(new ObservableStazioneLavoro(TipologiaStazione.ASCIUGATURA));
		System.out.println(s);
		System.out.println("------------------------------------------------");

		ArrayList<ObservableStazioneLavoro> lista = st.selectAll();
		for (ObservableStazioneLavoro s0 : lista)
			System.out.println(s0);
		*/
		Operatore o = new Operatore("D001");
		
		//st.assegnazioneOperatoreNoto(st.selectStazioniReadyNonAssegnate().get(0), o);
		//st.assegnazioneOperatoreNoto(st.selectStazioniReadyNonAssegnate().get(0), o);
		//st.assegnazioneOperatoreNoto(st.selectStazioniReadyNonAssegnate().get(0), o);
		ArrayList<ObservableStazioneLavoro> lista = st.selectStazioniByOperatore(o);
		
		for(ObservableStazioneLavoro s : lista)
			System.out.println(s);
		
	}

}
