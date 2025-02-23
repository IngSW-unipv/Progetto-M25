package it.unipv.ingsfw.model;

import java.text.ParseException;
import java.util.ArrayList;

public interface ICapoDAO {
	
	public ArrayList<Capo> selectAll();
	public ArrayList<Capo> selectCapoByStatoETipo(Capo input);
	public boolean updateStatoCapo(Capo inputSet);
	public String getNewIdCapo();
	public boolean insertCapo(Capo c) throws ParseException;
	public ArrayList<Capo> selectCapiDaRitirareByTappa(Capo input);
	public ArrayList<Capo> selectCapiDaConsegnareByTappa(Capo input);
	
}
