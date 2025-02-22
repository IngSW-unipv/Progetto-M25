package it.unipv.ingsfw.view.cliente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unipv.ingsfw.controller.TotemAction;
import it.unipv.ingsfw.model.negozio.Totem;

public class ClienteFrameRiepilogo extends JFrame {

	ClienteRiepilogoPanel pannello;
	Container c;

	public ClienteFrameRiepilogo (Totem t) throws HeadlessException {
		Toolkit kit = Toolkit.getDefaultToolkit ();
		Dimension screenSize = kit.getScreenSize ();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize (screenWidth/3, screenHeight/3) ; setLocation (screenWidth/4, screenHeight/4);
		setTitle ("Riepilogo-Pagamento");
		
		
        //JLabel cred = new JLabel("Qui puoi visualizzare il riepilogo dell'ordine e pagare:");
		pannello = new ClienteRiepilogoPanel();
		c = getContentPane();
		//c.add(cred);
		c.add(pannello);
		setLayout(new BorderLayout());
		//add(cred, BorderLayout.CENTER);
		add(pannello, BorderLayout.CENTER);
		new TotemAction(t, this);
		
	}

	public ClienteRiepilogoPanel getPannello() {
		return pannello;
	}

	public void setPannello(ClienteRiepilogoPanel pannello) {
		this.pannello = pannello;
	}

	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}
	

}
