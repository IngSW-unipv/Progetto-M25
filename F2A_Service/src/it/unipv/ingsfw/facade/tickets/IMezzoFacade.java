package it.unipv.ingsfw.facade.tickets;

import java.util.ArrayList;

import it.unipv.ingsfw.model.tickets.Mezzo;

public interface IMezzoFacade {

	public ArrayList<Mezzo> selectAll();
	public ArrayList<Mezzo> selectByCapienza(Mezzo fornInput);
	public boolean insertMezzo(Mezzo m);
	Mezzo selectById(Mezzo m);
}
