package it.unipv.ingsfw.test;

import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.lavorazioneCapi.TipologiaStazione;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.model.users.TipoOperatore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OperatoreTest {

    private Operatore operatore;
    private ObservableStazioneLavoro stazione1;
    private ObservableStazioneLavoro stazione2;

    @Before
    public void setUp() {
        operatore = new Operatore("D001", "Fabio", "Colombo", "clmfba08u45r218t", "fabio.colombo@f2aservice.com", "1234", 2500, TipoOperatore.RESPONSABILE_STAZIONE);
        stazione1 = new ObservableStazioneLavoro("S001", TipologiaStazione.LAVAGGIO, StatoStazione.READY, 100.0);
        stazione2 = new ObservableStazioneLavoro("S002", TipologiaStazione.ASCIUGATURA, StatoStazione.READY, 100.0);
        ArrayList<ObservableStazioneLavoro> stazioni = new ArrayList<>();
        stazioni.add(stazione1);
        stazioni.add(stazione2);
        operatore.getStazioniAssociate().addAll(stazioni);
    }

    @Test
    public void testGetTipoOperatore() {
        assertEquals(TipoOperatore.RESPONSABILE_STAZIONE, operatore.getTipoOperatore());
    }

    @Test
    public void testSetTipoOperatore() {
        operatore.setTipoOperatore(TipoOperatore.MANUTENTORE);
        assertEquals(TipoOperatore.MANUTENTORE, operatore.getTipoOperatore());
    }

    @Test
    public void testGetStazioniAssociate() {
        // Inizialmente la lista dovrebbe essere vuota
    	operatore.getStazioniAssociate().clear();
        assertTrue(operatore.getStazioniAssociate().isEmpty());
    }

    @Test
    public void testSetStazioniAssociate() {
        assertEquals(2, operatore.getStazioniAssociate().size());
    }

    @Test
    public void testToString() {
        String expected = "Dipendente D001\nNome: Fabio\nCognome: Colombo\ncf: clmfba08u45r218t\nEmail: fabio.colombo@f2aservice.com\nPassword: 1234\nTipoOperatore: RESPONSABILE_STAZIONE";
        assertEquals(expected, operatore.toString());
    }
    
    @Test
    public void testAvviaStazione() throws Exception {
        // Stazione con dei capi da lavorare
        stazione1.setListaCapiDaLavorare(new ArrayList<>());
        int result = operatore.avviaStazione(0);
        assertTrue(result == 1 || result == -1); // Può essere 1 (avviata) o -1 (guasto)
    }

    @Test
    public void testFermaStazione() throws Exception {
        int result = operatore.fermaStazione(0);
        assertEquals(1, result);
    }

    @Test
    public void testVerificaCredenzialiAccesso() {
        // Operatore esistente nel database
        boolean result = operatore.verificaCredenzialiAccesso("fabio.colombo@f2aservice.com", "1234");
        assertTrue(result);
    }

    @Test
    public void testCorrezioneGuasto() {
        // Stazione sia in stato di guasto
        stazione1.setStatoStazione(StatoStazione.MAINTENANCE);
        boolean result = operatore.correzioneGuasto(0);
        assertTrue(result);
    }

}
