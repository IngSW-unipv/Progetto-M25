package it.unipv.ingsfw.model.users;

import java.util.ArrayList;

public interface IClienteDAO {
	public ArrayList<Cliente> selectAll();
	public Cliente selectClienteByEmailEPassword(Cliente Input);
	public String selectIdByEmailPassword(Cliente input);
	public boolean insertCliente(Cliente c);
	public String getNewIdCliente();
}
