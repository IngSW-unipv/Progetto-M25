package it.unipv.ingsfw.view.clienteframe;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unipv.ingsfw.controller.TotemAction;
import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.view.clientepanel.ClienteRitiroPanel;

public class ClienteFrameRitiro extends JFrame{
	ClienteRitiroPanel pannello;
	Container c;

	public ClienteFrameRitiro (Totem t) throws HeadlessException {
	Toolkit kit = Toolkit.getDefaultToolkit ();
	Dimension screenSize = kit.getScreenSize ();
	int screenHeight = screenSize.height;
	int screenWidth = screenSize.width;
	setSize (screenWidth/3, screenHeight/3) ; setLocation (screenWidth/4, screenHeight/4);
	setTitle ("Ritiro");
	
    JLabel cred = new JLabel("Sezione ritiro capi");
	pannello = new ClienteRitiroPanel();
	c = getContentPane();
	c.add(cred);
	c.add(pannello);
	setLayout(new BorderLayout());
	add(cred, BorderLayout.NORTH);
	add(pannello, BorderLayout.CENTER);
	new TotemAction(t, this);

	}



	public ClienteRitiroPanel getPannello() {
		return pannello;
	}



	public void setPannello(ClienteRitiroPanel pannello) {
		this.pannello = pannello;
	}



	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}


	
}
