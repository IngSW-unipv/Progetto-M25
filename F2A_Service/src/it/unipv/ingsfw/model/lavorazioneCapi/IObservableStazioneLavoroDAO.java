package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;

public interface IObservableStazioneLavoroDAO {

	public ArrayList<ObservableStazioneLavoro> selectAll();
	public ArrayList<ObservableStazioneLavoro> selectByStato(ObservableStazioneLavoro input);
	public boolean insertStazione(ObservableStazioneLavoro s);
}
