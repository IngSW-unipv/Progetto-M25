package it.unipv.ingsfw.model.lavorazioneCapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.Capo;

public class LavorazioneDAO implements ILavorazioneDAO {

	private Connection conn;

	public LavorazioneDAO() {
		super();
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

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}
		DBConnection.closeConnection(conn);
		return esito;
	}
}
