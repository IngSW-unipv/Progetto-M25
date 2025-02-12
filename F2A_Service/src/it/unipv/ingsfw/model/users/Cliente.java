package it.unipv.ingsfw.model.users;

public class Cliente {

	private String idCliente;
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private String password;

	/**
	 * @param idCliente
	 * @param nome
	 * @param cognome
	 * @param telefono
	 * @param email
	 * @param password
	 */
	public Cliente(String idCliente, String nome, String cognome, String telefono, String email, String password) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	}
	
	public Cliente(String idCliente) {
		super();
		this.idCliente = idCliente;
		this.nome = null;
		this.cognome = null;
		this.telefono = null;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		return "Cliente "+ idCliente + "\nNome: " + nome + "\nCognome: " + cognome + "\nTelefono: "
				+ telefono + "\nEmail: " + email + "\nPassword " + password;
	}

}
