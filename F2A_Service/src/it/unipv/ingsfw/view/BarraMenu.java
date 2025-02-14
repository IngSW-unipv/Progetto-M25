package it.unipv.ingsfw.view;

import java.awt.BorderLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar{

	
	public BarraMenu() {
		super();
		setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
        JMenu gestioneStazioni = new JMenu("Gestione Stazioni");
        JMenu loginMenu = new JMenu("Login");
        JMenu controlloMacchinari = new JMenu("Controllo Macchinari");
        menuBar.add(loginMenu);
        menuBar.add(gestioneStazioni);
        menuBar.add(controlloMacchinari);
        add(menuBar, BorderLayout.NORTH);
	}
	
	

}
