package it.unipv.ingsfw.model;

import java.util.ArrayList;

public interface ICapoDAO {
	
	public ArrayList<Capo> selectAll();
	public ArrayList<Capo> selectByStatoCapo(Capo fornInput);
	public boolean insertCapo(Capo c);
	
}
