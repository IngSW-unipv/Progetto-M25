package it.unipv.ingsfw.model.lavorazioneCapi;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.CapoDAO;
import it.unipv.ingsfw.model.StatoCapo;

public class ObservableStazioneLavoro extends Observable {

	private String idStazione;
	private TipologiaStazione tipo;
	private StatoStazione statoStazione;
	private double livelloProdottoLavaggio;
	private ArrayList<Capo> listaCapiDaLavorare;
	private ArrayList<Observer> observers = new ArrayList<>(); //arraylist necessario a contenere i diversi observer

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
		//setChanged();
		//notifyObservers(this);
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

	//vecchia versione prima dell'implementazione dei dao
	
	/*public boolean setListaCapiDaLavorare(Capo c) {
		CapoDAO capi = new CapoDAO();
		LavorazioneDAO lav = LavorazioneDAO.getInstance();

		if (statoStazione.toString().equals("READY")) {
			// modificare per far prelevare solo i capi SOSPESI nella tabella lavorazione,
			// per avere quelli che hanno subito la lavorazione precedente se la stazione
			// non è di tipo lavaggio
			if (tipo.toString().equalsIgnoreCase("LAVAGGIO")) {
				listaCapiDaLavorare = capi.selectCapoByStatoETipo(c);
			} else {
				listaCapiDaLavorare = lav.checkPresenzaCapiInStazione(this);
			}

		}

		if (listaCapiDaLavorare.size() == 0) {
			return false;
		}
		return true;
	}*/
	
	
	public boolean setListaCapiDaLavorare(Capo c) {
		//CapoDAO capi = new CapoDAO();
		//LavorazioneDAO lav = LavorazioneDAO.getInstance();

		if (statoStazione.toString().equals("READY")) {
			// modificare per far prelevare solo i capi SOSPESI nella tabella lavorazione,
			// per avere quelli che hanno subito la lavorazione precedente se la stazione
			// non è di tipo lavaggio
			if (tipo.toString().equalsIgnoreCase("LAVAGGIO")) {
				listaCapiDaLavorare = F2aFacade.getInstance().getCapoFacade().selectCapoByStatoETipo(c);
			} else {
				listaCapiDaLavorare = F2aFacade.getInstance().getLavorazioneFacade().checkPresenzaCapiInStazione(this);
			}

		}

		if (listaCapiDaLavorare.size() == 0) {
			return false;
		}
		return true;
	}

	public void setListaCapiDaLavorare(ArrayList<Capo> listaCapi) {
		listaCapiDaLavorare = listaCapi;
	}

	@Override
	public String toString() {
		return "StazioneLavoro " + idStazione + "\nTipo: " + tipo.toString() + "\nStato: " + statoStazione.toString()
				+ "\nLivelloProdottoLavaggio: " + livelloProdottoLavaggio;
	}

	// metodo per modifica stato macchinario in maniera più automatizzata e generale

	public void messaInLavorazione(int index) {
		statoStazione = StatoStazione.WORKING;
		//setChanged();
		System.out.println("Stato cambiato 1 , notifico gli osservatori...");
	    //notifyObservers(index);
		for(Observer o : observers) {
			System.out.println(o);
		}
		notificaObservers();
	}

	public void messaInStandBy(int index) {
		statoStazione = StatoStazione.READY;
		//setChanged();
		System.out.println("Stato cambiato 2 , notifico gli osservatori...");
	    //notifyObservers(index);
		notificaObservers();
	}

	public void messaInManutenzione(int index) {
		statoStazione = StatoStazione.MAINTENANCE;
		//setChanged();
		System.out.println("Stato cambiato 3 , notifico gli osservatori...");
	    //notifyObservers(index);
		notificaObservers();
	}

	public boolean checkPresenzaCapi() {
		boolean esitoCaricamento = false;
		//LavorazioneDAO lav = LavorazioneDAO.getInstance();

		// prelevo capi dalla tabella lavorazione a db nel caso in cui ci siano già dei
		// capi pronti per la lavorazione
		ArrayList<Capo> listaCapi = F2aFacade.getInstance().getLavorazioneFacade().checkPresenzaCapiInStazione(this);

		if (listaCapi.size() != 0) {
			this.listaCapiDaLavorare = listaCapi;
			esitoCaricamento = true;
		} else {
			// nel caso in cui non ci fossero dei capi già assegnati a db allora andiamo a
			// prelevare, sempre da db, i capi in attesa di lavorazione e con la medesima
			// tipologia di lavaggio della stazione

			//CatenaLavorazioneDAO cat = new CatenaLavorazioneDAO();
			esitoCaricamento = setListaCapiDaLavorare(
					new Capo(StatoCapo.IN_LAVORAZIONE, F2aFacade.getInstance().getCatenaLavorazioneFacade().selectCatenaByStazione(this).getTipoLavaggio()));
		}

		for (Capo c : listaCapi) {
			System.out.println(c);
		}
		return esitoCaricamento;
	}

	public boolean caricamentoLavorazioni() {
		//LavorazioneDAO lav = LavorazioneDAO.getInstance();

		boolean esitoCaricamento = false;
		for (Capo c : listaCapiDaLavorare) {
			esitoCaricamento = F2aFacade.getInstance().getLavorazioneFacade().addLavorazione(this, c);
			System.out.println(c);
		}
		return esitoCaricamento;
	}

	public boolean aggiornamentoLavorazioni() {
		//LavorazioneDAO lav = LavorazioneDAO.getInstance();

		boolean esitoCaricamento = false;
		for (Capo c : listaCapiDaLavorare) {
			esitoCaricamento = F2aFacade.getInstance().getLavorazioneFacade().updateLavorazione(this, c);
			// System.out.println(c);
		}
		System.out.println("AGGIORNAMENTO TABELLA LAVORAZIONI CON DATA");
		return esitoCaricamento;
	}

	public boolean caricamentoLavorazioniSospese() {
		//LavorazioneDAO lav = LavorazioneDAO.getInstance();

		boolean esitoCaricamento = false;

		// System.out.println("this: " + this.getIdStazione());

		for (Capo c : listaCapiDaLavorare)
			esitoCaricamento = F2aFacade.getInstance().getLavorazioneFacade().addLavorazioneSospesa(this, c);

		return esitoCaricamento;
	}

	public void svuotaStazione() {
		// ArrayList<Capo> capiDaSpostare = this.listaCapiDaLavorare;
		this.listaCapiDaLavorare.clear();
	}

	public String getIdCatena() {
		//ObservableStazioneLavoroDAO obs = new ObservableStazioneLavoroDAO();
		String idCatena = F2aFacade.getInstance().getStazioneLavoroFacade().selectIdCatenaByStazione(this);
		return idCatena;
	}

	public boolean removeCapiStazione() {

		//CatenaLavorazioneDAO cat = new CatenaLavorazioneDAO();

		ArrayList<ObservableStazioneLavoro> stazioni = F2aFacade.getInstance().getCatenaLavorazioneFacade().selectStazioniByCatena(new CatenaLavorazione(this.getIdCatena()));

		for (ObservableStazioneLavoro s : stazioni)
			System.out.println(s.getIdStazione());

		if (this.getTipo().toString().equals("STIRATURA") || F2aFacade.getInstance().getCatenaLavorazioneFacade().selectCatenaByStazione(this).getTipoLavaggio().toString().equals("PELLE")) {

			//CapoDAO cap = new CapoDAO();

			for (Capo c : this.getListaCapiDaLavorare()) {
				c.setStatoCapo(StatoCapo.IN_CONSEGNA);
				F2aFacade.getInstance().getCapoFacade().updateStatoCapo(c);
			}

		} else {

			// String sub = this.getIdStazione().substring(3);
			int numPrimaStazione = Integer.parseInt(stazioni.get(0).getIdStazione().substring(3));

			String newIdStazione = this.getIdStazione();
			String sub = newIdStazione.substring(3);
			// System.out.println(sub);
			int num = Integer.parseInt(sub);

			int numStazioneSuccessiva = num - numPrimaStazione + 1;
			System.out.println("NUM STAZIONE SUCCESSIVA: " + numStazioneSuccessiva);
			// System.out.println(stazioni.get(numStazioneSuccessiva).getIdStazione());
			stazioni.get(numStazioneSuccessiva).setListaCapiDaLavorare(this.listaCapiDaLavorare);
			// System.out.println("bbbbbbbbbbbbbb");
			stazioni.get(numStazioneSuccessiva).caricamentoLavorazioniSospese();
		}

		this.svuotaStazione();
		return true;

	}
	
	public void assegnazionePostGuasto(int index) {
		this.messaInManutenzione(index);
		F2aFacade.getInstance().getStazioneLavoroFacade().changeStatoStazione(this);
		F2aFacade.getInstance().getStazioneLavoroFacade().assegnazioneManutentoreLibero(this);
	}
	
	//metodi per gestire l'inserimento degli observer nelle diverse stazioni
	
	public void aggiungiObserver(Observer observer) {
        observers.add(observer);
    }

    public void notificaObservers() {
        for (Observer observer : observers) {
            observer.update(this, statoStazione);
        }
    }

}
