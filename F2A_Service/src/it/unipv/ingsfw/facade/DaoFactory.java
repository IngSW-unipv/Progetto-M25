package it.unipv.ingsfw.facade;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import it.unipv.ingsfw.model.ICapoDAO;
import it.unipv.ingsfw.model.ILavaggioDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ICatenaLavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ILavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.IObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.negozio.INegozioDAO;
import it.unipv.ingsfw.model.tickets.IItinerarioDAO;
import it.unipv.ingsfw.model.tickets.IMezzoDAO;
import it.unipv.ingsfw.model.tickets.ITicketDAO;
import it.unipv.ingsfw.model.users.IClienteDAO;
import it.unipv.ingsfw.model.users.IDipendenteDAO;

public class DaoFactory {
	
	private static final Map<Class<?>, Object> loadedDao = new HashMap<>();
    private static final Properties properties = new Properties();
	

    static {
        try (InputStream input = DaoFactory.class.getResourceAsStream("/main/resources/properties_dao")) {
            if (input == null) {
                throw new RuntimeException("File properties non trovato");
            }
            properties.load(input);
            //System.out.println("Contenuto di properties: " + properties);
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento del file di configurazione", e);
        }
    }

    public static <T> T get(Class<T> clazz) {
        if (loadedDao.containsKey(clazz)) {
            return clazz.cast(loadedDao.get(clazz));
        }

        String className = properties.getProperty("dao." + clazz.getSimpleName().toLowerCase());
        if (className == null) {
            throw new RuntimeException("Classe non trovata nel file di configurazione per " + clazz.getSimpleName());
        }

        try {
            Class<?> retClass = Class.forName(className);
            Object instance = retClass.getDeclaredConstructor().newInstance();
            //System.out.println(instance);
            loadedDao.put(clazz, instance);
            return clazz.cast(instance);
        } catch (Exception e) {
            throw new RuntimeException("Errore nell'istanza della classe " + className, e);
        }
    }
    
	public static IDipendenteDAO getDipendenteDAO() {
		return get(IDipendenteDAO.class);
	}

	public static ILavorazioneDAO getLavorazioneDAO() {
		return get(ILavorazioneDAO.class);
	}

	public static IClienteDAO getClienteDAO() {
		return get(IClienteDAO.class);
	}

	public static INegozioDAO getNegozioDAO() {
		return get(INegozioDAO.class);
	}

	public static ICatenaLavorazioneDAO getCatenaLavorazioneDAO() {
		return get(ICatenaLavorazioneDAO.class);
	}

	public static IObservableStazioneLavoroDAO getObservableStazioneLavoroDAO() {
		return (ObservableStazioneLavoroDAO) get(IObservableStazioneLavoroDAO.class);
	}

	public static ICapoDAO getCapoDAO() {
		return get(ICapoDAO.class);
	}

	public static ILavaggioDAO getLavaggioDAO() {
		return get(ILavaggioDAO.class);
	}
	
	public static IMezzoDAO getMezzoDAO() {
		return get(IMezzoDAO.class);
	}
	
	public static IItinerarioDAO getItinerarioDAO() {
		return get(IItinerarioDAO.class);
	}
	
	public static ITicketDAO getTicketDAO() {
		return get(ITicketDAO.class);
	}

	}
