package it.unipv.ingsfw.model.users;

public class Cliente {

	private String idCliente;
	private String nome;
	private String cognome;
	private String cf;
	private String email;
	private String password;

	/**
	 * @param idCliente
	 * @param nome
	 * @param cognome
	 * @param cf
	 * @param email
	 * @param password
	 */
	public Cliente(String idCliente, String nome, String cognome, String cf, String email, String password) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.email = email;
		this.password = password;
	}
	
	public Cliente(String idCliente) {
		super();
		this.idCliente = idCliente;
		this.nome = null;
		this.cognome = null;
		this.cf = null;
		this.email = null;
		this.password = null;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
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

	@Override
	public String toString() {
		return "Cliente "+ idCliente + "\nNome: " + nome + "\nCognome: " + cognome + "\nCf: "
				+ cf + "\nEmail: " + email + "\nPassword " + password;
	}

}
