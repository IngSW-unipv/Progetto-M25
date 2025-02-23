package it.unipv.ingsfw.facade;

import java.util.Properties;

import it.unipv.ingsfw.facade.capo.ConcreteCapoFacade;
import it.unipv.ingsfw.facade.gestioneNegozio.ConcreteGestioneNegozioFacade;
import it.unipv.ingsfw.facade.gestioneTicket.ConcreteGestioneTicketsFacade;
//import it.unipv.ingsfw.facade.gestioneTicket.ConcreteItinerarioFacade;
//import it.unipv.ingsfw.facade.gestioneTicket.ConcreteMezzoFacade;
//import it.unipv.ingsfw.facade.gestioneTicket.ConcreteTicketsFacade;
import it.unipv.ingsfw.facade.lavaggio.ConcreteLavaggioFacade;
//import it.unipv.ingsfw.facade.lavorazioneCapi.ConcreteCatenaLavorazioneFacade;
import it.unipv.ingsfw.facade.lavorazioneCapi.ConcreteLavorazioneCapiFacade;
//import it.unipv.ingsfw.facade.lavorazioneCapi.ConcreteLavorazioneFacade;
//import it.unipv.ingsfw.facade.lavorazioneCapi.ConcreteStazioneLavoroFacade;
//import it.unipv.ingsfw.facade.negozio.ConcreteNegozioFacade;
//import it.unipv.ingsfw.facade.users.clienti.ConcreteClientiFacade;
import it.unipv.ingsfw.facade.users.dipendenti.ConcreteDipendentiFacade;

public class F2aFacade {

	private static final F2aFacade instance = new F2aFacade();
    public static F2aFacade getInstance() {
        return instance;
    }

    //private ConcreteCatenaLavorazioneFacade catenaLavorazioneFacade;
    //private ConcreteStazioneLavoroFacade stazioneLavoroFacade;
    private ConcreteGestioneNegozioFacade gestioneNegozioFacade;
    //private ConcreteItinerarioFacade itinerarioFacade;
    //private ConcreteMezzoFacade mezzoFacade;
    private ConcreteGestioneTicketsFacade gestioneTicketsFacade;
    //private ConcreteClientiFacade clientiFacade;
    private ConcreteDipendentiFacade dipendentiFacade;
    private ConcreteCapoFacade capoFacade;
    private ConcreteLavaggioFacade lavaggioFacade;
    //private ConcreteLavorazioneFacade lavorazioneFacade;
    private ConcreteLavorazioneCapiFacade lavorazioneCapiFacade;
   

    private F2aFacade() {

        try{
            //this.catenaLavorazioneFacade = (ConcreteCatenaLavorazioneFacade) loadClass("catenalavorazione").getDeclaredConstructor().newInstance();
            //this.stazioneLavoroFacade = (ConcreteStazioneLavoroFacade) loadClass("stazionelavoro").getDeclaredConstructor().newInstance();
            this.gestioneNegozioFacade = (ConcreteGestioneNegozioFacade) loadClass("negozio").getDeclaredConstructor().newInstance();
            //this.itinerarioFacade = (ConcreteItinerarioFacade) loadClass("itinerario").getDeclaredConstructor().newInstance();
            //this.mezzoFacade = (ConcreteMezzoFacade) loadClass("mezzo").getDeclaredConstructor().newInstance();
            this.gestioneTicketsFacade = (ConcreteGestioneTicketsFacade) loadClass("ticket").getDeclaredConstructor().newInstance();
            //this.clientiFacade = (ConcreteClientiFacade) loadClass("cliente").getDeclaredConstructor().newInstance();
            this.dipendentiFacade = (ConcreteDipendentiFacade) loadClass("dipendente").getDeclaredConstructor().newInstance();
            this.capoFacade = (ConcreteCapoFacade) loadClass("capo").getDeclaredConstructor().newInstance();
            this.lavaggioFacade = (ConcreteLavaggioFacade) loadClass("lavaggio").getDeclaredConstructor().newInstance();
            //this.lavorazioneFacade = (ConcreteLavorazioneFacade) loadClass("lavorazione").getDeclaredConstructor().newInstance();
            this.lavorazioneCapiFacade = (ConcreteLavorazioneCapiFacade) loadClass("lavorazionecapi").getDeclaredConstructor().newInstance();
        }catch (Exception e){
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
    }

    private Class<?> loadClass(String propertyName) throws Exception {
        Properties properties  = new Properties();
        properties.load(F2aFacade.class.getResourceAsStream("/main/resources/properties"));
        //System.out.println(Class.forName(properties.getProperty("facade."+propertyName)));
        return Class.forName(properties.getProperty("facade."+propertyName));   //java injection
    }

	

	/*public ConcreteCatenaLavorazioneFacade getCatenaLavorazioneFacade() {
		return catenaLavorazioneFacade;
	}

	public ConcreteStazioneLavoroFacade getStazioneLavoroFacade() {
		return stazioneLavoroFacade;
	}*/

	public ConcreteGestioneNegozioFacade getGestioneNegozioFacade() {
		return gestioneNegozioFacade;
	}

	/**
	public ConcreteItinerarioFacade getItinerarioFacade() {
		return itinerarioFacade;
	}

	public ConcreteMezzoFacade getMezzoFacade() {
		return mezzoFacade;
	}
	**/
	public ConcreteGestioneTicketsFacade getGestioneTicketsFacade() {
		return gestioneTicketsFacade;
	}

	/**
	public ConcreteClientiFacade getClientiFacade() {
		return clientiFacade;
	}**/

	public ConcreteDipendentiFacade getDipendentiFacade() {
		return dipendentiFacade;
	}

	public ConcreteCapoFacade getCapoFacade() {
		return capoFacade;
	}

	public ConcreteLavaggioFacade getLavaggioFacade() {
		return lavaggioFacade;
	}
	/*
	public ConcreteLavorazioneFacade getLavorazioneFacade() {
		return lavorazioneFacade;
	}*/

	public ConcreteLavorazioneCapiFacade getLavorazioneCapiFacade() {
		return lavorazioneCapiFacade;
	}
	
	

    
}
