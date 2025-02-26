package it.unipv.ingsfw.test;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.model.users.Corriere;
import it.unipv.ingsfw.model.users.Dipendente;
import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.model.users.StatoCorriere;
import it.unipv.ingsfw.model.users.TipoOperatore;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class DipendenteDAOTest {

    private DipendenteDAO dipendenteDAO;
    private Corriere corriereValido;
    private Operatore operatoreValido;

    @Before
    public void setUp() {
        dipendenteDAO = DipendenteDAO.getInstance();
        corriereValido = new Corriere("D003", "Giovanni", "Neri", "666666666", "giovanni.neri@email.com", "pwd111", 1800, StatoCorriere.LIBERO);
        operatoreValido = new Operatore("D004", "Sara", "Gialli", "777777777", "sara.gialli@email.com", "pwd222", 2000, TipoOperatore.MANUTENTORE);
    }

    @Test
    public void testSelectAll() {
        assertNotNull(dipendenteDAO.selectAll());
    }

    @Test
    public void testSelectCorrieri() {
        assertNotNull(dipendenteDAO.selectCorrieri());
    }

    @Test
    public void testSelectCorriereByIdCorriereEsistente() {
        Corriere risultato = dipendenteDAO.selectCorriereById(corriereValido);
        assertNotNull(risultato);
        assertEquals(corriereValido.getIdDipendente(), risultato.getIdDipendente());
    }

    @Test
    public void testSelectCorriereByIdCorriereNonEsistente() {
        Corriere corriereNonEsistente = new Corriere("D999");
        Corriere risultato = dipendenteDAO.selectCorriereById(corriereNonEsistente);
        assertNull(risultato.getNome());
    }

    @Test
    public void testSelectCorrieriLiberi() {
        assertNotNull(dipendenteDAO.selectCorrieriLiberi());
    }

    @Test
    public void testSelectResponsabiliStazioneNonAssegnati() {
        assertNotNull(dipendenteDAO.selectResponsabiliStazioneNonAssegnati());
    }

    @Test
    public void testSelectManutentoriNonAssegnati() {
        assertNotNull(dipendenteDAO.selectManutentoriNonAssegnati());
    }

    @Test
    public void testSelectByTipoOperatore() {
        assertNotNull(dipendenteDAO.selectByTipoOperatore(operatoreValido));
    }

    @Test
    public void testGetNewIdDipendente() {
        assertNotNull(dipendenteDAO.getNewIdDipendente());
    }

    @Test
    public void testInsertDipendente() {
        Dipendente nuovoDipendente = new Corriere("D026", "Test", "Dipendente", "TSTDPN00A00X000Z", "test.dipendente@example.com", "testpassword", 1000, StatoCorriere.LIBERO);
        assertTrue(dipendenteDAO.insertDipendente(nuovoDipendente));
    }

    @Test
    public void testSelectByEmailPasswordValido() {
        assertTrue(dipendenteDAO.selectByEmailPassword(corriereValido));
    }


    @Test
    public void testSelectCorriereByEmailPasswordValido() {
        assertNotNull(dipendenteDAO.selectCorriereByEmailPassword(corriereValido));
    }

    @Test
    public void testSelectCorriereByEmailPasswordNonValido() {
        Corriere corriereNonValido = new Corriere("email.non.valida@example.com", "password.non.valida");
        assertNull(dipendenteDAO.selectCorriereByEmailPassword(corriereNonValido).getNome());
    }

    @Test
    public void testSelectIdByEmailPasswordValido() {
        assertNotNull(dipendenteDAO.selectIdByEmailPassword(corriereValido));
    }


    @Test
    public void testSelectTipoOperatoreByIdValido() {
        assertNotNull(dipendenteDAO.selectTipoOperatoreById(operatoreValido));
    }

    @Test
    public void testSelectTipoOperatoreByIdNonValido() {
        Operatore operatoreNonValido = new Operatore("D999");
        assertNull(dipendenteDAO.selectTipoOperatoreById(operatoreNonValido));
    }

    @Test
    public void testSelectTipoDipendenteByIdValido() {
        assertNotNull(dipendenteDAO.selectTipoDipendenteById(corriereValido));
    }

    @Test
    public void testSelectTipoDipendenteByIdNonValido() {
        Dipendente dipendenteNonValido = new Corriere("D999");
        assertNull(dipendenteDAO.selectTipoDipendenteById(dipendenteNonValido));
    }

    @Test
    public void testSelectOperatoreByEmailPassword() {
        //Questo metodo è vuoto nella classe DAO, quindi il test deve essere adeguato
        assertNull(dipendenteDAO.selectOperatoreByEmailPassword(operatoreValido));
    }
}