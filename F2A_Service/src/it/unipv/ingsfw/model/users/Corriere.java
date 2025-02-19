package it.unipv.ingsfw.model.users;

public class Corriere extends Dipendente {
	private StatoCorriere statoCorriere;

	public Corriere(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.statoCorriere = StatoCorriere.LIBERO;
	}
	
	public Corriere(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio, StatoCorriere statoCorriere) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.statoCorriere = statoCorriere;
	}
	
	//costruttore di copia
	public Corriere(Corriere elementoDaCopiare) {
		super(elementoDaCopiare.getIdDipendente(), elementoDaCopiare.getNome(), elementoDaCopiare.getCognome(), elementoDaCopiare.getCf(), elementoDaCopiare.getEmail(), elementoDaCopiare.getPassword(), elementoDaCopiare.getStipendio());
		this.statoCorriere = elementoDaCopiare.statoCorriere;
	}
	

	//AGGIUNTO IN DATA 19/02
	//Costruttore ALTERNATIVO
	public Corriere (String idDipendente) {
		super(idDipendente, null, null, null, null, null, 0);
		this.statoCorriere = null;
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
	
	
}
