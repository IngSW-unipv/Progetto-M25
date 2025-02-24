package it.unipv.ingsfw.view.clientepanel;
import java.awt.*;
import javax.swing.*;
	
public class RegistrazionePanel extends JPanel  {

	JLabel userNome;
	JLabel userCognome;
	JLabel userCf;
	JLabel userEmail;
	JLabel pwLabel;
	JTextField userTextNome;
	JTextField userTextCognome;
	JTextField userTextCf;
	JTextField userTextEmail;
	JTextField userTextPw;
    JButton regButton;
    JLabel spaceLabel1;
    
    
 	public RegistrazionePanel() {
 		super();
 		Toolkit kit = Toolkit.getDefaultToolkit();
 		Dimension screenSize = kit.getScreenSize();
 		int screenHeight = screenSize.height;
 		int screenWidth = screenSize.width;
 		setSize(screenWidth/6, screenHeight/6);
 		setLocation(screenWidth/4, screenHeight/4);
 		
 		setLayout(new BorderLayout());
 		userNome = new JLabel("Nome:");
 	    userTextNome = new JTextField(25);
 		userCognome = new JLabel("Cognome:");
 	    userTextCognome = new JTextField(25);
 		userCf = new JLabel("Codice fiscale:");
 	    userTextCf = new JTextField(25);
 		userEmail = new JLabel("Email:");
 	    userTextEmail = new JTextField(25);		
 	    pwLabel = new JLabel("Password:");
 	    userTextPw = new JPasswordField(25);
 	    
 	    JPanel areaDati = new JPanel(new GridLayout(6, 1));
 	    areaDati.add(userNome);
 	    areaDati.add(userTextNome);
 	    areaDati.add(userCognome);
 	    areaDati.add(userTextCognome);
 	    areaDati.add(userCf);
 	    areaDati.add(userTextCf);
 	    areaDati.add(userEmail);
 	    areaDati.add(userTextEmail);
 	    areaDati.add(pwLabel);
 	    areaDati.add(userTextPw);
 	    add(areaDati, BorderLayout.CENTER);
 	    
 	    regButton = new JButton("Registrati");
 		regButton.setActionCommand("Registrazione");
 		add(regButton, BorderLayout.SOUTH);
 	    
 	}


	public JLabel getUserNome() {
		return userNome;
	}
	
	public void setUserNome(JLabel userNome) {
		this.userNome = userNome;
	}
	
	public JLabel getUserCognome() {
		return userCognome;
	}

	public void setUserCognome(JLabel userCognome) {
		this.userCognome = userCognome;
	}

	public JLabel getUserCf() {
		return userCf;
	}

	public void setUserCf(JLabel userCf) {
		this.userCf = userCf;
	}

	public JLabel getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(JLabel userEmail) {
		this.userEmail = userEmail;
	}

	public JLabel getPwLabel() {
		return pwLabel;
	}

	public void setPwLabel(JLabel pwLabel) {
		this.pwLabel = pwLabel;
	}

	public JTextField getUserTextNome() {
		return userTextNome;
	}

	public void setUserTextNome(JTextField userTextNome) {
		this.userTextNome = userTextNome;
	}

	public JTextField getUserTextCognome() {
		return userTextCognome;
	}

	public void setUserTextCognome(JTextField userTextCognome) {
		this.userTextCognome = userTextCognome;
	}

	public JTextField getUserTextCf() {
		return userTextCf;
	}

	public void setUserTextCf(JTextField userTextCf) {
		this.userTextCf = userTextCf;
	}

	public JTextField getUserTextEmail() {
		return userTextEmail;
	}

	public void setUserTextEmail(JTextField userTextEmail) {
		this.userTextEmail = userTextEmail;
	}

	public JTextField getUserTextPw() {
		return userTextPw;
	}

	public void setUserTextPw(JTextField userTextPw) {
		this.userTextPw = userTextPw;
	}

	public JButton getRegButton() {
		return regButton;
	}

	public void setRegButton(JButton regButton) {
		this.regButton = regButton;
	}
}




