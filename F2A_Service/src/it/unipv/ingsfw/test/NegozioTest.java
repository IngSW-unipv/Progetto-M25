package it.unipv.ingsfw.test;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.NegozioDAO;
import it.unipv.ingsfw.model.negozio.Totem;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NegozioTest {

    private NegozioDAO negozioDAO;

    @Before
    public void setUp() {
        negozioDAO = NegozioDAO.getInstance();
    }

    @Test
    public void testSelectAll() {
        ArrayList<Negozio> negozi = negozioDAO.selectAll();
        assertNotNull(negozi);
        assertFalse(negozi.isEmpty());

        for (Negozio negozio : negozi) {
            assertNotNull(negozio.getIdTappa());
            assertNotNull(negozio.getIndirizzo());
        }
    }

    @Test
    public void testInsertNegozio() {
        // Il metodo insertNegozio restituisce sempre false, quindi testiamo che sia effettivamente false.
        Negozio nuovoNegozio = new Negozio("N999", null, "Via Test 123");
        assertFalse(negozioDAO.insertNegozio(nuovoNegozio));
    }

    @Test
    public void testNegozioConstructorWithId() {
        Negozio negozio = new Negozio("N001");
        assertNotNull(negozio.getIdTappa());
        assertEquals("N001", negozio.getIdTappa());
        assertNull(negozio.getIndirizzo());
    }

    @Test
    public void testNegozioConstructorWithAllParams() {
        Negozio negozio = new Negozio("N002", null, "Via Roma 456");
        assertNotNull(negozio.getIdTappa());
        assertEquals("N002", negozio.getIdTappa());
        assertEquals("Via Roma 456", negozio.getIndirizzo());
    }

    @Test
    public void testCreaTotem() {
        Negozio negozio = new Negozio("N003", null, "Via Milano 789");
        ArrayList<Capo> listaCapi = new ArrayList<>();
        Capo capo = new Capo("C001", StatoCapo.IN_STORE, TipoLavaggio.BIANCHI, null, null, null, null, 25.0, null);
        listaCapi.add(capo);
        Totem totem = negozio.creaTotem(listaCapi);
        assertNotNull(totem);
        assertEquals(1, totem.getListaCapiDepositati().size());
    }


    @Test
    public void testToString() {
        Negozio negozio = new Negozio("N004", null, "Via Napoli 101");
        String expectedString = "Negozio N004\nIndirizzo: Via Napoli 101\n";
        assertEquals(expectedString, negozio.toString());
    }
}