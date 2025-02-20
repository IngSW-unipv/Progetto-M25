package it.unipv.ingsfw.facade;

import java.util.Properties;

import it.unipv.ingsfw.facade.lavorazioneCapi.ICatenaLavorazioneFacade;
import it.unipv.ingsfw.facade.lavorazioneCapi.IStazioneLavoroFacade;
import it.unipv.ingsfw.facade.negozio.INegozioFacade;
import it.unipv.ingsfw.facade.tickets.IItinerarioFacade;
import it.unipv.ingsfw.facade.tickets.IMezzoFacade;
import it.unipv.ingsfw.facade.tickets.ITicketsFacade;
import it.unipv.ingsfw.facade.users.clienti.IClientiFacade;
import it.unipv.ingsfw.facade.users.dipendenti.IDipendentiFacade;

public class F2aFacade {

	private static final F2aFacade instance = new F2aFacade();
    public static F2aFacade getInstance() {
        return instance;
    }

    private ICatenaLavorazioneFacade catenaLavorazioneFacade;
    private IStazioneLavoroFacade stazioneLavoroFacade;
    private INegozioFacade negozioFacade;
    private IItinerarioFacade itinerarioFacade;
    private IMezzoFacade mezzoFacade;
    private ITicketsFacade ticketsFacade;
    private IClientiFacade clientiFacade;
    private IDipendentiFacade dipendentiFacade;
    

    private F2aFacade() {

        try{
            this.catenaLavorazioneFacade = (ICatenaLavorazioneFacade) loadClass("catenalavorazione").getDeclaredConstructor().newInstance();
            this.stazioneLavoroFacade = (IStazioneLavoroFacade) loadClass("stazionelavoro").getDeclaredConstructor().newInstance();
            this.negozioFacade = (INegozioFacade) loadClass("negozio").getDeclaredConstructor().newInstance();
            this.itinerarioFacade = (IItinerarioFacade) loadClass("itinerario").getDeclaredConstructor().newInstance();
            this.mezzoFacade = (IMezzoFacade) loadClass("mezzo").getDeclaredConstructor().newInstance();
            this.ticketsFacade = (ITicketsFacade) loadClass("ticket").getDeclaredConstructor().newInstance();
            this.clientiFacade = (IClientiFacade) loadClass("cliente").getDeclaredConstructor().newInstance();
            this.dipendentiFacade = (IDipendentiFacade) loadClass("dipendente").getDeclaredConstructor().newInstance();
        }catch (Exception e){
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
    }

    private Class<?> loadClass(String propertyName) throws Exception {
        Properties properties  = new Properties();
        properties.load(F2aFacade.class.getResourceAsStream("properties/properties"));
        return Class.forName(properties.getProperty("facade."+propertyName));
    }

	

	public ICatenaLavorazioneFacade getCatenaLavorazioneFacade() {
		return catenaLavorazioneFacade;
	}

	public IStazioneLavoroFacade getStazioneLavoroFacade() {
		return stazioneLavoroFacade;
	}

	public INegozioFacade getNegozioFacade() {
		return negozioFacade;
	}

	public IItinerarioFacade getItinerarioFacade() {
		return itinerarioFacade;
	}

	public IMezzoFacade getMezzoFacade() {
		return mezzoFacade;
	}

	public ITicketsFacade getTicketsFacade() {
		return ticketsFacade;
	}

	public IClientiFacade getClientiFacade() {
		return clientiFacade;
	}

	public IDipendentiFacade getDipendentiFacade() {
		return dipendentiFacade;
	}

    
}
