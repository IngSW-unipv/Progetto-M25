package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;
import java.util.Observable;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.CapoDAO;
import it.unipv.ingsfw.model.StatoCapo;

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
		this.idStazione = idStazione;
		this.tipo = tipo;
		this.statoStazione = statoStazione;
		this.livelloProdottoLavaggio = livelloProdottoLavaggio;
		this.listaCapiDaLavorare = new ArrayList<Capo>();
	}

	public ObservableStazioneLavoro(TipologiaStazione tipo) {
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

	// metodo per modifica stato macchinario in maniera più automatizzata e generale

	public void messaInLavorazione() {
		statoStazione = StatoStazione.WORKING;
	}

	public void messaInStandBy() {
		statoStazione = StatoStazione.READY;
	}

	public void messaInManutenzione() {
		statoStazione = StatoStazione.MAINTENANCE;
	}

	public boolean checkPresenzaCapi() {
		LavorazioneDAO lav = LavorazioneDAO.getInstance();

		ArrayList<Capo> listaCapi = lav.checkPresenzaCapiInStazione(this);

		if (listaCapi.size() != 0) {
			this.listaCapiDaLavorare = listaCapi;
			return true;
		}
		return false;
	}

	public boolean caricamentoLavorazioni() {
		LavorazioneDAO lav = LavorazioneDAO.getInstance();

		boolean esitoCaricamento = false;
		for (Capo c : listaCapiDaLavorare)
			esitoCaricamento = lav.addLavorazione(this, c);

		return esitoCaricamento;
	}

	public boolean caricamentoLavorazioniSospese() {
		LavorazioneDAO lav = LavorazioneDAO.getInstance();

		boolean esitoCaricamento = false;
		for (Capo c : listaCapiDaLavorare)
			esitoCaricamento = lav.addLavorazioneSospesa(this, c);

		return esitoCaricamento;
	}

	public void svuotaStazione() {
		// ArrayList<Capo> capiDaSpostare = this.listaCapiDaLavorare;
		this.listaCapiDaLavorare.clear();
	}

	public String getIdCatena() {
		ObservableStazioneLavoroDAO obs = new ObservableStazioneLavoroDAO();
		String idCatena = obs.selectIdCatenaByStazione(this);
		return idCatena;
	}

	public void removeCapiStazione() {

		CatenaLavorazioneDAO cat = new CatenaLavorazioneDAO();

		ArrayList<ObservableStazioneLavoro> stazioni = cat
				.selectStazioniByCatena(new CatenaLavorazione(this.getIdCatena()));

		if (this.getTipo().toString().equals("STIRATURA") || cat.selectCatenaByStazione(this).getTipoLavaggio().toString().equals("PELLE")) {

			CapoDAO cap = new CapoDAO();

			for (Capo c : this.getListaCapiDaLavorare()) {
				c.setStatoCapo(StatoCapo.IN_CONSEGNA);
				cap.updateStatoCapo(c);
			}

		} else {
			int numStazioneSuccessiva = stazioni.indexOf(this) + 1;
			stazioni.get(numStazioneSuccessiva).caricamentoLavorazioniSospese();
		}

		this.svuotaStazione();

	}

}
