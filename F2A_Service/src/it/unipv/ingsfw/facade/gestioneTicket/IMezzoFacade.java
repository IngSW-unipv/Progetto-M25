package it.unipv.ingsfw.facade.gestioneTicket;

import java.util.ArrayList;

import it.unipv.ingsfw.model.tickets.Mezzo;

public interface IMezzoFacade {

	public ArrayList<Mezzo> selectAllMezzo();
	public ArrayList<Mezzo> selectMezzoByCapienza(Mezzo fornInput);
	public boolean insertMezzo(Mezzo m);
	Mezzo selectMezzoById(Mezzo m);
}
