package it.unipv.ingsfw.view.cliente;
import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.controller.TotemAction;
import it.unipv.ingsfw.model.users.Cliente;

public class ClienteFrameReg extends JFrame {
	
	RegistrazionePanel pannello;
	Container c;
	
	public ClienteFrameReg (Cliente cl) throws HeadlessException {
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
		setTitle ("Registrazione");
		
		
        JLabel cred = new JLabel("Inserisci le tue credenziali");
		pannello = new RegistrazionePanel();
		//ButtonPanel b = new ButtonPanel();
		c = getContentPane();
		c.add(cred);
		c.add(pannello);
		//c.add(b);
		setLayout(new BorderLayout());
		add(cred, BorderLayout.NORTH);
		add(pannello, BorderLayout.CENTER);
		//add(b, BorderLayout.SOUTH);
		new TotemAction(cl, this);
		
		/*RegistrazionePanel b = new RegistrazionePanel();
		Container c = getContentPane();
		c.add(b);*/
		
		
	}
	
	public RegistrazionePanel getPannello() {
		return pannello;
	}

	public void setPannello(RegistrazionePanel pannello) {
		this.pannello = pannello;
	}

	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}
}


