package it.unipv.ingsfw.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.scontistica.TotemContext;
import it.unipv.ingsfw.model.users.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TotemTest {

    private Totem totem;
    private Cliente clienteValido;
    private Cliente clienteNonValido;
    private Capo capoValido;
    private double costo;
    @Before
    public void setUp() throws ParseException {
    	costo=25;
        totem = new Totem();
        clienteValido = new Cliente("CL000", "Stefano", "Rossi", "RSSSTF23Y45R304I", "Admin@f2aService.com", "Admin");
        clienteNonValido = new Cliente("CL999", "Cliente", "NonValido", "NVLCLT00A00X000Z", "nonvalido@example.com", "wrongpass");
        capoValido = new Capo("C066", StatoCapo.IN_STORE, TipoLavaggio.BIANCHI, null, new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-31"), new Negozio("N001"), new Negozio("N002"), costo, clienteValido);
        totem.setCliente(clienteValido);
    }

    @Test
    public void testVerificaCredenzialiAccessoValido() {
        totem.setCliente(clienteValido);
        assertTrue(totem.verificaCredenzialiAccesso());
    }

    @Test
    public void testVerificaCredenzialiAccessoNonValido() {
        totem.setCliente(clienteNonValido);
        assertFalse(totem.verificaCredenzialiAccesso());
    }

    @Test
    public void testGetNegoziAttivi() {
        assertNotNull(totem.getNegoziAttivi());
    }

    @Test
    public void testGetTipologiaLavaggi() {
        assertNotNull(totem.getTipologiaLavaggi());
    }

    @Test //per funzionare modifica codice capo, in modo che non ci sia su database
    public void testGeneraPrenotazioneCapoValido() throws ParseException {
        totem.setCliente(clienteValido);
        assertTrue(totem.generaPrenotazioneCapo(capoValido));
    }

    @Test
    public void testCfValido() {
        assertTrue(totem.cfValido("RSSMRA80A01L219X"));
        assertFalse(totem.cfValido("RSSMRA80A01L219"));
    }

    @Test
    public void testMailVuota() {
        assertFalse(totem.mailVuota("mario.rossi@example.com"));
        assertTrue(totem.mailVuota(null));
        assertTrue(totem.mailVuota(""));
    }

    @Test
    public void testPwValida() {
        assertTrue(totem.pwValida("Admin1234"));
        //per essere valida deve essere di almeno 8 caratteri
        assertFalse(totem.pwValida(null));
        assertFalse(totem.pwValida(""));
        assertFalse(totem.pwValida("short"));
    }

    @Test
    public void testIdValido() {
        assertTrue(totem.idValido("CL001"));
        assertTrue(totem.idValido("Cl001"));
        assertTrue(totem.idValido("cL001"));
        assertTrue(totem.idValido("cl001"));
        assertFalse(totem.idValido(null));
        assertFalse(totem.idValido(""));
        assertFalse(totem.idValido("CL1"));
        assertFalse(totem.idValido("CL0001"));
        assertFalse(totem.idValido("cl001a"));
        assertFalse(totem.idValido("CLAAA"));
    }

    @Test
    public void testDataValida() {
        assertTrue(totem.dataValida("2025-12-31"));
        //ricordati per essere valida, deve essere almeno tre giorni in piu della data attuale
        assertFalse(totem.dataValida(null));
        assertFalse(totem.dataValida(""));
        assertFalse(totem.dataValida("2023-12-31"));
        assertFalse(totem.dataValida("31/12/2024"));
        assertFalse(totem.dataValida("2024-13-01"));
        assertFalse(totem.dataValida("2024-01-32"));
        assertFalse(totem.dataValida("2024-02-28"));
    }

    @Test
    public void testIdCapoValido() {
        assertTrue(totem.idCapoValido("C001"));
        assertTrue(totem.idCapoValido("c001"));
        assertFalse(totem.idCapoValido(null));
        assertFalse(totem.idCapoValido(""));
        assertFalse(totem.idCapoValido("C1"));
        assertFalse(totem.idCapoValido("C0001"));
        assertFalse(totem.idCapoValido("c00a1"));
        assertFalse(totem.idCapoValido("CAAA"));
    }
}