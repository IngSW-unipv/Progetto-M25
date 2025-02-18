package it.unipv.ingsfw.model.users;

import java.util.ArrayList;

import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.CapoDAO;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazione;
import it.unipv.ingsfw.model.lavorazioneCapi.CatenaLavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.LavorazioneDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;

public class Operatore extends Dipendente {

	private TipoOperatore tipoOperatore;
	private ArrayList<ObservableStazioneLavoro> stazioniAssociate;

	public Operatore(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio, TipoOperatore tipoOperatore) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.tipoOperatore = tipoOperatore;
		stazioniAssociate = new ArrayList<ObservableStazioneLavoro>();
	}
	
	public Operatore(String email, String password) {
		super(email, password);
		this.tipoOperatore = null;
		stazioniAssociate = new ArrayList<ObservableStazioneLavoro>();
	}
	
	public Operatore(String idDipendente) {
		super(idDipendente);
		this.tipoOperatore = null;
		stazioniAssociate = new ArrayList<ObservableStazioneLavoro>();
	}
	
	public Operatore() {
		super(null, null);
	}

	public TipoOperatore getTipoOperatore() {
		return tipoOperatore;
	}

	public void setTipoOperatore(TipoOperatore tipoOperatore) {
		this.tipoOperatore = tipoOperatore;
	}

	// gestire eventualmente il getStazioniAssociate andando a prelevare da db le
	// stazioni che gli sono state assegnate e passarle all'oggetto sw relativo

	public ArrayList<ObservableStazioneLavoro> getStazioniAssociate() {
		return stazioniAssociate;
	}

	// assegnazione stazioni via db nella lista stazioni gestite dall'operatore
	// anche tutte dello stesso tipo
	// aggiunto anche il caso del manutentore a cui però viene assegnata una sola
	// stazione guasta alla volta

	public void setStazioniAssociate() {
		
		ObservableStazioneLavoroDAO staz = new ObservableStazioneLavoroDAO();
		
		if(staz.selectStazioniByOperatore(this).size() != 0) {
			stazioniAssociate = staz.selectStazioniByOperatore(this);
			
		}else {
			
			switch (tipoOperatore) {
			case RESPONSABILE_STAZIONE:

				ArrayList<ObservableStazioneLavoro> obsReady = staz.selectStazioniReadyNonAssegnate();

				for (int i = 0; i < 3; i++) {
					stazioniAssociate.add(obsReady.get(i));
					staz.assegnazioneOperatoreNoto(obsReady.get(i), this);
				}

				break;

			case MANUTENTORE:

				ArrayList<ObservableStazioneLavoro> obsMain = staz.selectStazioniMaintenanceNonAssegnate();
				stazioniAssociate.add(obsMain.get(0));
				staz.assegnazioneOperatoreNoto(obsMain.get(0), this);

				break;
			}
		}
		

	}

	@Override
	public String toString() {
		return super.toString() + "\nTipoOperatore: " + tipoOperatore;
	}

	// metodo per mandare in stato di working una stazione di lavorazione, andando
	// prima a controllare se questa contiene effettivamente dei capi da lavorare
	
	//uso attributo index utile per passare da GUI a esecuzione del metodo

	public void avviaStazione(int index) throws Exception{
		ObservableStazioneLavoroDAO dao = new ObservableStazioneLavoroDAO();
		
		setStazioniAssociate();
		if (stazioniAssociate.get(index).checkPresenzaCapi()) {
			System.out.println("aaaaaaaaaaaaaa");
			stazioniAssociate.get(index).messaInLavorazione();
			
			dao.changeStatoStazione(stazioniAssociate.get(index));
			
			if(!stazioniAssociate.get(index).getTipo().toString().equalsIgnoreCase("LAVAGGIO")) {
				stazioniAssociate.get(index).caricamentoLavorazioni();
			}

			// attendere qualche secondo ....
			
			for(int i = 0; i < 10000000; i++) {
				
			}
			
			//COMMENTI SOTTOSTANTI DA TOGLIERE PER POTER EFFETTUARE UNA LAVORAZIONE COMPLETA ALL'INTERNO DELLA STAZIONE

			stazioniAssociate.get(index).messaInStandBy();
			
			System.out.println("Lavorazione completata");

			
			dao.changeStatoStazione(stazioniAssociate.get(index));
			stazioniAssociate.get(index).removeCapiStazione();

			

		}else {
			System.err.println("Capi assenti");
			
		}
	}
	
	public boolean verificaCredenzialiAccesso(String email, String password) {
		DipendenteDAO dip = new DipendenteDAO();
		boolean t = dip.selectByEmailPassword(new Operatore(email,password));
		return t;
	}

	public static void main(String[] args) {

		DipendenteDAO dip = new DipendenteDAO();
		ArrayList<Operatore> listaOp = dip.selectResponsabiliStazioneNonAssegnati();
		ArrayList<Operatore> listaOpMan = dip.selectManutentoriNonAssegnati();
		
		for (Operatore o : listaOp)
			System.out.println(o);

		for (Operatore o : listaOpMan)
			System.out.println(o);

		/*
		 * Operatore o = listaOp.get(0); o.setStazioniAssociate();
		 * for(ObservableStazioneLavoro s : o.stazioniAssociate) {
		 * System.out.println(s); }
		 */
	}
}
