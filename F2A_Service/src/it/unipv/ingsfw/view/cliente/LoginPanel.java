package it.unipv.ingsfw.view.cliente;
import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {
	
	JLabel userLabel;
	JTextField userText;
	JLabel passLabel;
    JPasswordField passText;
    JButton loginButton;
    JLabel spaceLabel1;
    
    
 	public LoginPanel() {
 		super();
 		Toolkit kit = Toolkit.getDefaultToolkit();
 		Dimension screenSize = kit.getScreenSize();
 		int screenHeight = screenSize.height;
 		int screenWidth = screenSize.width;
 		setSize(screenWidth/6, screenHeight/6);
 		setLocation(screenWidth/4, screenHeight/4);
 		
 		setLayout(new BorderLayout());
 		userLabel = new JLabel("Email:");
 	    userText = new JTextField(25);
 		spaceLabel1 = new JLabel("    ");
 	    passLabel = new JLabel("Password:");
 	    passText = new JPasswordField(25);
 	    
 	    JPanel areaDati = new JPanel(new GridLayout(6, 1));
 	    areaDati.add(spaceLabel1);
 	    areaDati.add(spaceLabel1);
 	    areaDati.add(userLabel);
 	    areaDati.add(userText);
 	    areaDati.add(spaceLabel1);
 	    areaDati.add(spaceLabel1);
 	    areaDati.add(passLabel);
 	    areaDati.add(passText);
 	    areaDati.add(spaceLabel1);
 	    areaDati.add(spaceLabel1);
 	    add(areaDati, BorderLayout.CENTER);
 	    
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