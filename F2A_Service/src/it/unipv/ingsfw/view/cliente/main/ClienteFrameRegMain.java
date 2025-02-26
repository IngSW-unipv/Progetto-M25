package it.unipv.ingsfw.view.cliente.main;
import javax.swing.*;

import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.view.clienteframe.ClienteFrameReg;

public class ClienteFrameRegMain {

	public static void main(String[] args) {
		Totem t= new Totem();
		ClienteFrameReg c = new ClienteFrameReg(t);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);

	}

}
