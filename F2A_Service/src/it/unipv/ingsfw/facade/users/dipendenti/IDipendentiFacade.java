package it.unipv.ingsfw.facade.users.dipendenti;

import java.util.ArrayList;

import it.unipv.ingsfw.model.users.Corriere;
import it.unipv.ingsfw.model.users.Dipendente;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.model.users.TipoOperatore;

public interface IDipendentiFacade {

	public ArrayList<Dipendente> selectAll();
	//public ArrayList<Dipendente> selectById(Dipendente input);
	public ArrayList<Corriere> selectCorrieri();
	public ArrayList<Corriere> selectCorrieriLiberi();
	public ArrayList<Operatore> selectResponsabiliStazioneNonAssegnati();
	public ArrayList<Operatore> selectManutentoriNonAssegnati();
	public ArrayList<Operatore> selectByTipoOperatore(Operatore input);
	public boolean selectByEmailPassword(Dipendente input);
	public String selectIdByEmailPassword(Dipendente input);
	public String getNewIdDipendente();
	public boolean insertDipendente(Dipendente d);
	public TipoOperatore selectTipoOperatoreById(Dipendente input);
	public Operatore selectOperatoreByEmailPassword(Dipendente input);
	public Corriere selectCorriereById(Corriere cF);
	public Corriere selectCorriereByEmailPassword(Corriere input);
}
