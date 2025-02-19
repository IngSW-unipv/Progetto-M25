package it.unipv.ingsfw.model.tickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.users.Corriere;

public class MezzoDAO implements IMezzoDAO {
	Connection conn;

	@Override
	public ArrayList<Mezzo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Mezzo> selectByCapienza(Mezzo fornInput) {
		// TODO Auto-generated method stub
		return null;
	}

	//AGGIUNTO IN DATA 19/02
	@Override
	public Mezzo selectById(Mezzo m) {
		String IdMez= m.getIdMezzo();
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		PreparedStatement st2;
		ResultSet rs1;
		ResultSet rs2;

		try {

			String query = "SELECT * FROM MEZZI WHERE IdMezzo=?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, IdMez);
			rs1 = st1.executeQuery();
			
			//seconda query per impostare la disponibilità del mezzo (true or false) --> se quel mezzo è assegnato ad un ticket 'Presa_in_carico' non è disponibile
			String query2 = "select count(*) from mezzi join ticket on mezzi.IdMezzo=ticket.IdMezzo where mezzi.IdMezzo =? and ticket.stato='PRESA_IN_CARICO'";
			st2 = conn.prepareStatement(query2);
			st2.setString(1, IdMez);
			rs2 = st2.executeQuery();

			while (rs1.next()) {
				m = new Mezzo (rs1.getString(1),rs1.getInt(2),false);
				while (rs2.next()) {
					if(rs2.getInt(1)==0) m.setDisponibilita(true);
				}
			}
			
		} catch (ClassCastException e) {
			System.out.println("Errore in fase di casting del dipendente");
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return m;
	}
	@Override
	public boolean insertMezzo(Mezzo m) {
		// TODO Auto-generated method stub
		return false;
	}

}
