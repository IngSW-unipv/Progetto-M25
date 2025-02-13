package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;

public interface IStazioneLavoroDAO {

	public ArrayList<StazioneLavoro> selectAll();
	public ArrayList<StazioneLavoro> selectByStato(StazioneLavoro fornInput);
	public boolean insertStazione(StazioneLavoro s);
}
