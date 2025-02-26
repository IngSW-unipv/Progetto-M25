package it.unipv.ingsfw.test;

import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.StatoTappa;
import it.unipv.ingsfw.model.negozio.Tappa;
import it.unipv.ingsfw.model.tickets.Itinerario;
import it.unipv.ingsfw.model.tickets.ItinerarioDAO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItinerarioTest {
	private Itinerario itinerario;
	private ItinerarioDAO itinerarioDAO;
	private ArrayList<Tappa> listaTappe;
	private Tappa tappa1;
	private Tappa tappa2;

	@Before
	public void setUp() {
		tappa1 = new Negozio("N001", StatoTappa.NON_ATTRAVERSATA, "Via Roma 1");
		tappa2 = new Negozio("N002", StatoTappa.NON_ATTRAVERSATA, "Via Milano 2");
		listaTappe = new ArrayList<>();
		listaTappe.add(tappa1);
		listaTappe.add(tappa2);
		itinerario = new Itinerario("I001", listaTappe);
		itinerarioDAO = new ItinerarioDAO();
	}

	@Test
	public void testGetIdItinerario() {
		assertEquals("I001", itinerario.getIdItinerario());
	}

	@Test
	public void testSetIdItinerario() {
		itinerario.setIdItinerario("I002");
		assertEquals("I002", itinerario.getIdItinerario());
	}

	@Test
	public void testGetListaTappeNegozi() {
		assertEquals(listaTappe, itinerario.getListaTappeNegozi());
	}

	@Test
	public void testSetListaTappeNegozi() {
		ArrayList<Tappa> nuovaListaTappe = new ArrayList<>();
		Tappa tappa3 = new Negozio("N003", StatoTappa.NON_ATTRAVERSATA, "Via Napoli 3");
		nuovaListaTappe.add(tappa3);
		itinerario.setListaTappeNegozi(nuovaListaTappe);
		assertEquals(nuovaListaTappe, itinerario.getListaTappeNegozi());
	}

	@Test
	public void testGetListaTappeNonAttraversate() {
		assertEquals(listaTappe, itinerario.getListaTappeNonAttraversate());
	}

	@Test
	public void testSetListaTappeNonAttraversate() {
		ArrayList<Tappa> nuovaListaTappe = new ArrayList<>();
		Tappa tappa3 = new Negozio("N003", StatoTappa.NON_ATTRAVERSATA, "Via Napoli 3");
		nuovaListaTappe.add(tappa3);
		itinerario.setListaTappeNonAttraversate(nuovaListaTappe);
		assertEquals(nuovaListaTappe, itinerario.getListaTappeNonAttraversate());
	}

	@Test
	public void testGetItinerarioByID() {
		Itinerario itinerarioTrovato = itinerario.getItinerarioByID();
		assertNotNull(itinerarioTrovato);
		assertEquals(itinerario.getIdItinerario(), itinerarioTrovato.getIdItinerario());
	}

	@Test
	public void testToString() {
		String expected = "\n ID Itinerario: I001\n TAPPE:\n-Tappa 1: Negozio N001\nIndirizzo: Via Roma 1\n\n-Tappa 2: Negozio N002\nIndirizzo: Via Milano 2\n";
		assertEquals(expected, itinerario.toString());
	}

	@Test
	public void testGetTappaCorrente() {
		Tappa tappaCorrente = itinerario.getTappaCorrente();
		assertEquals(tappa1, tappaCorrente);
	}

	@Test
	public void testSelectById() {
		Itinerario itinerarioTrovato = itinerarioDAO.selectById(itinerario);
		assertNotNull(itinerarioTrovato);
		assertEquals(itinerario.getIdItinerario(), itinerarioTrovato.getIdItinerario());
	}

	@Test
	public void testListaTappeNegoziVuota() {
		ArrayList<Tappa> listaTappeVuota = new ArrayList<>();
		Itinerario itinerarioVuoto = new Itinerario("I002", listaTappeVuota);
		assertEquals(0, itinerarioVuoto.getListaTappeNegozi().size());
	}

	@Test
	public void testListaTappeNegoziUnElemento() {
		ArrayList<Tappa> listaTappeUnElemento = new ArrayList<>();
		listaTappeUnElemento.add(tappa1);
		Itinerario itinerarioUnElemento = new Itinerario("I003", listaTappeUnElemento);
		assertEquals(1, itinerarioUnElemento.getListaTappeNegozi().size());
	}

	@Test
	public void testListaTappeNegoziMoltiElementi() {
		assertEquals(2, itinerario.getListaTappeNegozi().size());
	}

	@Test
	public void testListaTappeNonAttraversateVuota() {
		ArrayList<Tappa> listaTappeVuota = new ArrayList<>();
		Itinerario itinerarioVuoto = new Itinerario("I004", listaTappeVuota);
		assertEquals(0, itinerarioVuoto.getListaTappeNonAttraversate().size());
	}

	@Test
	public void testListaTappeNonAttraversateUnElemento() {
		ArrayList<Tappa> listaTappeUnElemento = new ArrayList<>();
		listaTappeUnElemento.add(tappa1);
		Itinerario itinerarioUnElemento = new Itinerario("I005", listaTappeUnElemento);
		assertEquals(1, itinerarioUnElemento.getListaTappeNonAttraversate().size());
	}

	@Test
	public void testListaTappeNonAttraversateMoltiElementi() {
		assertEquals(2, itinerario.getListaTappeNonAttraversate().size());
	}

	// Casi Of-By-One per ItinerarioDAO
	@Test
	public void testSelectByIdNessunItinerario() {
		Itinerario itinerarioNonTrovato = new Itinerario("I999");
		Itinerario itinerarioTrovato = itinerarioDAO.selectById(itinerarioNonTrovato);
		assertNull(itinerarioTrovato);
	}

	@Test
	public void testSelectByIdUnItinerario() {
		Itinerario itinerarioTrovato = itinerarioDAO.selectById(itinerario);
		assertNotNull(itinerarioTrovato);
		assertEquals(itinerario.getIdItinerario(), itinerarioTrovato.getIdItinerario());
	}
}
