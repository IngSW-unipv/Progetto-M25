package it.unipv.ingsfw.view.clientepanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;
import it.unipv.ingsfw.controller.TotemAction;


public class ButtonRegLogPanel extends JPanel {
	JButton logButton;
	JButton regButton;
	JButton spegniButton;
	
	public ButtonRegLogPanel() {
		
 		super();
 		Toolkit kit = Toolkit.getDefaultToolkit();
 		Dimension screenSize = kit.getScreenSize();
 		int screenHeight = screenSize.height;
 		int screenWidth = screenSize.width;
 		setSize(screenWidth/6, screenHeight/6);
 		setLocation(screenWidth/4, screenHeight/4);
 		
 		setLayout(new BorderLayout());
		
		logButton = new JButton("Login");
	    logButton.setActionCommand("Log");	
		regButton = new JButton("Registrati");
	    regButton.setActionCommand("Reg");
	    JPanel utenteArea = new JPanel(new GridLayout(3, 1));
		utenteArea.add(regButton);
		utenteArea.add(logButton);	
		add(utenteArea, BorderLayout.CENTER);
	
	
		spegniButton = new JButton("Spegni sistema");
		spegniButton.setActionCommand("Spegni");
		JPanel ammArea = new JPanel(new GridLayout(1, 1));
		ammArea.add(spegniButton);
		add(ammArea, BorderLayout.SOUTH);

}
	public JButton getSpegniButton() {
		return spegniButton;
	}
	public void setSpegniButton(JButton spegniButton) {
		this.spegniButton = spegniButton;
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
