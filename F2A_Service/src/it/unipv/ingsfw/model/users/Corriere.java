package it.unipv.ingsfw.model.users;

public class Corriere extends Dipendente {
	
	private Enum statoCorriere;

	public Corriere(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio, Enum statoCorriere) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.statoCorriere = statoCorriere;
	}

	public Enum getStatoCorriere() {
		return statoCorriere;
	}

	public void setStatoCorriere(Enum statoCorriere) {
		this.statoCorriere = statoCorriere;
	}

}
