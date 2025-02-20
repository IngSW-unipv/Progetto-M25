package it.unipv.ingsfw.view.cliente;

import java.awt.FlowLayout;
import javax.swing.*;
import it.unipv.ingsfw.controller.TotemAction;


public class ButtonRegLogPanel extends JPanel {
	JButton logButton;
	JButton regButton;
	
	public ButtonRegLogPanel() {
		
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		
		logButton = new JButton("Login");
	    logButton.setActionCommand("Log");	
		
		regButton = new JButton("Registrati");
	    regButton.setActionCommand("Reg");
		
		add(regButton);
		add(logButton);	
	
	}

	public JButton getLogButton() {
		return logButton;
	}


	public void setLogButton(JButton logButton) {
		this.logButton = logButton;
	}
	
	public JButton getRegButton() {
		return regButton;
	}


	public void setRegButton(JButton regButton) {
		this.regButton = regButton;
	}
	
	
}
