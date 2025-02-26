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
	private ArrayList<Observer> observers; //arraylist necessario a contenere i diversi observer

	/**
	 * @param idStazione
	 * @param tipo
	 * @param statoStazione
	 * @param livelloProdottoLavaggio
	 */
	public ObservableStazioneLavoro(String idStazione, TipologiaStazione tipo, StatoStazione statoStazione,
			double livelloProdottoLavaggio) {
		if(idStazione.length() == 4) {
			this.idStazione = idStazione;
		}else {
			throw new IllegalArgumentException("Errore nell'inserimento dell'id stazione");
		}
		this.tipo = tipo;
		this.statoStazione = statoStazione;
		this.livelloProdottoLavaggio = livelloProdottoLavaggio;
		this.listaCapiDaLavorare = new ArrayList<Capo>();
		this.observers = new ArrayList<Observer>();
	}

	public ObservableStazioneLavoro(TipologiaStazione tipo) {
		this.idStazione = null;
		this.tipo = tipo;
		this.statoStazione = null;
		this.livelloProdottoLavaggio = 0.0;
		this.listaCapiDaLavorare = null;
		this.observers = new ArrayList<Observer>();
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

		if (statoStazione.toString().equals("READY")) {
			// modificare per far prelevare solo i capi SOSPESI nella tabella lavorazione,
			// per avere quelli che hanno subito la lavorazione precedente se la stazione
			// non è di tipo lavaggio
			if (tipo.toString().equalsIgnoreCase("LAVAGGIO")) {
				listaCapiDaLavorare = F2aFacade.getInstance().getCapoFacade().selectCapoByStatoETipo(c);
			} else {
				listaCapiDaLavorare = F2aFacade.getInstance().getLavorazioneCapiFacade().checkPresenzaCapiInStazione(this);
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
		System.out.println("Stato cambiato 1 , notifico gli osservatori...");
		notificaObservers(index);
	}

	public void messaInStandBy(int index) {
		statoStazione = StatoStazione.READY;
		System.out.println("Stato cambiato 2 , notifico gli osservatori...");
		notificaObservers(index);
	}

	public void messaInManutenzione(int index) {
		statoStazione = StatoStazione.MAINTENANCE;
		System.out.println("Stato cambiato 3 , notifico gli osservatori...");
		notificaObservers(index);
	}

	public boolean checkPresenzaCapi() {
		boolean esitoCaricamento = false;

		// prelevo capi dalla tabella lavorazione a db nel caso in cui ci siano già dei
		// capi pronti per la lavorazione
		ArrayList<Capo> listaCapi = F2aFacade.getInstance().getLavorazioneCapiFacade().checkPresenzaCapiInStazione(this);

		if (listaCapi.size() != 0) {
			this.listaCapiDaLavorare = listaCapi;
			esitoCaricamento = true;
		} else {
			// nel caso in cui non ci fossero dei capi già assegnati a db allora andiamo a
			// prelevare, sempre da db, i capi in attesa di lavorazione e con la medesima
			// tipologia di lavaggio della stazione
			
			esitoCaricamento = setListaCapiDaLavorare(new Capo(StatoCapo.IN_LAVORAZIONE, F2aFacade.getInstance().getLavorazioneCapiFacade().selectCatenaByStazione(this).getTipoLavaggio()));
		}

		for (Capo c : listaCapi) {
			//System.out.println(c);
		}
		return esitoCaricamento;
	}

	public boolean caricamentoLavorazioni() {

		boolean esitoCaricamento = false;
		for (Capo c : listaCapiDaLavorare) {
			esitoCaricamento = F2aFacade.getInstance().getLavorazioneCapiFacade().addLavorazione(this, c);
			System.out.println(c);
		}
		return esitoCaricamento;
	}

	public boolean aggiornamentoLavorazioni() {
		
		boolean esitoCaricamento = false;
		for (Capo c : listaCapiDaLavorare) {
			esitoCaricamento = F2aFacade.getInstance().getLavorazioneCapiFacade().updateLavorazione(this, c);
			System.out.println(c);
		}
		System.out.println("AGGIORNAMENTO TABELLA LAVORAZIONI CON DATA");
		return esitoCaricamento;
	}

	public boolean caricamentoLavorazioniSospese() {

		boolean esitoCaricamento = false;

		for (Capo c : listaCapiDaLavorare)
			esitoCaricamento = F2aFacade.getInstance().getLavorazioneCapiFacade().addLavorazioneSospesa(this, c);

		return esitoCaricamento;
	}

	public void svuotaStazione() {

		this.listaCapiDaLavorare.clear();
	}

	public String getIdCatena() {
		
		String idCatena = F2aFacade.getInstance().getLavorazioneCapiFacade().selectIdCatenaByStazione(this);
		return idCatena;
	}

	public boolean removeCapiStazione() {


		ArrayList<ObservableStazioneLavoro> stazioni = F2aFacade.getInstance().getLavorazioneCapiFacade().selectStazioniByCatena(new CatenaLavorazione(this.getIdCatena()));

		for (ObservableStazioneLavoro s : stazioni) {
			System.out.println(s.getIdStazione());
		}

		if (this.getTipo().toString().equalsIgnoreCase("ASCIUGATURA") && F2aFacade.getInstance().getLavorazioneCapiFacade().selectCatenaByStazione(this).getTipoLavaggio().toString().equalsIgnoreCase("6")) {

			for (Capo c : this.getListaCapiDaLavorare()) {
				c.setStatoCapo(StatoCapo.IN_CONSEGNA);
				F2aFacade.getInstance().getLavorazioneCapiFacade().updateLavorazione(this, c);
				F2aFacade.getInstance().getCapoFacade().updateStatoCapo(c);
			}

		} else {

			//System.out.println(this.getTipo());
			//System.out.println(F2aFacade.getInstance().getLavorazioneCapiFacade().selectCatenaByStazione(this).getTipoLavaggio());
			// String sub = this.getIdStazione().substring(3);
			int numPrimaStazione = Integer.parseInt(stazioni.get(0).getIdStazione().substring(3));

			String newIdStazione = this.getIdStazione();
			String sub = newIdStazione.substring(3);
			int num = Integer.parseInt(sub);

			int numStazioneSuccessiva = num - numPrimaStazione + 1;
			System.out.println("NUM STAZIONE SUCCESSIVA: " + numStazioneSuccessiva);
			stazioni.get(numStazioneSuccessiva).setListaCapiDaLavorare(this.listaCapiDaLavorare);
			stazioni.get(numStazioneSuccessiva).caricamentoLavorazioniSospese();
		}

		this.svuotaStazione();
		return true;

	}
	
	public void assegnazionePostGuasto(int index) {
		this.messaInManutenzione(index);
		F2aFacade.getInstance().getLavorazioneCapiFacade().changeStatoStazione(this);
		F2aFacade.getInstance().getLavorazioneCapiFacade().assegnazioneManutentoreLibero(this);
	}
	
	//metodi per gestire l'inserimento degli observer nelle diverse stazioni
	
	public void aggiungiObserver(Observer observer) {
        observers.add(observer);
        //System.out.println("OBSERVER: " + observer);
        /*for (Observer obs : observers) {
        	System.out.println("AAA: " + obs);
        }*/
    }

    public void notificaObservers(int index) {
    	System.out.println("NOTIFICHE");
    	System.out.println(observers.size());
        for (Observer observer : observers) {
            observer.update(this, index);
        }
    }

}
