package it.unipv.ingsfw.facade.capo;

import java.text.ParseException;
import java.util.ArrayList;

import it.unipv.ingsfw.model.Capo;

public interface ICapoFacade {
	
	public ArrayList<Capo> selectAll();
	public ArrayList<Capo> selectCapoByStatoETipo(Capo input);
	public boolean updateStatoCapo(Capo inputSet);
	public String getNewIdCapo();
	public boolean insertCapo(Capo c) throws ParseException;
	public String getStatoCapoById(Capo c);

}
