package it.unipv.ingsfw.model;

import java.util.HashMap;

public interface ILavaggioDAO {
	
	public HashMap<Integer,String> selectAll();
	
	public int getCostoLavaggio(String descrizione);
	
	public int getCostoLavaggio(int idLavaggio);
}
