package it.unipv.ingsfw.view.cliente;
import javax.swing.*;
import it.unipv.ingsfw.model.users.*;
public class ClienteFrameLogTest {

	public static void main(String[] args) {
		Cliente cl= new Cliente();
		ClienteFrameLog c = new ClienteFrameLog(cl);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);
	}
}
