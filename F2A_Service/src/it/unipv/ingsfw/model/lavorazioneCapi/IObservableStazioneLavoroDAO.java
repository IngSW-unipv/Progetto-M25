package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.model.users.Operatore;

public interface IObservableStazioneLavoroDAO {

	public ArrayList<ObservableStazioneLavoro> selectAll();
	public ArrayList<ObservableStazioneLavoro> selectByStato(ObservableStazioneLavoro input);
	public boolean insertStazioneWithUnknownCatena(ObservableStazioneLavoro s);
	public boolean insertStazioneWithKnownCatena(ObservableStazioneLavoro s, CatenaLavorazione c);
	public String selectIdCatenaByStazione(ObservableStazioneLavoro s);
	public boolean assegnazioneResponsabileStazioneLibero(ObservableStazioneLavoro s);
	public boolean assegnazioneManutentoreLibero(ObservableStazioneLavoro s);
	public int getIdLastAssegnazione();
	public ArrayList<ObservableStazioneLavoro> selectStazioniReadyNonAssegnate();
	public ArrayList<ObservableStazioneLavoro> selectStazioniMaintenanceNonAssegnate();
	public String getNewIdStazione();
}
