package it.unipv.ingsfw.facade.negozio;

import java.util.ArrayList;
import it.unipv.ingsfw.model.negozio.Negozio;

public interface INegozioFacade {
	
	public ArrayList<Negozio> selectAll();
	public boolean insertNegozio(Negozio n);

}
