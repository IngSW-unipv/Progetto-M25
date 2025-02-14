package it.unipv.ingsfw.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel{
	
	public ButtonPanel() {
		super();
		setLayout(new BorderLayout());
		JButton loginButton = new JButton("Login");
		add(loginButton, BorderLayout.SOUTH);
	}
}
