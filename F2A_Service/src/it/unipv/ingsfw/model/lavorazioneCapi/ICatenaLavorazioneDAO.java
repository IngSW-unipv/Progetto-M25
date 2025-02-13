package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;

public interface ICatenaLavorazioneDAO {
	
	public ArrayList<CatenaLavorazione> selectAll();
	public ArrayList<CatenaLavorazione> selectByTipoLavaggio(CatenaLavorazione fornInput);
	public boolean insertCatena(CatenaLavorazione c);
	//public ArrayList<ObservableStazioneLavoro> selectStazioniFromCatena(CatenaLavorazione fornInput);
}
