package it.unipv.ingsfw.view.cliente;

import java.awt.*;

import javax.swing.*;

import it.unipv.ingsfw.controller.TotemAction;

public class ClienteFrameRegLog extends JFrame{
	
	ButtonRegLogPanel brlp;
	
	public ClienteFrameRegLog () {
	/*	setSize (WIDTH, HEIGHT);
	}
	public static final int WIDTH =300;
	public static final int HEIGHT =200;
	*/
		Toolkit kit = Toolkit.getDefaultToolkit ();
		Dimension screenSize = kit.getScreenSize ();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize (screenWidth/3, screenHeight/3) ; setLocation (screenWidth/4, screenHeight/4);
		setTitle ("Registrazione-Login") ;
		
		brlp = new ButtonRegLogPanel();
		
		Container c = getContentPane();
		c.add(brlp, BorderLayout.CENTER);
		
		new TotemAction(this);
		
	}

	public ButtonRegLogPanel getBrlp() {
		return brlp;
	}

	public void setBrlp(ButtonRegLogPanel brlp) {
		this.brlp = brlp;
	}
	
	
}
