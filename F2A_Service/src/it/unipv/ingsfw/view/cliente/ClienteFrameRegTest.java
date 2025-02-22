package it.unipv.ingsfw.view.cliente;
import javax.swing.*;

import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.users.Cliente;

public class ClienteFrameRegTest {

	public static void main(String[] args) {
		//Cliente cl= new Cliente();
		//ClienteFrameReg c = new ClienteFrameReg(cl);
		Totem t= new Totem();
		ClienteFrameReg c = new ClienteFrameReg(t);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);

	}

}
