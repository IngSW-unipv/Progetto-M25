package it.unipv.ingsfw.test;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.facade.lavorazioneCapi.ConcreteLavorazioneCapiFacade;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.lavorazioneCapi.TipologiaStazione;
import it.unipv.ingsfw.model.users.Operatore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ObservableStazioneLavoroTest {

    private ObservableStazioneLavoro stazione;
    private ObservableStazioneLavoro stazione2;
    private ConcreteLavorazioneCapiFacade stazioneDAO;
    private Capo capo;
    private Operatore operatore;

    @Before
    public void setUp() {
        stazione = new ObservableStazioneLavoro("S001", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0);
        stazione2 = new ObservableStazioneLavoro("S004", TipologiaStazione.ASCIUGATURA, StatoStazione.READY, 100.0);
        stazioneDAO = F2aFacade.getInstance().getLavorazioneCapiFacade();
        capo = new Capo(StatoCapo.IN_LAVORAZIONE, TipoLavaggio.BIANCHI);
        operatore = new Operatore("D001");
    }

    @Test
    public void testSetListaCapiDaLavorare() {
        boolean result = stazione2.setListaCapiDaLavorare(capo);
        assertTrue(result);
        assertFalse(stazione2.getListaCapiDaLavorare().isEmpty());
    }

    @Test
    public void testMessaInLavorazione() {
        stazione.messaInLavorazione(0);
        assertEquals(StatoStazione.WORKING, stazione.getStatoStazione());
    }

    @Test
    public void testMessaInStandBy() {
        stazione.messaInStandBy(0);
        assertEquals(StatoStazione.READY, stazione.getStatoStazione());
    }

    @Test
    public void testMessaInManutenzione() {
        stazione.messaInManutenzione(0);
        assertEquals(StatoStazione.MAINTENANCE, stazione.getStatoStazione());
    }

    @Test
    public void testCheckPresenzaCapi() {
        boolean result = stazione.checkPresenzaCapi();
        assertTrue(result);
    }

    @Test
    public void testSvuotaStazione() {
        stazione.setListaCapiDaLavorare(capo);
        stazione.svuotaStazione();
        assertTrue(stazione.getListaCapiDaLavorare().isEmpty());
    }

    @Test
    public void testRemoveCapiStazione() {
        stazione.setListaCapiDaLavorare(capo);
        boolean result = stazione.removeCapiStazione();
        assertTrue(result);
    }

    @Test
    public void testAssegnazionePostGuasto() {
        stazione.assegnazionePostGuasto(0);
        assertEquals(StatoStazione.MAINTENANCE, stazione.getStatoStazione());
    }

    @Test
    public void testSelectAll() {
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectAllStazioni();
        assertFalse(stazioni.isEmpty());
    }

    @Test
    public void testSelectByStato() {
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectByStato(stazione);
        assertFalse(stazioni.isEmpty());
    }

    @Test
    public void testAssegnazioneResponsabileStazioneLibero() {
        boolean result = stazioneDAO.assegnazioneResponsabileStazioneLibero(stazione);
        assertTrue(result);
    }

    @Test
    public void testAssegnazioneManutentoreLibero() {
        stazione.setStatoStazione(StatoStazione.MAINTENANCE);
        boolean result = stazioneDAO.assegnazioneManutentoreLibero(stazione);
        assertTrue(result);
    }

    @Test
    public void testSelectStazioniReadyNonAssegnate() {
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniReadyNonAssegnate();
        assertFalse(stazioni.isEmpty());
    }

    @Test
    public void testSelectStazioniMaintenanceNonAssegnate() {
        stazione.setStatoStazione(StatoStazione.MAINTENANCE);
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniMaintenanceNonAssegnate();
        assertFalse(stazioni.isEmpty());
    }

    @Test
    public void testSelectStazioniReadyNonAssegnatePerTipo() {
        ObservableStazioneLavoro stazione = stazioneDAO.selectStazioniReadyNonAssegnatePerTipo(new ObservableStazioneLavoro(TipologiaStazione.LAVAGGIO));
        assertNotNull(stazione);
    }

    @Test
    public void testAssegnazioneOperatoreNoto() {
        boolean result = stazioneDAO.assegnazioneOperatoreNoto(stazione, operatore);
        assertTrue(result);
    }

    @Test
    public void testChangeStatoStazione() {
        stazione.setStatoStazione(StatoStazione.WORKING);
        boolean result = stazioneDAO.changeStatoStazione(stazione);
        assertTrue(result);
    }

    @Test
    public void testSelectStazioniByOperatore() {
        stazioneDAO.assegnazioneOperatoreNoto(stazione, operatore);
        ArrayList<ObservableStazioneLavoro> lista = stazioneDAO.selectStazioniByOperatore(operatore);
        assertFalse(lista.isEmpty());
    }

    @Test
    public void testChiusuraAssegnazioneOperatoreNoto() {
        stazioneDAO.assegnazioneOperatoreNoto(stazione, operatore);
        boolean result = stazioneDAO.chiusuraAssegnazioneOperatoreNoto(stazione, operatore);
        assertTrue(result);
    }
    
    // Casi Off-By-One per ObservableStazioneLavoro
    @Test
    public void testIdStazioneLimiteInferiore() {
        try {
            new ObservableStazioneLavoro("", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0);
            fail("Dovrebbe lanciare un'eccezione per idStazione vuoto.");
        } catch (IllegalArgumentException e) {
            // Test superato se l'eccezione viene lanciata
        }
    }

    @Test
    public void testIdStazioneLimiteSuperiore() {
        try {
            new ObservableStazioneLavoro("S00123456789", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0);
            fail("Dovrebbe lanciare un'eccezione per idStazione troppo lungo.");
        } catch (IllegalArgumentException e) {
            // Test superato se l'eccezione viene lanciata
        }
    }

    @Test
    public void testListaCapiDaLavorareVuota() {
        assertEquals(0, stazione.getListaCapiDaLavorare().size());
    }

    @Test
    public void testListaCapiDaLavorareUnElemento() {
        ArrayList<Capo> listaCapi = new ArrayList<>();
        listaCapi.add(capo);
        stazione.setListaCapiDaLavorare(listaCapi);
        assertEquals(1, stazione.getListaCapiDaLavorare().size());
    }

    @Test
    public void testListaCapiDaLavorareMoltiElementi() {
        ArrayList<Capo> listaCapi = new ArrayList<>();
        listaCapi.add(capo);
        listaCapi.add(new Capo(StatoCapo.IN_LAVORAZIONE, TipoLavaggio.COLORATI));
        stazione.setListaCapiDaLavorare(listaCapi);
        assertEquals(2, stazione.getListaCapiDaLavorare().size());
    }

    // Casi Off-By-One per ObservableStazioneLavoroDAO
   
    @Test
    public void testSelectStazioniByOperatoreNessunaStazione() {
        // No stazioni assegnate all'operatore nel database
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniByOperatore(operatore);
        assertEquals(0, stazioni.size());
    }

    @Test
    public void testSelectStazioniByOperatoreUnaStazione() {
        // Stazione assegnata all'operatore nel database
        stazioneDAO.assegnazioneOperatoreNoto(stazione, operatore);
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniByOperatore(operatore);
        assertEquals(1, stazioni.size());
    }

    @Test
    public void testSelectStazioniByOperatoreMolteStazioni() {
        // Più stazioni assegnate all'operatore nel database
        stazioneDAO.assegnazioneOperatoreNoto(stazione, operatore);
        stazioneDAO.assegnazioneOperatoreNoto(stazione2, operatore);
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniByOperatore(operatore);
        assertTrue(stazioni.size() > 1);
    }
    @Test
    public void testSelectAllNessunaStazione() {
        // No stazioni nel database
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectAllStazioni();
        assertEquals(0, stazioni.size());
    }

    @Test
    public void testSelectAllUnaStazione() {
        // Una stazione nel database
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectAllStazioni();
        assertEquals(1, stazioni.size());
    }

    @Test
    public void testSelectAllMolteStazioni() {
        // Più stazioni nel database
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectAllStazioni();
        assertTrue(stazioni.size() > 1);
    }

    @Test
    public void testSelectByStatoNessunaStazione() {
        // No stazioni con lo stato specificato
        ObservableStazioneLavoro stazioneFittizia = new ObservableStazioneLavoro(TipologiaStazione.LAVAGGIO);
        stazioneFittizia.setStatoStazione(StatoStazione.MAINTENANCE);
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectByStato(stazioneFittizia);
        assertEquals(0, stazioni.size());
    }

    @Test
    public void testSelectByStatoUnaStazione() {
        // Una stazione con lo stato specificato
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectByStato(stazione);
        assertEquals(1, stazioni.size());
    }

    @Test
    public void testSelectByStatoMolteStazioni() {
        // Più stazioni con lo stato specificato
        ObservableStazioneLavoro stazioneFittizia = new ObservableStazioneLavoro(TipologiaStazione.LAVAGGIO);
        stazioneFittizia.setStatoStazione(StatoStazione.READY);
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectByStato(stazioneFittizia);
        assertTrue(stazioni.size() > 1);
    }

    @Test
    public void testAssegnazioneResponsabileStazioneLiberoNessunResponsabile() {
        // No responsabili liberi nel database
        boolean result = stazioneDAO.assegnazioneResponsabileStazioneLibero(stazione);
        assertFalse(result);
    }

    @Test
    public void testAssegnazioneResponsabileStazioneLiberoUnResponsabile() {
        // Responsabile libero nel database
        boolean result = stazioneDAO.assegnazioneResponsabileStazioneLibero(stazione);
        assertTrue(result);
    }

    @Test
    public void testAssegnazioneManutentoreLiberoNessunManutentore() {
        // No manutentori liberi nel database
        stazione.setStatoStazione(StatoStazione.MAINTENANCE);
        boolean result = stazioneDAO.assegnazioneManutentoreLibero(stazione);
        assertFalse(result);
    }

    @Test
    public void testAssegnazioneManutentoreLiberoUnManutentore() {
        // Manutentore libero nel database
        // (potrebbe richiedere la modifica dei dati di test)
        stazione.setStatoStazione(StatoStazione.MAINTENANCE);
        boolean result = stazioneDAO.assegnazioneManutentoreLibero(stazione);
        assertTrue(result);
    }

    @Test
    public void testSelectStazioniReadyNonAssegnateNessunaStazione() {
        // Stazioni pronte e non assegnate nel database
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniReadyNonAssegnate();
        assertEquals(0, stazioni.size());
    }

    @Test
    public void testSelectStazioniReadyNonAssegnateUnaStazione() {
        // Una stazione pronta e non assegnata nel database
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniReadyNonAssegnate();
        assertEquals(1, stazioni.size());
    }

    @Test
    public void testSelectStazioniReadyNonAssegnateMolteStazioni() {
        // Più stazioni pronte e non assegnate nel database
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniReadyNonAssegnate();
        assertTrue(stazioni.size() > 1);
    }

    @Test
    public void testSelectStazioniMaintenanceNonAssegnateNessunaStazione() {
        // No stazioni in manutenzione e non assegnate nel database
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniMaintenanceNonAssegnate();
        assertEquals(0, stazioni.size());
    }

    @Test
    public void testSelectStazioniMaintenanceNonAssegnateUnaStazione() {
        // Una stazione in manutenzione e non assegnata nel database
        stazione.setStatoStazione(StatoStazione.MAINTENANCE);
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniMaintenanceNonAssegnate();
        assertEquals(1, stazioni.size());
    }

    @Test
    public void testSelectStazioniMaintenanceNonAssegnateMolteStazioni() {
        // Più stazioni in manutenzione e non assegnate nel database
        stazione.setStatoStazione(StatoStazione.MAINTENANCE);
        ArrayList<ObservableStazioneLavoro> stazioni = stazioneDAO.selectStazioniMaintenanceNonAssegnate();
        assertTrue(stazioni.size() > 1);
    }

    @Test
    public void testSelectStazioniReadyNonAssegnatePerTipoNessunaStazione() {
        // No stazioni pronte e non assegnate per il tipo specificato
        ObservableStazioneLavoro stazioneFittizia = new ObservableStazioneLavoro(TipologiaStazione.ASCIUGATURA);
        ObservableStazioneLavoro stazione = stazioneDAO.selectStazioniReadyNonAssegnatePerTipo(stazioneFittizia);
        assertNull(stazione);
    }

}