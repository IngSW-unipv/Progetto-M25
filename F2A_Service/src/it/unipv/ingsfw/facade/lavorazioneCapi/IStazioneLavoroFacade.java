package it.unipv.ingsfw.facade.lavorazioneCapi;

import java.util.ArrayList;

import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.users.Operatore;

public interface IStazioneLavoroFacade {
	
	public ArrayList<ObservableStazioneLavoro> selectAll();
	public ArrayList<ObservableStazioneLavoro> selectByStato(ObservableStazioneLavoro input);
	public boolean insertStazioneWithUnknownCatena(ObservableStazioneLavoro s);
	public boolean insertStazioneWithKnownCatena(ObservableStazioneLavoro s, CatenaLavorazione c);
	public String selectIdCatenaByStazione(ObservableStazioneLavoro s);
	public boolean assegnazioneResponsabileStazioneLibero(ObservableStazioneLavoro s);
	public boolean assegnazioneManutentoreLibero(ObservableStazioneLavoro s);
	public int getIdLastAssegnazione();
	public String getNewIdStazione();
	public ArrayList<ObservableStazioneLavoro> selectStazioniReadyNonAssegnate();
	public ArrayList<ObservableStazioneLavoro> selectStazioniMaintenanceNonAssegnate();
	public ObservableStazioneLavoro selectStazioniReadyNonAssegnatePerTipo(ObservableStazioneLavoro s);
	public boolean assegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o);
	public boolean changeStatoStazione(ObservableStazioneLavoro s);
	public ArrayList<ObservableStazioneLavoro> selectStazioniByOperatore(Operatore o);

}
