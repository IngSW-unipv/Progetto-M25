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
	public String getNewIdStazione();
	public ArrayList<ObservableStazioneLavoro> selectStazioniReadyNonAssegnate();
	public ArrayList<ObservableStazioneLavoro> selectStazioniMaintenanceNonAssegnate();
	public ObservableStazioneLavoro selectStazioniReadyNonAssegnatePerTipo(ObservableStazioneLavoro s);
	public boolean assegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o);
	public boolean changeStatoStazione(ObservableStazioneLavoro s);
	public ArrayList<ObservableStazioneLavoro> selectStazioniByOperatore(Operatore o);
	public boolean chiusuraAssegnazioneOperatoreNoto(ObservableStazioneLavoro s, Operatore o);
}
