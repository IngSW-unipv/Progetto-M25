package it.unipv.ingsfw.facade.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;

public interface ILavorazioneFacade {
	
	public boolean addLavorazione(ObservableStazioneLavoro s, Capo c);
	public boolean updateLavorazione(ObservableStazioneLavoro s, Capo c);
	public boolean addLavorazioneSospesa(ObservableStazioneLavoro s, Capo c);
	public ArrayList<Capo> checkPresenzaCapiInStazione(ObservableStazioneLavoro s);

}
