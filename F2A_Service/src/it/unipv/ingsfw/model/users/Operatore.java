package it.unipv.ingsfw.model.users;

public class Operatore extends Dipendente {
	
	private Enum tipoOperatore;

	public Operatore(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio, Enum tipoOperatore) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.tipoOperatore = tipoOperatore;
	}

	public Enum getTipoOperatore() {
		return tipoOperatore;
	}

	public void setTipoOperatore(Enum tipoOperatore) {
		this.tipoOperatore = tipoOperatore;
	}
	
	

}
