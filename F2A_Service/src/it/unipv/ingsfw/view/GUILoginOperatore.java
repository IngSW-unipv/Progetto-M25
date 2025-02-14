package it.unipv.ingsfw.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GUILoginOperatore extends JFrame{
	
	/**
	 * @throws HeadlessException
	 */
	public GUILoginOperatore() throws HeadlessException {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		Image img = kit.getImage("properties/Logo_F2A_zoom.PNG");
		setIconImage(img);
		setTitle("Login Operatore");
		
		BarraMenu menu = new BarraMenu();
		setJMenuBar(menu);
        
		InsertPanel pannello = new InsertPanel();
		ButtonPanel b = new ButtonPanel();
		Container c = getContentPane();
		c.add(menu);
		c.add(pannello);
		c.add(b);
	}
	
	public static void main(String[] args) {
		GUILoginOperatore loginOp = new GUILoginOperatore();
		loginOp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginOp.setVisible(true);
	}
}
