package it.unipv.ingsfw.model.users;

import java.util.ArrayList;

public interface IClienteDAO {
	public ArrayList<Cliente> selectAll();
	public ArrayList<Cliente> selectByEmailEPassword(Cliente fornInput);
	public boolean insertCliente(Cliente c);
}
