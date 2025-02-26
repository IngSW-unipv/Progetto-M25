package it.unipv.ingsfw.view.corriere.viewLogin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unipv.ingsfw.controller.TicketAction;

public class GUILoginCorriere extends JFrame{
	
	InsertPanel pannello;
	Container c;
	/**
	 * @throws HeadlessException
	 */
	public GUILoginCorriere() throws HeadlessException {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		Image img = kit.getImage("properties/Logo_F2A_zoom.PNG");
		setIconImage(img);
		setTitle("Login Corriere");
		
        JLabel cred = new JLabel("Inserisci le tue credenziali");
		pannello = new InsertPanel();
		//ButtonPanel b = new ButtonPanel();
		c = getContentPane();
		c.add(cred);
		c.add(pannello);
		//c.add(b);
		setLayout(new BorderLayout());
		add(cred, BorderLayout.NORTH);
		add(pannello, BorderLayout.CENTER);
		new TicketAction(this);
	}

	public InsertPanel getPannello() {
		return pannello;
	}

	public void setPannello(InsertPanel pannello) {
		this.pannello = pannello;
	}

	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}


	public static void main(String[] args) {
		GUILoginCorriere loginCor = new GUILoginCorriere();
		loginCor.setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginCor.setVisible(true);
	}
}
