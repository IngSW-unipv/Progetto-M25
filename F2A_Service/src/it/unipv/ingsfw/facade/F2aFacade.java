package it.unipv.ingsfw.facade;

import java.util.Properties;

import it.unipv.ingsfw.facade.capo.ConcreteCapoFacade;
import it.unipv.ingsfw.facade.lavaggio.ConcreteLavaggioFacade;
import it.unipv.ingsfw.facade.lavorazioneCapi.ConcreteCatenaLavorazioneFacade;
import it.unipv.ingsfw.facade.lavorazioneCapi.ConcreteStazioneLavoroFacade;
import it.unipv.ingsfw.facade.negozio.ConcreteNegozioFacade;
import it.unipv.ingsfw.facade.tickets.ConcreteItinerarioFacade;
import it.unipv.ingsfw.facade.tickets.ConcreteMezzoFacade;
import it.unipv.ingsfw.facade.tickets.ConcreteTicketsFacade;
import it.unipv.ingsfw.facade.users.clienti.ConcreteClientiFacade;
import it.unipv.ingsfw.facade.users.dipendenti.ConcreteDipendentiFacade;

public class F2aFacade {

	private static final F2aFacade instance = new F2aFacade();
    public static F2aFacade getInstance() {
        return instance;
    }

    private ConcreteCatenaLavorazioneFacade catenaLavorazioneFacade;
    private ConcreteStazioneLavoroFacade stazioneLavoroFacade;
    private ConcreteNegozioFacade negozioFacade;
    private ConcreteItinerarioFacade itinerarioFacade;
    private ConcreteMezzoFacade mezzoFacade;
    private ConcreteTicketsFacade ticketsFacade;
    private ConcreteClientiFacade clientiFacade;
    private ConcreteDipendentiFacade dipendentiFacade;
    private ConcreteCapoFacade capoFacade;
    private ConcreteLavaggioFacade lavaggioFacade;
    

    private F2aFacade() {

        try{
            this.catenaLavorazioneFacade = (ConcreteCatenaLavorazioneFacade) loadClass("catenalavorazione").getDeclaredConstructor().newInstance();
            this.stazioneLavoroFacade = (ConcreteStazioneLavoroFacade) loadClass("stazionelavoro").getDeclaredConstructor().newInstance();
            this.negozioFacade = (ConcreteNegozioFacade) loadClass("negozio").getDeclaredConstructor().newInstance();
            this.itinerarioFacade = (ConcreteItinerarioFacade) loadClass("itinerario").getDeclaredConstructor().newInstance();
            this.mezzoFacade = (ConcreteMezzoFacade) loadClass("mezzo").getDeclaredConstructor().newInstance();
            this.ticketsFacade = (ConcreteTicketsFacade) loadClass("ticket").getDeclaredConstructor().newInstance();
            this.clientiFacade = (ConcreteClientiFacade) loadClass("cliente").getDeclaredConstructor().newInstance();
            this.dipendentiFacade = (ConcreteDipendentiFacade) loadClass("dipendente").getDeclaredConstructor().newInstance();
            this.capoFacade = (ConcreteCapoFacade) loadClass("dipendente").getDeclaredConstructor().newInstance();
            this.lavaggioFacade = (ConcreteLavaggioFacade) loadClass("dipendente").getDeclaredConstructor().newInstance();
        }catch (Exception e){
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
    }

    private Class<?> loadClass(String propertyName) throws Exception {
        Properties properties  = new Properties();
        properties.load(F2aFacade.class.getResourceAsStream("/main/resources/properties"));
        return Class.forName(properties.getProperty("facade."+propertyName));
    }

	

	public ConcreteCatenaLavorazioneFacade getCatenaLavorazioneFacade() {
		return catenaLavorazioneFacade;
	}

	public ConcreteStazioneLavoroFacade getStazioneLavoroFacade() {
		return stazioneLavoroFacade;
	}

	public ConcreteNegozioFacade getNegozioFacade() {
		return negozioFacade;
	}

	public ConcreteItinerarioFacade getItinerarioFacade() {
		return itinerarioFacade;
	}

	public ConcreteMezzoFacade getMezzoFacade() {
		return mezzoFacade;
	}

	public ConcreteTicketsFacade getTicketsFacade() {
		return ticketsFacade;
	}

	public ConcreteClientiFacade getClientiFacade() {
		return clientiFacade;
	}

	public ConcreteDipendentiFacade getDipendentiFacade() {
		return dipendentiFacade;
	}

	public ConcreteCapoFacade getCapoFacade() {
		return capoFacade;
	}

	public ConcreteLavaggioFacade getLavaggioFacade() {
		return lavaggioFacade;
	}
	
	

    
}
