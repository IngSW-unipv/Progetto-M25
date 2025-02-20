package it.unipv.ingsfw.view.cliente;
import java.awt.*;
import javax.swing.*;

public class ClienteFrameReg extends JFrame {
	public ClienteFrameReg () {
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
		
		RegistrazionePanel b = new RegistrazionePanel();
		Container c = getContentPane();
		c.add(b);
		
		
	}
}


