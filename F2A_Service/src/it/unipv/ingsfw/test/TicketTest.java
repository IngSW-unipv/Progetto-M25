package it.unipv.ingsfw.test;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.tickets.Itinerario;
import it.unipv.ingsfw.model.tickets.Mezzo;
import it.unipv.ingsfw.model.tickets.StatoTicket;
import it.unipv.ingsfw.model.tickets.Ticket;
import it.unipv.ingsfw.model.tickets.TicketDAO;
import it.unipv.ingsfw.model.tickets.TipologiaTicket;
import it.unipv.ingsfw.model.users.Corriere;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TicketTest {

	private Ticket ticketCompleto;
	private Ticket ticketStatoCorriere;
	private Ticket ticketId;
	private Ticket ticketNullo;
	private TicketDAO ticketDAO;
	private Corriere corriere;
	private Mezzo mezzo;
	private Itinerario itinerario;

	@Before
	public void setUp() {
		corriere = new Corriere("D001");
		mezzo = new Mezzo("M001");
		itinerario = new Itinerario("I001");
		ticketCompleto = new Ticket("T001", TipologiaTicket.RITIRO, StatoTicket.ASSEGNATO, mezzo, itinerario, corriere);
		ticketStatoCorriere = new Ticket(StatoTicket.ASSEGNATO, corriere);
		ticketId = new Ticket("T002");
		ticketNullo = new Ticket();
		ticketDAO = TicketDAO.getInstance();
	}

	@Test
	public void testCostruttoreCompleto() {
		assertEquals("T001", ticketCompleto.getIdTicket());
		assertEquals(TipologiaTicket.RITIRO, ticketCompleto.getTipologia());
		assertEquals(StatoTicket.ASSEGNATO, ticketCompleto.getStato());
		assertEquals("D001", ticketCompleto.getCorriere().getIdDipendente());
		assertEquals("M001", ticketCompleto.getMezzo().getIdMezzo());
		assertEquals("I001", ticketCompleto.getItinerario().getIdItinerario());
	}

	@Test
	public void testCostruttoreStatoCorriere() {
		assertNull(ticketStatoCorriere.getIdTicket());
		assertNull(ticketStatoCorriere.getTipologia());
		assertEquals(StatoTicket.ASSEGNATO, ticketStatoCorriere.getStato());
		assertEquals("D001", ticketStatoCorriere.getCorriere().getIdDipendente());
	}

	@Test
	public void testCostruttoreId() {
		assertEquals("T002", ticketId.getIdTicket());
		assertNull(ticketId.getTipologia());
		assertNull(ticketId.getStato());
		assertNull(ticketId.getCorriere());
	}

	@Test
	public void testCostruttoreNullo() {
		assertNull(ticketNullo.getIdTicket());
		assertNull(ticketNullo.getTipologia());
		assertNull(ticketNullo.getStato());
		assertNull(ticketNullo.getCorriere());
	}

	@Test
	public void testSetGetStato() {
		ticketNullo.setStato(StatoTicket.COMPLETATO);
		assertEquals(StatoTicket.COMPLETATO, ticketNullo.getStato());
	}

	@Test
	public void testSetGetCorriere() {
		Corriere nuovoCorriere = new Corriere("D002");
		ticketNullo.setCorriere(nuovoCorriere);
		assertEquals("D002", ticketNullo.getCorriere().getIdDipendente());
	}

	@Test
	public void testSetGetMezzo() {
		Mezzo nuovoMezzo = new Mezzo("M002");
		ticketNullo.setMezzo(nuovoMezzo);
		assertEquals("M002", ticketNullo.getMezzo().getIdMezzo());
	}

	@Test
	public void testSetGetItinerario() {
		Itinerario nuovoItinerario = new Itinerario("I002");
		ticketNullo.setItinerario(nuovoItinerario);
		assertEquals("I002", ticketNullo.getItinerario().getIdItinerario());
	}

	@Test
	public void testSetGetListaCapiRitOCon() {
		ArrayList<Capo> listaCapi = new ArrayList<>();
		listaCapi.add(new Capo("C001"));
		ticketNullo.setListaCapiRitOCon(listaCapi);
		assertEquals(1, ticketNullo.getListaCapiRitOCon().size());
		assertEquals("C001", ticketNullo.getListaCapiRitOCon().get(0).getIdCapo());
	}

	@Test
	public void testToString() {
		String expected = "\nTicket T001\nTipologia: RITIRO\nStato: ASSEGNATO\n\n Info Corriere: Dipendente D001\nNome: null\nCognome: null\ncf: null\nEmail: null\nPassword: null\nStatoCorriere: null\n\n Info Itinerario:\n ID Itinerario: I001\n TAPPE:\n\n Info Mezzo:\n Id Mezzo: M001\n Capienza: 0";
		assertEquals(expected, ticketCompleto.toString());
	}

	// Test dei metodi DAO
	@Test
	public void testSelectByStatoAndCorriere() {
		ArrayList<Ticket> tickets = ticketDAO.selectByStatoAndCorriere(ticketStatoCorriere);
		assertNotNull(tickets);
		assertTrue(tickets.size() >= 0);
	}

	@Test
	public void testSelectIDTipoTicketByStatoAndCorriere() {
		ArrayList<String> anteprime = ticketDAO.selectIDTipoTicketByStatoAndCorriere(ticketStatoCorriere);
		assertNotNull(anteprime);
		assertTrue(anteprime.size() >= 0);
	}

	@Test
	public void testSelectTicketById() {
		Ticket ticket = ticketDAO.selectTicketById(ticketId);
		assertNotNull(ticket);
		assertEquals("T002", ticket.getIdTicket());
	}

	@Test
	public void testUpdateStatoTicket() {
		ticketCompleto.setStato(StatoTicket.COMPLETATO);
		assertTrue(ticketDAO.updateStatoTicket(ticketCompleto));
	}

	// Test dei casi Of-By-One per Ticket
	@Test
	public void testIdTicketLimiteInferiore() {
		try {
			new Ticket("");
			fail("Scatenata Eccezione, IdTicket vuoto");
		} catch (IllegalArgumentException e) {
			// Test superato se l'eccezione viene lanciata
		}
	}

	@Test
	public void testIdTicketLimiteSuperiore() {
		try {
			new Ticket("T00123456789");
			fail("Scatenata Eccezione, IdTicket troppo lungo");
		} catch (IllegalArgumentException e) {
			// Test superato se l'eccezione viene lanciata
		}
	}

	// aggiunto controllo su IDTicket nella classe Ticket

	@Test
	public void testListaCapiRitOConVuota() {
		assertEquals(0, ticketNullo.getListaCapiRitOCon().size());
	}

	@Test
	public void testListaCapiRitOConUnElemento() {
		ArrayList<Capo> listaCapi = new ArrayList<>();
		listaCapi.add(new Capo("C001"));
		ticketNullo.setListaCapiRitOCon(listaCapi);
		assertEquals(1, ticketNullo.getListaCapiRitOCon().size());
	}

	@Test
	public void testListaCapiRitOConMoltiElementi() {
		ArrayList<Capo> listaCapi = new ArrayList<>();
		listaCapi.add(new Capo("C001"));
		listaCapi.add(new Capo("C002"));
		ticketNullo.setListaCapiRitOCon(listaCapi);
		assertEquals(2, ticketNullo.getListaCapiRitOCon().size());
	}

	// Test dei casi Of-By-One per TicketDAO
	@Test
	public void testSelectByStatoAndCorriereNessunTicket() {
		Ticket ticketFittizio = new Ticket(StatoTicket.COMPLETATO, new Corriere("D999"));
		ArrayList<Ticket> tickets = ticketDAO.selectByStatoAndCorriere(ticketFittizio);
		assertEquals(0, tickets.size());
	}

	@Test
	public void testSelectByStatoAndCorriereUnTicket() {
		Ticket ticketFittizio = new Ticket(StatoTicket.ASSEGNATO, new Corriere("D003"));
		ArrayList<Ticket> tickets = ticketDAO.selectByStatoAndCorriere(ticketFittizio);
		assertTrue(tickets.size() >= 1);
	}

	@Test
	public void testSelectByStatoAndCorriereMoltiTicket() {
		Ticket ticketFittizio = new Ticket(StatoTicket.ASSEGNATO, new Corriere("D003"));
		ArrayList<Ticket> tickets = ticketDAO.selectByStatoAndCorriere(ticketFittizio);
		assertTrue(tickets.size() > 1);
	}

	// Per selectIDTipoTicketByStatoAndCorriere

	@Test
	public void testSelectIDTipoTicketByStatoAndCorriereNessunTicket() {
		// cotrollo che effettivamente non ci sia nessun ticket associato al corriere
		// D999 (che non esiste)
		Ticket ticketFittizio = new Ticket(StatoTicket.COMPLETATO, new Corriere("D999"));
		ArrayList<String> anteprime = ticketDAO.selectIDTipoTicketByStatoAndCorriere(ticketFittizio);
		assertEquals(0, anteprime.size());
	}

	@Test
	public void testSelectIDTipoTicketByStatoAndCorriereUnTicket() {
		// cotrollo su esistenza di almeno un ticket assegnato al corriere D003
		Ticket ticketFittizio = new Ticket(StatoTicket.ASSEGNATO, new Corriere("D003"));
		ArrayList<String> anteprime = ticketDAO.selectIDTipoTicketByStatoAndCorriere(ticketFittizio);
		assertTrue(anteprime.size() >= 1);
	}

	@Test
	public void testSelectIDTipoTicketByStatoAndCorriereMoltiTicket() {
		Ticket ticketFittizio = new Ticket(StatoTicket.ASSEGNATO, new Corriere("D003"));
		ArrayList<String> anteprime = ticketDAO.selectIDTipoTicketByStatoAndCorriere(ticketFittizio);
		assertTrue(anteprime.size() > 1);
	}
}
