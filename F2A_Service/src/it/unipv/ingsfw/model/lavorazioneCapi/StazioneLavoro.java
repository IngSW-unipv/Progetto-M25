package it.unipv.ingsfw.model.lavorazioneCapi;

public class StazioneLavoro {
	
	private String idStazione;
	private TipologiaStazione tipo;
	private StatoStazione statoStazione;
	private double livelloProdottoLavaggio;
	
	/**
	 * @param idStazione
	 * @param tipo
	 * @param statoStazione
	 * @param livelloProdottoLavaggio
	 */
	public StazioneLavoro(String idStazione, TipologiaStazione tipo, StatoStazione statoStazione,
			double livelloProdottoLavaggio) {
		super();
		this.idStazione = idStazione;
		this.tipo = tipo;
		this.statoStazione = statoStazione;
		this.livelloProdottoLavaggio = livelloProdottoLavaggio;
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
	
	/*
	public static void main(String[] args) {
		StazioneLavoro s = new StazioneLavoro("S001", TipologiaStazione.ASCIUGATURA, StatoStazione.READY, 100);
		System.out.println(s.getTipo());
	}*/
	
	
}
