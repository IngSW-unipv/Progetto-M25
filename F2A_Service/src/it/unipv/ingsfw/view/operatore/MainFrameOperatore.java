package it.unipv.ingsfw.view.operatore;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import it.unipv.ingsfw.controller.OperatoreAction;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.view.operatore.viewLogin.InsertPanel;

public class MainFrameOperatore extends JFrame{
	
	BarraMenu menu;
	InsertPanel pannello;
	Container c;
	/**
	 * @throws HeadlessException
	 */
	public MainFrameOperatore(Operatore o) throws HeadlessException {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		Image img = kit.getImage("properties/Logo_F2A_zoom.PNG");
		setIconImage(img);
		setTitle("MainFrame");
		
		menu = new BarraMenu();
		setJMenuBar(menu);
        
		//pannello = new InsertPanel();
		//ButtonPanel b = new ButtonPanel();
		c = getContentPane();
		c.add(menu);
		//c.add(pannello);
		//c.add(b);
		setLayout(new BorderLayout());
		add(menu, BorderLayout.NORTH);
		//add(pannello, BorderLayout.CENTER);
		//add(b, BorderLayout.SOUTH);
		//OperatoreAction opAction1 = new OperatoreAction(o, this);
	}
	
	public BarraMenu getMenu() {
		return menu;
	}

	public void setMenu(BarraMenu menu) {
		this.menu = menu;
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


}
