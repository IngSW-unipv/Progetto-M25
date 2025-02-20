package it.unipv.ingsfw.facade.lavaggio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public interface ILavaggioFacade {
	
	public HashMap<Integer,String> selectAll();
	public int getCostoLavaggio(String descrizione);
	public int getCostoLavaggio(int idLavaggio);

}
