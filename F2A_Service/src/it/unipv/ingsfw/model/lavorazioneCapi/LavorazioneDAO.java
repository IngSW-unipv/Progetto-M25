package it.unipv.ingsfw.model.lavorazioneCapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.LavaggioDAO;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.users.Cliente;

public class LavorazioneDAO implements ILavorazioneDAO {

	private Connection conn;
	private static LavorazioneDAO instance = null;

	private LavorazioneDAO() {
		super();
	}
	
	public static LavorazioneDAO getInstance() {
		if (instance == null) {
			instance = new LavorazioneDAO();
			System.out.println("Create new instance");
		} else
			System.out.println("Instance already available");
		return instance;
	}

	@Override
	public boolean addLavorazione(ObservableStazioneLavoro s, Capo c) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			String query = "INSERT INTO LAVORAZIONI VALUES (?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, c.getIdCapo());
			st1.setString(2, s.getIdStazione());

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			// System.out.println(date);
			st1.setDate(3, date);

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}
		DBConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateLavorazione(ObservableStazioneLavoro s, Capo c) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			String query = "UPDATE LAVORAZIONI SET DATALAVORAZIONE=? WHERE IDC=? AND IDSTAZIONE=?";
			st1 = conn.prepareStatement(query);

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			// System.out.println(date);
			st1.setDate(1, date);
			st1.setString(2, c.getIdCapo());
			st1.setString(3, s.getIdStazione());

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}
		DBConnection.closeConnection(conn);
		return esito;
	}

	//usata in removeCapiStazione() nella classe ObservableStazioneLavoro
	
	@Override
	public boolean addLavorazioneSospesa(ObservableStazioneLavoro s, Capo c) {
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {

			String query = "INSERT INTO LAVORAZIONI VALUES (?,?,null)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, c.getIdCapo());
			st1.setString(2, s.getIdStazione());

			st1.executeUpdate();

		} catch(SQLIntegrityConstraintViolationException e) {
			System.err.println("chiave duplicata");
			esito = false;
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}
		DBConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public ArrayList<Capo> checkPresenzaCapiInStazione(ObservableStazioneLavoro s) {

		ArrayList<Capo> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;

		try {

			LavaggioDAO lav = new LavaggioDAO();
			HashMap<Integer, String> listaTipiLavaggio = lav.selectAll();

			String query = "SELECT * FROM CAPI WHERE IDC IN (SELECT IDC FROM LAVORAZIONI WHERE IDSTAZIONE='"
					+ s.getIdStazione() + "' AND DATALAVORAZIONE IS NULL)";
			st1 = conn.prepareStatement(query);

			rs1 = st1.executeQuery();

			while (rs1.next()) {

				Negozio negDep = new Negozio(rs1.getString(6));
				Negozio negCon = new Negozio(rs1.getString(7));
				Cliente cl = new Cliente(rs1.getString(9));
				Capo c = new Capo(rs1.getString(1), StatoCapo.valueOf(rs1.getString(2)),
						TipoLavaggio.valueOf(listaTipiLavaggio.get(rs1.getInt(3))), rs1.getDate(4), rs1.getDate(5),
						negDep, negCon, rs1.getDouble(8), cl);

				result.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
}
