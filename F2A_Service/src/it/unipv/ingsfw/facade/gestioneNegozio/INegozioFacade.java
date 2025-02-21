package it.unipv.ingsfw.facade.gestioneNegozio;

import java.util.ArrayList;
import it.unipv.ingsfw.model.negozio.Negozio;

public interface INegozioFacade {
	
	public ArrayList<Negozio> selectAllNegozio();
	public boolean insertNegozio(Negozio n);

}
