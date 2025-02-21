package it.unipv.ingsfw.facade.users.clienti;

import java.util.ArrayList;

import it.unipv.ingsfw.model.users.Cliente;

public interface IClientiFacade {
	
	public ArrayList<Cliente> selectAll();
	public Cliente selectClienteByEmailEPassword(Cliente Input);
	public String selectIdClienteByEmailPassword(Cliente input);
	public boolean insertCliente(Cliente c);
	public String getNewIdCliente();
}
