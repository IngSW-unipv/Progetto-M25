package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.model.Capo;

public interface ILavorazioneDAO {

	public boolean addLavorazione(ObservableStazioneLavoro s, Capo c);

	public boolean updateLavorazione(ObservableStazioneLavoro s, Capo c);

	// aggiunta metodo per inserire a db una lavorazione ma senza specificare la
	// data non essendo ancora stata svolta la lavorazione stessa
	public boolean addLavorazioneSospesa(ObservableStazioneLavoro s, Capo c);
	public ArrayList<Capo> checkPresenzaCapiInStazione(ObservableStazioneLavoro s);
}
