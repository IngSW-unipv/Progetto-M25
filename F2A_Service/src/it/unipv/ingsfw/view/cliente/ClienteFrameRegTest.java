package it.unipv.ingsfw.view.cliente;
import javax.swing.*;

import it.unipv.ingsfw.model.users.Cliente;

public class ClienteFrameRegTest {

	public static void main(String[] args) {
		Cliente cl= new Cliente();
		ClienteFrameReg c = new ClienteFrameReg(cl);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);

	}

}
