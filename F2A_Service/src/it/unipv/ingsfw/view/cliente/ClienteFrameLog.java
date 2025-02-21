package it.unipv.ingsfw.view.cliente;
import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.controller.TotemAction;
import it.unipv.ingsfw.model.users.Cliente;
public class ClienteFrameLog extends JFrame{
	
	LoginPanel pannello;
	Container c;
	
	public ClienteFrameLog (Cliente cl) throws HeadlessException {
		super();
		/*	setSize (WIDTH, HEIGHT);
		}
		public static final int WIDTH =300;
		public static final int HEIGHT =200;
		 */
		Toolkit kit = Toolkit.getDefaultToolkit ();
		Dimension screenSize = kit.getScreenSize ();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize (screenWidth/3, screenHeight/3) ; 
		setLocation (screenWidth/4, screenHeight/4);
		setTitle ("Login") ;
		
	        JLabel cred = new JLabel("Inserisci le tue credenziali");
			pannello = new LoginPanel();
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
		}

		public LoginPanel getPannello() {
			return pannello;
		}

		public void setPannello(LoginPanel pannello) {
			this.pannello = pannello;
		}

		public Container getC() {
			return c;
		}

		public void setC(Container c) {
			this.c = c;
		}


		/*public static void main(String[] args) {
			Operatore op = new Operatore();
			GUILoginOperatore loginOp = new GUILoginOperatore(op);
			loginOp.setDefaultCloseOperation(EXIT_ON_CLOSE);
			loginOp.setVisible(true);
		}*/
	

		
		/*LoginPanel b = new LoginPanel();
		Container c = getContentPane();
		c.add(b);
		*/
}

