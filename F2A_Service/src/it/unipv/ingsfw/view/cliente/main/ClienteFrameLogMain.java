package it.unipv.ingsfw.view.cliente.main;
import javax.swing.*;

import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.users.*;
import it.unipv.ingsfw.view.clienteframe.ClienteFrameLog;
public class ClienteFrameLogMain {

	public static void main(String[] args) {
		//Cliente cl= new Cliente();
		//ClienteFrameLog c = new ClienteFrameLog(cl);
		Totem t= new Totem();
		ClienteFrameLog c = new ClienteFrameLog(t);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);
	}
}
