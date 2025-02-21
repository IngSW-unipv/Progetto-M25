package it.unipv.ingsfw.view.operatore.viewLogin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import it.unipv.ingsfw.controller.ManutentoreAction;
import it.unipv.ingsfw.controller.OperatoreAction;
import it.unipv.ingsfw.model.users.Operatore;

public class GUILoginOperatore extends JFrame implements Runnable{
	
	InsertPanel pannello;
	Container c;
	OperatoreAction op;
	ManutentoreAction m;
	/**
	 * @throws HeadlessException
	 */
	public GUILoginOperatore(Operatore o) throws HeadlessException {
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
		//add(b, BorderLayout.SOUTH);
		op = new OperatoreAction(o, this);
		m = new ManutentoreAction(o,this);
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

	public OperatoreAction getOp() {
		return op;
	}

	public void setOp(OperatoreAction op) {
		this.op = op;
	}

	public ManutentoreAction getM() {
		return m;
	}

	public void setM(ManutentoreAction m) {
		this.m = m;
	}

	


	public static void main(String[] args) {
		Operatore op = new Operatore();
		GUILoginOperatore loginOp = new GUILoginOperatore(op);
		loginOp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginOp.setVisible(true);
	}

	@Override
	public void run() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
		
	}
	
	public static void startGUI() {
		Operatore op = new Operatore();
        Thread t = new Thread(new GUILoginOperatore(op));
        try {
        	 t.start();
		} catch (IllegalThreadStateException e) {
			System.err.println("Thread già avviato");
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
       ;
    }
}
