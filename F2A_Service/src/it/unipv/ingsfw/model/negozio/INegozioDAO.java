package it.unipv.ingsfw.model.negozio;

import java.util.ArrayList;

public interface INegozioDAO {
	
	public ArrayList<Negozio> selectAll();
	public boolean insertNegozio(Negozio n);
}
