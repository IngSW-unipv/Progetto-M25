package it.unipv.ingsfw.model.users;

import java.util.ArrayList;

public interface IDipendenteDAO {
	public ArrayList<Dipendente> selectAll();
	public ArrayList<Dipendente> selectByTipo(Dipendente fornInput);
	public boolean insertDipendente(Dipendente f);
}
