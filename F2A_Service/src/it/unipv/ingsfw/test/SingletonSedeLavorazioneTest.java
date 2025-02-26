package it.unipv.ingsfw.test;

import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.SingletonSedeLavorazione;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SingletonSedeLavorazioneTest {

    @Test
    public void testGetInstance() {
        SingletonSedeLavorazione instance1 = SingletonSedeLavorazione.getInstance();
        SingletonSedeLavorazione instance2 = SingletonSedeLavorazione.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetIdSede() {
        SingletonSedeLavorazione instance = SingletonSedeLavorazione.getInstance();
        assertEquals("S001", instance.getIdSede());
    }

    @Test
    public void testSetIdSede() {
        SingletonSedeLavorazione instance = SingletonSedeLavorazione.getInstance();
        instance.setIdSede("S002");
        assertEquals("S002", instance.getIdSede());
    }

    @Test
    public void testGetListaCateneVuota() {
        SingletonSedeLavorazione instance = SingletonSedeLavorazione.getInstance();
        //instance.setListaCatene(new ArrayList<>());
        assertTrue(instance.getListaCatene().isEmpty());
    }

    @Test
    public void testGetListaCateneUnElemento() {
        SingletonSedeLavorazione instance = SingletonSedeLavorazione.getInstance();
        ArrayList<CatenaLavorazione> listaCatene = new ArrayList<>();
        CatenaLavorazione catena1 = new CatenaLavorazione("C001");
        listaCatene.add(catena1);
        instance.getListaCatene().addAll(listaCatene);
        assertEquals(1, instance.getListaCatene().size());
    }

    @Test
    public void testGetListaCateneMoltiElementi() {
        SingletonSedeLavorazione instance = SingletonSedeLavorazione.getInstance();
        ArrayList<CatenaLavorazione> listaCatene = new ArrayList<>();
        CatenaLavorazione catena1 = new CatenaLavorazione("C001");
        CatenaLavorazione catena2 = new CatenaLavorazione("C002");
        listaCatene.add(catena1);
        listaCatene.add(catena2);
        instance.getListaCatene().addAll(listaCatene);
        assertEquals(2, instance.getListaCatene().size());
    }

}
