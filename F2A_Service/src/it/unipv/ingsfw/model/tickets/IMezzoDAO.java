package it.unipv.ingsfw.model.tickets;
//MODIFICATO IN DATA 19/02
import java.util.ArrayList;

public interface IMezzoDAO {
	public ArrayList<Mezzo> selectAll();
	public ArrayList<Mezzo> selectByCapienza(Mezzo fornInput);
	public boolean insertMezzo(Mezzo m);
	Mezzo selectById(Mezzo m);
}
