package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;

public interface ICatenaLavorazioneDAO {
	
	public ArrayList<CatenaLavorazione> selectAll();
	public ArrayList<CatenaLavorazione> selectByTipoLavaggio(CatenaLavorazione input);
	public boolean insertCatena(CatenaLavorazione c);
	public boolean checkCatenaAlreadyExists(CatenaLavorazione c);
	public ArrayList<ObservableStazioneLavoro> selectStazioniByCatena(CatenaLavorazione input);
	public CatenaLavorazione selectCatenaByStazione(ObservableStazioneLavoro input);
	public String getNewIdCatena();
}
