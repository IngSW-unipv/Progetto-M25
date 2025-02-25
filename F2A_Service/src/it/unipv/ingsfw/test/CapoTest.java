package it.unipv.ingsfw.test;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.CapoDAO;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.users.Cliente;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class CapoTest {

    private Capo capoCompleto;
    private Capo capoStatoTipo;
    private Capo capoIdStato;
    private Capo capoNegozioNegozio;
    private Capo capoNullo;
    private Capo capoId;
    private Capo capoTipoLavaggio;
    private CapoDAO capoDAO;
    private SimpleDateFormat dateFormat;

    @Before
    public void setUp() throws ParseException {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dataRitiro = dateFormat.parse("2024-01-01");
        Date dataConsegna = dateFormat.parse("2024-01-10");
        Negozio negozioDeposito = new Negozio("N001");
        Negozio negozioConsegna = new Negozio("N002");
        Cliente cliente = new Cliente("CL001");

        capoCompleto = new Capo("C005", StatoCapo.IN_STORE, TipoLavaggio.BIANCHI, dataRitiro, dataConsegna, negozioConsegna, negozioDeposito, 25.0, cliente);
        capoStatoTipo = new Capo(StatoCapo.IN_LAVORAZIONE, TipoLavaggio.BIANCHI);
        capoIdStato = new Capo("C006", StatoCapo.IN_CONSEGNA);
        capoNegozioNegozio = new Capo(negozioDeposito, negozioConsegna);
        capoNullo = new Capo();
        capoId = new Capo("C007");
        capoTipoLavaggio = new Capo(TipoLavaggio.COLORATI);
        capoDAO = new CapoDAO();
    }

    @Test
    public void testCostruttoreCompleto() {
        assertEquals("C005", capoCompleto.getIdCapo());
        assertEquals(StatoCapo.IN_STORE, capoCompleto.getStatoCapo());
        assertEquals(TipoLavaggio.BIANCHI, capoCompleto.getTipoLavaggio());
        assertNotNull(capoCompleto.getDataRitiro());
        assertNotNull(capoCompleto.getDataUltimaConsegna());
        assertEquals("N001", capoCompleto.getNegozioDeposito().getIdTappa());
        assertEquals("N002", capoCompleto.getNegozioConsegna().getIdTappa());
        assertEquals(25.0, capoCompleto.getPrezzoScontato(), 0.0);
        assertEquals("CL001", capoCompleto.getCliente().getIdCliente());
    }

    @Test
    public void testCostruttoreStatoTipo() {
        assertNull(capoStatoTipo.getIdCapo());
        assertEquals(StatoCapo.IN_LAVORAZIONE, capoStatoTipo.getStatoCapo());
        assertEquals(TipoLavaggio.BIANCHI, capoStatoTipo.getTipoLavaggio());
    }

    @Test
    public void testCostruttoreIdStato() {
        assertEquals("C006", capoIdStato.getIdCapo());
        assertEquals(StatoCapo.IN_CONSEGNA, capoIdStato.getStatoCapo());
    }

    @Test
    public void testCostruttoreNegozioNegozio() {
        assertEquals("N001", capoNegozioNegozio.getNegozioDeposito().getIdTappa());
        assertEquals("N002", capoNegozioNegozio.getNegozioConsegna().getIdTappa());
    }

    @Test
    public void testCostruttoreNullo() {
        assertNull(capoNullo.getIdCapo());
    }

    @Test
    public void testCostruttoreId() {
        assertEquals("C007", capoId.getIdCapo());
    }

    @Test
    public void testCostruttoreTipoLavaggio() {
        assertEquals(TipoLavaggio.COLORATI, capoTipoLavaggio.getTipoLavaggio());
    }

    @Test
    public void testToString() {
        String expected = "Capo C005\nStato: IN_STORE\nTipoLavaggio: 1\nDataRitiro: Mon Jan 01 00:00:00 CET 2024\nDataUltimaConsegna: Wed Jan 10 00:00:00 CET 2024\nNegozioDeposito: N001\nNegozioConsegna: N002\nPrezzoScontato: 25.0\nIdCliente: CL001";
        assertEquals(expected, capoCompleto.toString());
    }

    // Test dei metodi DAO
    @Test
    public void testSelectAll() {
        ArrayList<Capo> capi = capoDAO.selectAll();
        assertNotNull(capi);
        assertTrue(capi.size() >= 0);
    }

    @Test
    public void testSelectCapoByStatoETipo() {
        ArrayList<Capo> capi = capoDAO.selectCapoByStatoETipo(capoStatoTipo);
        assertNotNull(capi);
    }

    @Test
    public void testUpdateStatoCapo() {
        capoCompleto.setStatoCapo(StatoCapo.CONSEGNATO);
        assertTrue(capoDAO.updateStatoCapo(capoCompleto));
    }

    @Test
    public void testUpdateStatoCapoByTappa() {
        capoCompleto.setStatoCapo(StatoCapo.IN_CONSEGNA);
        assertTrue(capoDAO.updateStatoCapoByTappa(capoCompleto));
    }

    @Test
    public void testGetNewIdCapo() {
        String newId = capoDAO.getNewIdCapo();
        assertNotNull(newId);
        assertTrue(newId.startsWith("C"));
    }

    @Test
    public void testInsertCapo() throws ParseException {
        Date dataRitiro = dateFormat.parse("2024-02-01");
        Date dataConsegna = dateFormat.parse("2024-02-10");
        Capo newCapo = new Capo(capoDAO.getNewIdCapo(), StatoCapo.IN_STORE, TipoLavaggio.COLORATI, dataRitiro, dataConsegna, new Negozio("N003"), new Negozio("N004"), 30.0, new Cliente("CL002"));
        assertTrue(capoDAO.insertCapo(newCapo));
    }

    @Test
    public void testSelectCapiDaRitirareByTappa() {
        ArrayList<Capo> capi = capoDAO.selectCapiDaRitirareByTappa(capoNegozioNegozio);
        assertNotNull(capi);
    }

    @Test
    public void testGetStatoCapoById() {
        assertNotNull(capoDAO.getStatoCapoById(capoCompleto));
    }
}