package it.unipv.ingsfw.view.operatore.viewLogin;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InsertPanel extends JPanel{
	
	JLabel userLabel;
	JTextField userText;
	JLabel passLabel;
    JPasswordField passText;
    JButton loginButton;
    
    
	public InsertPanel() {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		
		setLayout(new BorderLayout());
		userLabel = new JLabel("Email:");
	    userText = new JTextField(20);
	    passLabel = new JLabel("Password:");
	    passText = new JPasswordField(20);
	    JPanel areaDati = new JPanel();
	    areaDati.add(userLabel);
	    areaDati.add(userText);
	    areaDati.add(passLabel);
	    areaDati.add(passText);
	    add(areaDati, BorderLayout.WEST);
	    
	    loginButton = new JButton("Login");
		loginButton.setActionCommand("accesso");
		add(loginButton, BorderLayout.SOUTH);
	    
	}
	

	public JTextField getUserText() {
		return userText;
	}


	public void setUserText(JTextField userText) {
		this.userText = userText;
	}


	public JPasswordField getPassText() {
		return passText;
	}


	public void setPassText(JPasswordField passText) {
		this.passText = passText;
	}


	public JButton getLoginButton() {
		return loginButton;
	}


	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}
	
	
    
    
    
}

	
