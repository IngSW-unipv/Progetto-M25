package it.unipv.ingsfw.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.lavorazioneCapi.TipologiaStazione;

public class CatenaLavorazioneTest {

    private CatenaLavorazione catena;
    private CatenaLavorazione catena1;
    private ObservableStazioneLavoro stazione1;
    private ObservableStazioneLavoro stazione2;
    private ObservableStazioneLavoro stazione3;
    private ObservableStazioneLavoro stazione4;
    private CatenaLavorazioneDAO catenaDAO;
    private CatenaLavorazione catenaTestDAO1;
    private CatenaLavorazione catenaTestDAO2;

    @Before
    public void setUp() {
        catena = new CatenaLavorazione("CAT010", TipoLavaggio.COTONE);
        catena1 = new CatenaLavorazione("CAT011", TipoLavaggio.COTONE);
        stazione1 = new ObservableStazioneLavoro("S010", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0);
        stazione2 = new ObservableStazioneLavoro("S011", TipologiaStazione.ASCIUGATURA, StatoStazione.READY, 100.0);
        stazione3 = new ObservableStazioneLavoro("S012", TipologiaStazione.STIRATURA, StatoStazione.READY, 100.0);
        stazione4 = new ObservableStazioneLavoro("S013", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0);
        catenaDAO = new CatenaLavorazioneDAO();
        catenaTestDAO1 = new CatenaLavorazione("CAT010", TipoLavaggio.COTONE);
        catenaTestDAO2 = new CatenaLavorazione("CAT014", TipoLavaggio.LANA);
    }

    // Test per CatenaLavorazione
    @Test
    public void testAddStazioneLavoro_Success() {
    	assertTrue(catena1.addStazioneLavoro(stazione4));
        assertEquals(1, catena1.getListaStazioni().size());
    }

    @Test
    public void testAddStazioneLavoro_LimitReached() {
        catena.addStazioneLavoro(stazione1);
        catena.addStazioneLavoro(stazione2);
        catena.addStazioneLavoro(stazione3);
        assertFalse(catena.addStazioneLavoro(new ObservableStazioneLavoro("S013", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0)));
        assertEquals(3, catena.getListaStazioni().size());
    }

    @Test
    public void testAddStazioneLavoro_DuplicateType() {
        catena.addStazioneLavoro(stazione1);
        assertFalse(catena.addStazioneLavoro(new ObservableStazioneLavoro("S010", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0)));
        assertEquals(1, catena.getListaStazioni().size());
    }

    @Test
    public void testAddStazioneLavoro_PelleType() {
        catena.setTipoLavaggio(TipoLavaggio.PELLE);
        assertFalse(catena.addStazioneLavoro(stazione1));
        assertEquals(0, catena.getListaStazioni().size());
    }

    @Test
    public void testRiempimentoListaStazioniDaDB_Success() {
        catena.addStazioneLavoro(stazione1);
        catena.getListaStazioni().clear();
        assertTrue(catena.riempimentoListaStazioniDaDB());
        assertFalse(catena.getListaStazioni().isEmpty());
    }

    @Test
    public void testRiempimentoListaStazioniDaDB_AlreadyFilled() {
        catena.addStazioneLavoro(stazione1);
        assertFalse(catena.riempimentoListaStazioniDaDB());
        assertEquals(1, catena.getListaStazioni().size());
    }

    // Test per CatenaLavorazioneDAO
    @Test
    public void testInsertCatena_Success() {
        assertTrue(catenaDAO.insertCatena(catenaTestDAO2));
    }


    @Test
    public void testSelectStazioniByCatena() {
        assertFalse(catenaDAO.selectStazioniByCatena(catenaTestDAO1).isEmpty());
    }

    @Test
    public void testSelectCatenaByStazione() {
        ObservableStazioneLavoro stazione = new ObservableStazioneLavoro("S001", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0);
        catenaDAO.insertCatena(catenaTestDAO1);
        assertNotNull(catenaDAO.selectCatenaByStazione(stazione));
    }

    @Test
    public void testGetNewIdCatena() {
        String newId = catenaDAO.getNewIdCatena();
        assertNotNull(newId);
        assertTrue(newId.startsWith("CAT"));
    }
}