package it.unipv.ingsfw.test;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.model.users.Corriere;
import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.StatoCorriere;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class CorriereTest {

	    private Corriere corriereCompleto;
	    private Corriere corriereEmailPassword;
	    private Corriere corriereIdDipendente;
	    private Corriere corriereNullo;
        private DipendenteDAO dao;
        private Corriere corriereTest;

	    @Before
	    public void setUp() {
	        corriereCompleto = new Corriere("123", "Mario", "Rossi", "RSSMRA80A01L219X", "mario.rossi@example.com", "password123", 1500, StatoCorriere.LIBERO);
	        corriereEmailPassword = new Corriere("giovanni.neri@email.com", "pwd111");
	        corriereIdDipendente = new Corriere("456");
	        corriereNullo = new Corriere();
	        dao = new DipendenteDAO();
            corriereTest = new Corriere("D003"); // Usa un ID valido per i test
	    }

	    @Test
	    public void testCostruttoreCompleto() {
	        assertEquals("123", corriereCompleto.getIdDipendente());
	        assertEquals("Mario", corriereCompleto.getNome());
	        assertEquals("Rossi", corriereCompleto.getCognome());
	        assertEquals("RSSMRA80A01L219X", corriereCompleto.getCf());
	        assertEquals("mario.rossi@example.com", corriereCompleto.getEmail());
	        assertEquals("password123", corriereCompleto.getPassword());
	        assertEquals(1500, corriereCompleto.getStipendio());
	        assertEquals(StatoCorriere.LIBERO, corriereCompleto.getStatoCorriere());
	    }

	    @Test
	    public void testCostruttoreEmailPassword() {
	        assertEquals("giovanni.neri@email.com", corriereEmailPassword.getEmail());
	        assertEquals("pwd111", corriereEmailPassword.getPassword());
	    }

	    @Test
	    public void testCostruttoreIdDipendente() {
	        assertEquals("456", corriereIdDipendente.getIdDipendente());
	        assertNull(corriereIdDipendente.getNome());
	        assertNull(corriereIdDipendente.getCognome());
	        assertNull(corriereIdDipendente.getCf());
	        assertNull(corriereIdDipendente.getEmail());
	        assertNull(corriereIdDipendente.getPassword());
	        assertEquals(0, corriereIdDipendente.getStipendio());
	        assertNull(corriereIdDipendente.getStatoCorriere());
	    }

	    @Test
	    public void testCostruttoreNullo() {
	        assertNull(corriereNullo.getIdDipendente());
	        assertNull(corriereNullo.getEmail());
	        assertNull(corriereNullo.getPassword());
	    }

	    @Test
	    public void testSetGetStatoCorriere() {
	        Corriere corriere = new Corriere();
	        corriere.setStatoCorriere(StatoCorriere.OCCUPATO);
	        assertEquals(StatoCorriere.OCCUPATO, corriere.getStatoCorriere());
	    }

	    @Test
	    public void testToString() {
	        Corriere corriere = new Corriere("789", "Anna", "Verdi", "VRDANNA90B02M123Y", "anna.verdi@example.com", "securePass", 2000, StatoCorriere.LIBERO);
	        String expected = "Dipendente 789\nNome: Anna\nCognome: Verdi\ncf: VRDANNA90B02M123Y\nEmail: anna.verdi@example.com\nPassword: securePass\nStatoCorriere: LIBERO";
	        assertEquals(expected, corriere.toString());
	    }


	        @Test
	        public void testSelectCorrieri() {
	            assertNotNull(dao.selectCorrieri());
	        }

	        @Test
	        public void testSelectCorriereById() {
	            Corriere result = dao.selectCorriereById(corriereTest);
	            assertNotNull(result);
	            assertEquals(corriereTest.getIdDipendente(), result.getIdDipendente());
	        }

	        @Test
	        public void testSelectCorrieriLiberi() {
	            for (Corriere c : dao.selectCorrieriLiberi()) {
	                assertEquals(StatoCorriere.LIBERO, c.getStatoCorriere());
	            }
	        }

	        @Test
	        public void testSelectByEmailPassword() {
	            assertTrue(dao.selectByEmailPassword(new Corriere("giovanni.neri@email.com", "pwd111"))); // Usa credenziali valide
	            assertFalse(dao.selectByEmailPassword(new Corriere("emailNonEsistente", "passwordNonEsistente")));
	        }

	        @Test
	        public void testSelectCorriereByEmailPassword() {
	            Corriere result = dao.selectCorriereByEmailPassword(new Corriere("giovanni.neri@email.com", "pwd111")); // Usa credenziali valide
	            assertNotNull(result);
	            assertEquals("giovanni.neri@email.com", result.getEmail());
	        }

	        @Test
	        public void testSelectIdByEmailPassword() {
	            assertEquals(corriereTest.getIdDipendente(), dao.selectIdByEmailPassword(new Corriere("giovanni.neri@email.com", "pwd111"))); // Usa credenziali valide
	        }

	        @Test
	        public void testSelectTipoDipendenteById() {
	            assertEquals("CORRIERE", dao.selectTipoDipendenteById(corriereTest));
	        }
}