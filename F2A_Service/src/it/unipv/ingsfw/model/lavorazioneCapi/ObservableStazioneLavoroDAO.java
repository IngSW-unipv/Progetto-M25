package it.unipv.ingsfw.model.lavorazioneCapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.TipoLavaggio;

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

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				s = new ObservableStazioneLavoro(rs1.getString(1), TipologiaStazione.valueOf(rs1.getString(2)),
						StatoStazione.valueOf(rs1.getString(3)), rs1.getDouble(7));

				result.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertStazione(ObservableStazioneLavoro s) {

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
			/*
			 * query2="INSERT INTO STAZIONILAVORO (IDSTAZIONE,IDCATENA,TIPO,STATO,LIVELLOPRODOTTOLAVAGGIO) VALUES (?,?,?,?,?)"
			 * ; st1 = conn.prepareStatement(query2); st1.setString(1,s.getIdStazione());
			 * st1.setString(2,idCatena); st1.setString(3,s.getTipo().toString());
			 * st1.setString(4,s.getStatoStazione().toString());
			 * st1.setDouble(5,s.getLivelloProdottoLavaggio());
			 */

			query2 = "INSERT INTO STAZIONILAVORO (IDSTAZIONE,IDCATENA,TIPO,STATO,LIVELLOPRODOTTOLAVAGGIO) VALUES ('"
					+ s.getIdStazione() + "','" + idCatena + "','" + s.getTipo().toString() + "','"
					+ s.getStatoStazione().toString() + "', " + s.getLivelloProdottoLavaggio() + ")";
			st1 = conn.prepareStatement(query2);

			st1.executeUpdate(query2);

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	public static void main(String[] args) {
		ObservableStazioneLavoroDAO st = new ObservableStazioneLavoroDAO();
		//ObservableStazioneLavoro s = new ObservableStazioneLavoro("S001", TipologiaStazione.ASCIUGATURA, StatoStazione.READY, 100.0);
		//boolean t = st.insertStazione(s);
		//if (t)
			//System.out.println(s);
		
		ArrayList<ObservableStazioneLavoro> obs = st.selectAll();
		for(ObservableStazioneLavoro o : obs)
			System.out.println(o);
	}

}
