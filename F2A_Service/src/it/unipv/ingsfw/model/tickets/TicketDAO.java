package it.unipv.ingsfw.model.tickets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;


import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.users.Corriere;


public class TicketDAO implements ITicketDAO {
	
	Connection connessione;
	private static TicketDAO instance = null;

	public TicketDAO() {
		super();
	}
	
	//Concettualmente un singleton ma visibilità a public per il DAO FACTORY (retClass.getDeclaredConstructor().newInstance())
	public static TicketDAO getInstance() {
		if (instance == null) {
			instance = new TicketDAO();
			System.out.println("Create new instance");
		} else
			System.out.println("Instance already available");
		return instance;
	}

	
	//uso un oggetto ticket fittizio (tP), per passare lo stato e il dipendente
	//Manca gestione sql injection, anche nelle altre modifiche
	//MODIFICATO IN DATA 19/02
	@Override
	public ArrayList<Ticket> selectByStatoAndCorriere(Ticket ticketFit) {
		ArrayList <Ticket> listaTicket = new ArrayList<Ticket>();
		StatoTicket stato = ticketFit.getStato();
		String st = stato.toString();
		String idDip = ticketFit.getCorriere().getIdDipendente();
		connessione = DBConnection.startConnection(connessione);
		PreparedStatement st1;
		ResultSet rs1;

		try {
			String query = "select * from Ticket where stato =? and IdDipendente =?";
			st1 = connessione.prepareStatement(query);
			st1.setString(1, st);
			st1.setString(2, idDip);
			rs1 = st1.executeQuery();

			while (rs1.next()) {
				ticketFit=getTicketFromResultQuery(rs1);
				//aggiungo all'arrayList di ticket il ticket appena creato (preso da DB)
				listaTicket.add(ticketFit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(connessione);
		return listaTicket;
	}
	
	
	public ArrayList<String> selectIDTipoTicketByStatoAndCorriere(Ticket ticketFit) {
		ArrayList <String> listaAnteprimeTicket = new ArrayList <String>();
		String anteprimaTicket;
		StatoTicket stato = ticketFit.getStato();
		String st = stato.toString();
		String idDip = ticketFit.getCorriere().getIdDipendente();
		connessione = DBConnection.startConnection(connessione);
		PreparedStatement st1;
		ResultSet rs1;

		try {

			String query = "select IdTicket, TipoTicket from Ticket where stato =? and IdDipendente =?";
			st1 = connessione.prepareStatement(query);
			st1.setString(1, st);
			st1.setString(2, idDip);
			rs1 = st1.executeQuery();

			while (rs1.next()) {
				anteprimaTicket = "ID: "+rs1.getString(1)+" TIPOLOGIA: "+rs1.getString(2);
				//aggiungo all'arrayList di ticket il ticket appena creato (preso da DB)
				listaAnteprimeTicket.add(anteprimaTicket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(connessione);
		return listaAnteprimeTicket;
	}
	
	@Override
	public Ticket selectTicketById(Ticket ticketFit) {
		String idTik = ticketFit.getIdTicket();
		connessione = DBConnection.startConnection(connessione);
		PreparedStatement st1;
		ResultSet rs1;

		try {

			String query = "select * from Ticket where IdTicket =?";
			st1 = connessione.prepareStatement(query);
			st1.setString(1, idTik);
			rs1 = st1.executeQuery();
			while (rs1.next()) {
				ticketFit=getTicketFromResultQuery(rs1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(connessione);
		return ticketFit;
	}
	
	
	
	@Override
	public boolean insertTicket(Ticket t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//Dato che viene utilizzzato da più richieste
	private Ticket getTicketFromResultQuery(ResultSet rs1) throws SQLException {
		Mezzo mezzo = new Mezzo(rs1.getString(6));
		mezzo = mezzo.getMezzoByID();
		Itinerario itinerario = new Itinerario(rs1.getString(7));
		itinerario=itinerario.getItinerarioByID(); //aggiungo all'itinerario anche la relativa lista di tappe
		Corriere corriere = new Corriere(rs1.getString(8));
		corriere = corriere.getCorriereByID();
		Ticket ticket = new Ticket(rs1.getString(1),TipologiaTicket.valueOf(rs1.getString(2)),StatoTicket.valueOf(rs1.getString(3)),mezzo,itinerario,corriere);
		return ticket;
	}
	
	@Override
	public boolean updateStatoTicket(Ticket ticket){
		
		connessione = DBConnection.startConnection(connessione);
		PreparedStatement st1;
		boolean esito = true;

		try {
			String query = "UPDATE TICKET SET STATO =? WHERE IdTicket = ?";

			st1 = connessione.prepareStatement(query);
			st1.setString(1, ticket.getStato().toString());
			st1.setString(2, ticket.getIdTicket());

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

		DBConnection.closeConnection(connessione);
		return esito;
	}
	
	
	public static void main(String[] args) {
		//questo main deve essere cio che fa la classe corriere (sia view che model) (capire cosa fare)
		
		//STAMPARE I TICKET ASSEGNATI AD UN CERTO CORRIERE
		System.out.println("elenco ticket del Corriere D003");
		Corriere cor = new Corriere("D003");
		Ticket ticket = new Ticket(StatoTicket.ASSEGNATO ,cor);
		TicketDAO td = new TicketDAO();
		ArrayList <Ticket> listaTicket = td.selectByStatoAndCorriere(ticket);
		for (Ticket t1 : listaTicket) {
			System.out.println(t1);
		System.out.println("--------------------------------------------------------");
		}
	}

}
