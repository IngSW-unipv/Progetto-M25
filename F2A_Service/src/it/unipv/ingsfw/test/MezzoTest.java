package it.unipv.ingsfw.test;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.model.tickets.Mezzo;

import static org.junit.Assert.*;

public class MezzoTest {

    private Mezzo mezzoCompleto;
    private Mezzo mezzoId;

    @Before
    public void setUp() {
        mezzoCompleto = new Mezzo("M123", 50, true);
        mezzoId = new Mezzo("M456");
    }

    @Test
    public void testCostruttoreCompleto() {
        assertEquals("M123", mezzoCompleto.getIdMezzo());
        assertEquals(50, mezzoCompleto.getCapienza());
        assertTrue(mezzoCompleto.isDisponibilita());
    }

    @Test
    public void testCostruttoreId() {
        assertEquals("M456", mezzoId.getIdMezzo());
        assertEquals(0, mezzoId.getCapienza());
        assertFalse(mezzoId.isDisponibilita());
    }

    @Test
    public void testGetSetIdMezzo() {
        mezzoCompleto.setIdMezzo("M789");
        assertEquals("M789", mezzoCompleto.getIdMezzo());
    }

    @Test
    public void testGetSetCapienza() {
        mezzoCompleto.setCapienza(100);
        assertEquals(100, mezzoCompleto.getCapienza());
    }

    @Test
    public void testGetSetDisponibilita() {
        mezzoCompleto.setDisponibilita(false);
        assertFalse(mezzoCompleto.isDisponibilita());
    }

    @Test
    public void testToString() {
        String expected = "\n Id Mezzo: M123\n Capienza: 50";
        assertEquals(expected, mezzoCompleto.toString());
    }

    @Test
    public void testGetMezzoByIDMezzoEsistente() {
        // Test su database con id "M001"
        Mezzo mezzo = new Mezzo("M001");
        Mezzo risultato = mezzo.getMezzoByID();
        assertNotNull(risultato);
        assertEquals("M001", risultato.getIdMezzo());
    }

    @Test
    public void testGetMezzoByIDMezzoNonEsistente() {
        Mezzo mezzo = new Mezzo("M005");
        Mezzo risultato = mezzo.getMezzoByID();
        assertNull(risultato);
    }

}