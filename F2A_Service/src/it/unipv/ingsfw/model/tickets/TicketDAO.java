package it.unipv.ingsfw.model.tickets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.LavaggioDAO;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazioneDAO;
import it.unipv.ingsfw.model.users.Corriere;

import java.util.ArrayList;

public class TicketDAO implements ITicketDAO {
	Connection connessione;
	
	/**
	 * 
	 */
	public TicketDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	//uso un oggetto ticket fittizio (tP), per passare lo stato e il dipendente
	//Manca gestione sql injection, anche nelle altre modifiche
	@Override
	public ArrayList<Ticket> selectByStatoAndCorriere(Ticket ticketFit) {
		ArrayList <Ticket> listaTicket = new ArrayList<Ticket>();
		StatoTicket stato = ticketFit.getStato();
		String s = stato.toString();
		String c = ticketFit.getCorriere().getIdDipendente();
		connessione = DBConnection.startConnection(connessione);
		PreparedStatement st1;
		ResultSet rs1;

		try {

			String query = "select * from Ticket where stato ='" + s +"' and IdDipendente ='"+ c+"'";
			st1 = connessione.prepareStatement(query);
			rs1 = st1.executeQuery();

			while (rs1.next()) {

				//ticketFit = new Ticket(rs1.getString(1),TipologiaTicket.valueOf(rs1.getString(2)) ,StatoTicket.valueOf(rs1.getString(3)));
				//aggiungo all'arrayList di ticket il ticket appena creato (preso da DB)
				listaTicket.add(ticketFit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(connessione);
		return listaTicket;
	}

	@Override
	public boolean insertTicket(Ticket t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) {
		Corriere cor = new Corriere("D003",null,null,null,null,null,0);
		Ticket ticket = new Ticket(null, null, StatoTicket.ASSEGNATO ,null,null,cor);
		TicketDAO td = new TicketDAO();
		ArrayList <Ticket> listaTicket = td.selectByStatoAndCorriere(ticket);
		for (Ticket t1 : listaTicket)
			System.out.println(t1);
		
		System.out.println("--------------------------------------------------------");
	}

}
