package it.unipv.ingsfw.model.users;

public abstract class Dipendente {
	
	private String idDipendente;
	private String nome;
	private String cognome;
	private String cf;
	private String email;
	private String password;
	private int stipendio;
	
	/**
	 * @param idDipendente
	 * @param nome
	 * @param cognome
	 * @param cf
	 * @param email
	 * @param password
	 * @param stipendio
	 */
	public Dipendente(String idDipendente, String nome, String cognome, String cf, String email, String password,
			int stipendio) {
		super();
		this.idDipendente = idDipendente;
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.email = email;
		this.password = password;
		this.stipendio = stipendio;
	}
	
	public Dipendente(String email, String password) {
		super();
		this.idDipendente = null;
		this.nome = null;
		this.cognome = null;
		this.cf = null;
		this.email = email;
		this.password = password;
		this.stipendio = 0;
	}

	public String getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(String idDipendente) {
		this.idDipendente = idDipendente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStipendio() {
		return stipendio;
	}

	public void setStipendio(int stipendio) {
		this.stipendio = stipendio;
	}
	
	@Override
	public String toString() {
		return "Dipendente "+ idDipendente + "\nNome: " + nome + "\nCognome: " + cognome + "\ncf: "
				+ cf + "\nEmail: " + email + "\nPassword: " + password;
	}
	
	
	
}
