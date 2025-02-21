package it.unipv.ingsfw.view.cliente;
import java.awt.*;
import javax.swing.*;
import it.unipv.ingsfw.controller.TotemAction;
import it.unipv.ingsfw.model.users.Cliente;
public class ClienteFrameDeposito extends JFrame {

	ClienteDepositoPanel pannello;
	Container c;

	public ClienteFrameDeposito (Cliente cl) throws HeadlessException {
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
		setTitle ("Deposito");
		
		
		
		
		
        JLabel cred = new JLabel("Definisci i seguenti parametri");
		pannello = new ClienteDepositoPanel();
		c = getContentPane();
		c.add(cred);
		c.add(pannello);
		setLayout(new BorderLayout());
		add(cred, BorderLayout.NORTH);
		add(pannello, BorderLayout.CENTER);
		new TotemAction(cl, this);
		
	}
	
	public ClienteDepositoPanel getPannello() {
		return pannello;
	}

	public void setPannello(ClienteDepositoPanel pannello) {
		this.pannello = pannello;
	}

	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}
}


