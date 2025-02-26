package it.unipv.ingsfw.model.lavorazioneCapi;
import java.util.ArrayList;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.CapoDAO;
import it.unipv.ingsfw.model.StatoCapo;
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
	
	public CatenaLavorazione(String idCatena) {
		super();
		this.idCatena = idCatena;
		this.tipoLavaggio = null;
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

	/*
	 * public void setListaStazioni(ArrayList<StazioneLavoro> listaStazioni) {
	 * this.listaStazioni = listaStazioni; }
	 */

	public void checkStatoStazione(ObservableStazioneLavoro stazione) {
		stazione.getStatoStazione().toString();
	}

	/*
	 * public boolean svuotaStazione(StazioneLavoro stazione) { for(StazioneLavoro s
	 * : listaStazioni) { if(s.equals(stazione))
	 * 
	 * } }
	 */

	public boolean tipoStazioneAlreadyExists(ObservableStazioneLavoro s) {
		for (ObservableStazioneLavoro o : listaStazioni) {
			if (s.getTipo().equals(o.getTipo()))
				return true;
		}
		return false;
	}

	public boolean addStazioneLavoro(ObservableStazioneLavoro newStazione) {
		//ObservableStazioneLavoroDAO stazione = new ObservableStazioneLavoroDAO();

		if (listaStazioni.size() <= 2 && !tipoLavaggio.equals(TipoLavaggio.PELLE) && !tipoStazioneAlreadyExists(newStazione)) {
			listaStazioni.add(newStazione);
			if (F2aFacade.getInstance().getLavorazioneCapiFacade().insertStazioneWithKnownCatena(newStazione, this)) {
				return true;
			}else {
				if(listaStazioni.size() > 3)
					listaStazioni.removeLast();
				return false;
			}

		}
		return false;

	}

	public boolean riempimentoListaStazioniDaDB() {
		//CatenaLavorazioneDAO catena = new CatenaLavorazioneDAO();
		if (listaStazioni.size() == 0) {
			listaStazioni = F2aFacade.getInstance().getLavorazioneCapiFacade().selectStazioniByCatena(this);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String catena = "Catena " + idCatena + "\nTipoLavaggio: " + tipoLavaggio.toString();
		for (ObservableStazioneLavoro s : listaStazioni)
			s.toString();
		return catena;
	}
	
	/*
	public static void main(String[] args) {
		
		//CatenaLavorazioneDAO cat = new CatenaLavorazioneDAO();
		CatenaLavorazione newCat = new CatenaLavorazione("CAT002", TipoLavaggio.BIANCHI);
		//cat.insertCatena(newCat);
		
		//CapoDAO cap = new CapoDAO();
		ArrayList<Capo> capi = F2aFacade.getInstance().getCapoFacade().selectCapoByStatoETipo(new Capo(StatoCapo.IN_LAVORAZIONE, newCat.getTipoLavaggio()));
		
		for(Capo c : capi)
			System.out.println(c);
		
		//ESEMPIO LAVORAZIONE IN UNA CATENA COMPOSTA DA 2/3 STAZIONI DI LAVORO
		
		newCat.getListaStazioni().get(0).getListaCapiDaLavorare().addAll(capi);
		
		//azione operatore
		newCat.getListaStazioni().get(0).setStatoStazione(StatoStazione.WORKING);
		
		//aspetto un tot.....
		
		newCat.getListaStazioni().get(0).setStatoStazione(StatoStazione.READY);
		//azione operatore
		ArrayList<Capo> capiDaSpostare = newCat.getListaStazioni().get(0).getListaCapiDaLavorare();
		newCat.getListaStazioni().get(1).getListaCapiDaLavorare().addAll(capiDaSpostare);
		newCat.getListaStazioni().get(1).setStatoStazione(StatoStazione.WORKING);
		
		//aspetto un tot...
		
		newCat.getListaStazioni().get(1).setStatoStazione(StatoStazione.READY);
		
		if(!newCat.getTipoLavaggio().toString().equals("PELLE")) {
			//azione operatore
			newCat.getListaStazioni().get(2).getListaCapiDaLavorare().addAll(capiDaSpostare);
			newCat.getListaStazioni().get(2).setStatoStazione(StatoStazione.WORKING);
		}
		
		//cambio stato capi a db da IN_LAVORAZIONE a IN_CONSEGNA
		
		for(Capo c : capi) {
			c.setStatoCapo(StatoCapo.IN_CONSEGNA);
			F2aFacade.getInstance().getCapoFacade().updateStatoCapo(c);
		}
			
			
		
		
	}*/
}
