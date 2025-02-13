package it.unipv.ingsfw.model.tickets;

import java.util.ArrayList;

public interface IMezzoDAO {
	public ArrayList<Mezzo> selectAll();
	public ArrayList<Mezzo> selectByCapienza(Mezzo fornInput);
	public boolean insertMezzo(Mezzo m);
}
