package it.unipv.ingsfw.view.operatore;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class InsertPanel extends JPanel{
	
	public InsertPanel() {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		
		setLayout(new BorderLayout());
		JLabel userLabel = new JLabel("Email:");
	    JTextField userText = new JTextField(15);
	    JLabel passLabel = new JLabel("Password:");
	    JPasswordField passText = new JPasswordField(15);
	    JPanel areaDati = new JPanel();
	    areaDati.add(userLabel);
	    areaDati.add(userText);
	    areaDati.add(passLabel);
	    areaDati.add(passText);
	    add(areaDati, BorderLayout.WEST);
	    
	}
    
    
    
}

	
