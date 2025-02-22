package it.unipv.ingsfw.view.cliente;

import javax.swing.JFrame;

import it.unipv.ingsfw.model.negozio.Totem;

public class CLienteFrameDepositoTest {
	public static void main(String[] args) {
		Totem t = new Totem();
		ClienteFrameDeposito c = new ClienteFrameDeposito(t);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);
	}
}
