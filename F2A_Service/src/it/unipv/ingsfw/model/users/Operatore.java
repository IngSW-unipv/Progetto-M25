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

	@Override
	public String toString() {
		return super.toString() + "\nTipoOperatore: " + tipoOperatore;
	}

	// metodo per mandare in stato di working una stazione di lavorazione, andando
	// prima a controllare se questa contiene effettivamente dei capi da lavorare

	public void avviaStazione(int index) {
		ObservableStazioneLavoroDAO dao = new ObservableStazioneLavoroDAO();

		if (stazioniAssociate.get(index).checkPresenzaCapi()) {
			stazioniAssociate.get(index).setStatoStazione(StatoStazione.WORKING);
			dao.changeStatoStazione(stazioniAssociate.get(index));
			stazioniAssociate.get(index).caricamentoLavorazioni();

			// attendere qualche secondo ....

			stazioniAssociate.get(index).setStatoStazione(StatoStazione.READY);

			removeCapiStazione(stazioniAssociate.get(index));

			dao.changeStatoStazione(stazioniAssociate.get(index));

		}
	}

	public void removeCapiStazione(ObservableStazioneLavoro o) {

		CatenaLavorazioneDAO cat = new CatenaLavorazioneDAO();

		ArrayList<ObservableStazioneLavoro> stazioni = cat.selectStazioniByCatena(new CatenaLavorazione(o.getIdCatena()));

		if (o.getTipo().toString().equals("STIRATURA") || cat.selectCatenaByStazione(o).getTipoLavaggio().toString().equals("PELLE")) {

			CapoDAO cap = new CapoDAO();

			for (Capo c : o.getListaCapiDaLavorare()) {
				c.setStatoCapo(StatoCapo.IN_CONSEGNA);
				cap.updateStatoCapo(c);
			}

		} else {
			int numStazioneSuccessiva = stazioni.indexOf(o) + 1;
			stazioni.get(numStazioneSuccessiva).caricamentoLavorazioniSospese();
		}

		o.svuotaStazione();

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
