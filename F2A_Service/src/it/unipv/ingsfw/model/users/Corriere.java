package it.unipv.ingsfw.model.users;

public class Corriere extends Dipendente {
	
	private StatoCorriere statoCorriere;

	public Corriere(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio, StatoCorriere statoCorriere) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.statoCorriere = statoCorriere;
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
}
