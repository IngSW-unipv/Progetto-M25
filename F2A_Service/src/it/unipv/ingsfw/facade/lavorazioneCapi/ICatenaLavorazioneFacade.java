package it.unipv.ingsfw.facade.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.users.Operatore;

public interface ICatenaLavorazioneFacade {
	
	public ArrayList<CatenaLavorazione> selectAll();
	public ArrayList<CatenaLavorazione> selectByTipoLavaggio(CatenaLavorazione input);
	public boolean insertCatena(CatenaLavorazione c);
	public boolean checkCatenaAlreadyExists(CatenaLavorazione c);
	public ArrayList<ObservableStazioneLavoro> selectStazioniByCatena(CatenaLavorazione input);
	public CatenaLavorazione selectCatenaByStazione(ObservableStazioneLavoro input);
	public String getNewIdCatena();
	

}
