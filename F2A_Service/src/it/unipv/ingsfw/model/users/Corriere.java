package it.unipv.ingsfw.model.users;


import it.unipv.ingsfw.facade.F2aFacade;

public class Corriere extends Dipendente {
	private StatoCorriere statoCorriere;

	/**
	public Corriere(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.statoCorriere = StatoCorriere.LIBERO;
	}**/
	
	public Corriere(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio, StatoCorriere statoCorriere) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.statoCorriere = statoCorriere;
	}
	
	//non servono altre info, costruttore per la verifica
	public Corriere(String email, String password) {
		super(email, password);
	}
	
	//costruttore di copia
	/**public Corriere(Corriere elementoDaCopiare) {
		super(elementoDaCopiare.getIdDipendente(), elementoDaCopiare.getNome(), elementoDaCopiare.getCognome(), elementoDaCopiare.getCf(), elementoDaCopiare.getEmail(), elementoDaCopiare.getPassword(), elementoDaCopiare.getStipendio());
		this.statoCorriere = elementoDaCopiare.statoCorriere;
	}**/
	

	//AGGIUNTO IN DATA 19/02
	//Costruttore ALTERNATIVO
	public Corriere (String idDipendente) {
		super(idDipendente, null, null, null, null, null, 0);
		this.statoCorriere = null;
	}
	
	//Costruttore nullo, utile in fase di login
	public Corriere() {
		super(null, null);
	}

	public StatoCorriere getStatoCorriere() {
		return statoCorriere;
	}

	public void setStatoCorriere(StatoCorriere statoCorriere) {
		this.statoCorriere = statoCorriere;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nStatoCorriere " + statoCorriere;
	}
	
	//AGGIUNTO IN DATA 19/02
	public Corriere getCorriereByID() {
		DipendenteDAO dao = new DipendenteDAO();
		Corriere c = dao.selectCorriereById(this);
		return c;
	}
	
	public boolean verificaCredenzialiAccesso(String email, String password) {
		//DipendenteDAO dip = new DipendenteDAO();
		boolean t = F2aFacade.getInstance().getDipendentiFacade().selectByEmailPassword(new Corriere(email, password));
		return t;
	}
	
}
