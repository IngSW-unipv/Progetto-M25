package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;
import java.util.Observable;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.CapoDAO;

public class ObservableStazioneLavoro extends Observable {

	private String idStazione;
	private TipologiaStazione tipo;
	private StatoStazione statoStazione;
	private double livelloProdottoLavaggio;
	private ArrayList<Capo> listaCapiDaLavorare;

	/**
	 * @param idStazione
	 * @param tipo
	 * @param statoStazione
	 * @param livelloProdottoLavaggio
	 */
	public ObservableStazioneLavoro(String idStazione, TipologiaStazione tipo, StatoStazione statoStazione,
			double livelloProdottoLavaggio) {
		super();
		this.idStazione = idStazione;
		this.tipo = tipo;
		this.statoStazione = statoStazione;
		this.livelloProdottoLavaggio = livelloProdottoLavaggio;
		this.listaCapiDaLavorare = new ArrayList<Capo>();
	}

	public ObservableStazioneLavoro(TipologiaStazione tipo) {
		super();
		this.idStazione = null;
		this.tipo = tipo;
		this.statoStazione = null;
		this.livelloProdottoLavaggio = 0.0;
		this.listaCapiDaLavorare = null;
	}

	public String getIdStazione() {
		return idStazione;
	}

	public void setIdStazione(String idStazione) {
		this.idStazione = idStazione;
	}

	public TipologiaStazione getTipo() {
		return tipo;
	}

	public void setTipo(TipologiaStazione tipo) {
		this.tipo = tipo;
	}

	public StatoStazione getStatoStazione() {
		return statoStazione;
	}

	public void setStatoStazione(StatoStazione statoStazione) {
		this.statoStazione = statoStazione;
	}

	public double getLivelloProdottoLavaggio() {
		return livelloProdottoLavaggio;
	}

	public void setLivelloProdottoLavaggio(double livelloProdottoLavaggio) {
		this.livelloProdottoLavaggio = livelloProdottoLavaggio;
	}

	public ArrayList<Capo> getListaCapiDaLavorare() {
		return listaCapiDaLavorare;
	}

	public boolean setListaCapiDaLavorare(Capo c) {
		CapoDAO capi = new CapoDAO();

		if (statoStazione.toString().equals("READY")) {
			listaCapiDaLavorare = capi.selectCapoByStatoETipo(c);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "StazioneLavoro " + idStazione + "\nTipo: " + tipo.toString() + "\nStato: " + statoStazione.toString()
				+ "\nLivelloProdottoLavaggio: " + livelloProdottoLavaggio;
	}

	public boolean checkPresenzaCapi() {
		if (this.listaCapiDaLavorare.size() == 0) {
			return false;
		}
		return true;
	}

	public boolean caricamentoLavorazioni() {
		LavorazioneDAO lav = new LavorazioneDAO();

		boolean esitoCaricamento = false;
		for (Capo c : listaCapiDaLavorare)
			esitoCaricamento = lav.addLavorazione(this, c);
		
		return esitoCaricamento;
	}
	
	public boolean caricamentoLavorazioniSospese() {
		LavorazioneDAO lav = new LavorazioneDAO();

		boolean esitoCaricamento = false;
		for (Capo c : listaCapiDaLavorare)
			esitoCaricamento = lav.addLavorazioneSospesa(this, c);
		
		return esitoCaricamento;
	}

	public void svuotaStazione() {
		//ArrayList<Capo> capiDaSpostare = this.listaCapiDaLavorare;
		this.listaCapiDaLavorare.clear();
	}
	
	public String getIdCatena() {
		ObservableStazioneLavoroDAO obs = new ObservableStazioneLavoroDAO();
		String idCatena = obs.selectIdCatenaByStazione(this);
		return idCatena;
	}

}
