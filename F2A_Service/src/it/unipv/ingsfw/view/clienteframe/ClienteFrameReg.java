package it.unipv.ingsfw.view.clienteframe;
import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.controller.TotemAction;
import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.view.clientepanel.RegistrazionePanel;

public class ClienteFrameReg extends JFrame {
	
	RegistrazionePanel pannello;
	Container c;
	
	//public ClienteFrameReg (Cliente cl) throws HeadlessException {
	public ClienteFrameReg (Totem t) throws HeadlessException {
		
		
		Toolkit kit = Toolkit.getDefaultToolkit ();
		Dimension screenSize = kit.getScreenSize ();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize (screenWidth/3, screenHeight/3) ; setLocation (screenWidth/4, screenHeight/4);
		setTitle ("Registrazione");
		
		
        JLabel cred = new JLabel("Inserisci le tue credenziali");
		pannello = new RegistrazionePanel();

		c = getContentPane();
		c.add(cred);
		c.add(pannello);

		setLayout(new BorderLayout());
		add(cred, BorderLayout.NORTH);
		add(pannello, BorderLayout.CENTER);

		//new TotemAction(cl, this);
		new TotemAction(t, this);

		
		
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


