package it.unipv.ingsfw.view.cliente;

import java.awt.*;
import javax.swing.*;
import it.unipv.ingsfw.controller.TotemAction;

public class ClienteFrameDepRit extends JFrame{ 

	ButtonDepRitPanel bdrp;
	
	public ClienteFrameDepRit () {
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
		setTitle ("Deposito-Ritiro Capi") ;
		
		bdrp = new ButtonDepRitPanel();
		
		Container c = getContentPane();
		c.add(bdrp, BorderLayout.CENTER);
		
		new TotemAction(this);
		
	}

	public ButtonDepRitPanel getBdrp() {
		return bdrp;
	}

	public void setBrlp(ButtonDepRitPanel bdrp) {
		this.bdrp = bdrp;
	}
	
	
}
