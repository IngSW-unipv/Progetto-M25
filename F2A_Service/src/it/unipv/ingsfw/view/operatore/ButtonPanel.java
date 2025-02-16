package it.unipv.ingsfw.view.operatore;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel{
	
	JButton loginButton;
	
	public ButtonPanel() {
		super();
		setLayout(new BorderLayout());
		loginButton = new JButton("Login");
		loginButton.setActionCommand("accesso");
		
		add(loginButton, BorderLayout.SOUTH);
	}
	
	public JButton getLoginButton() {
		
		return loginButton;
	}
	
}
