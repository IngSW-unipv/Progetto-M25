package it.unipv.ingsfw.model.users;

public class Operatore extends Dipendente {
	
	private TipoOperatore tipoOperatore;

	public Operatore(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio, TipoOperatore tipoOperatore) {
		super(idDipendente, nome, cognome, cf, email, password, stipendio);
		this.tipoOperatore = tipoOperatore;
	}

	public TipoOperatore getTipoOperatore() {
		return tipoOperatore;
	}

	public void setTipoOperatore(TipoOperatore tipoOperatore) {
		this.tipoOperatore = tipoOperatore;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nTipoOperatore " + tipoOperatore;
	}
	

}
