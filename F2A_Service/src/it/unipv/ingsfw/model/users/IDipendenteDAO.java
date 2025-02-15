package it.unipv.ingsfw.model.users;

import java.util.ArrayList;

public interface IDipendenteDAO {
	public ArrayList<Dipendente> selectAll();
	//public ArrayList<Dipendente> selectById(Dipendente input);
	public ArrayList<Corriere> selectCorrieri();
	public ArrayList<Corriere> selectCorrieriLiberi();
	public ArrayList<Operatore> selectResponsabiliStazioneNonAssegnati();
	public ArrayList<Operatore> selectManutentoriNonAssegnati();
	public ArrayList<Operatore> selectByTipoOperatore(Operatore input);
	public boolean selectByEmailPassword(Dipendente input);
	public boolean insertDipendente(Dipendente d);
}
