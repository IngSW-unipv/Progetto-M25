package it.unipv.ingsfw.model.lavorazioneCapi;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.ingsfw.model.TipoLavaggio;

public class CatenaLavorazione {
	
	private String idCatena;
	private TipoLavaggio tipoLavaggio;
	private ArrayList<ObservableStazioneLavoro> listaStazioni;
	
	/**
	 * @param idCatena
	 * @param tipoLavaggio
	 * @param listaStazioni
	 */
	public CatenaLavorazione(String idCatena, TipoLavaggio tipoLavaggio) {
		super();
		this.idCatena = idCatena;
		this.tipoLavaggio = tipoLavaggio;
		this.listaStazioni = new ArrayList<ObservableStazioneLavoro>();
	}

	public String getIdCatena() {
		return idCatena;
	}

	public void setIdCatena(String idCatena) {
		this.idCatena = idCatena;
	}

	public TipoLavaggio getTipoLavaggio() {
		return tipoLavaggio;
	}

	public void setTipoLavaggio(TipoLavaggio tipoLavaggio) {
		this.tipoLavaggio = tipoLavaggio;
	}

	public ArrayList<ObservableStazioneLavoro> getListaStazioni() {
		return listaStazioni;
	}

	/*public void setListaStazioni(ArrayList<StazioneLavoro> listaStazioni) {
		this.listaStazioni = listaStazioni;
	}*/
	
	public void checkStatoStazione(ObservableStazioneLavoro stazione) {
		stazione.getStatoStazione().toString();
	}
	
	/*public boolean svuotaStazione(StazioneLavoro stazione) {
		for(StazioneLavoro s : listaStazioni) {
			if(s.equals(stazione))
				
		}
	}*/
	
	public boolean tipoStazioneAlreadyExists(ObservableStazioneLavoro s) {
		for(ObservableStazioneLavoro o : listaStazioni) {
			if(s.getTipo().equals(o.getTipo()))
				return true;
		}
		return false;
	}
	
	public boolean addStazioneLavoro(ObservableStazioneLavoro newStazione) {
		ObservableStazioneLavoroDAO stazione = new ObservableStazioneLavoroDAO();
		
		if(listaStazioni.size() <= 2 && !tipoLavaggio.equals(TipoLavaggio.PELLE) && !tipoStazioneAlreadyExists(newStazione)) {
			if(stazione.insertStazioneWithKnownCatena(newStazione, this)) {
				listaStazioni.add(newStazione);
				return true;
			}
				
		}
		return false;
			
	}
	
	public boolean riempimentoListaStazioni() throws SQLException {
		CatenaLavorazioneDAO cl = new CatenaLavorazioneDAO();
		listaStazioni = cl.selectStazioniByCatena(this);
		return true;
	}
	
	@Override
	public String toString() {
		String catena = "Catena " + idCatena + "\nTipoLavaggio: " + tipoLavaggio.toString();
		for(ObservableStazioneLavoro s : listaStazioni)
			s.toString();
		return catena;
	}
}
