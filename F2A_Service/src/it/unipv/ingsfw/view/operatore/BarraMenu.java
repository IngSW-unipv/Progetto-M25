package it.unipv.ingsfw.view.operatore;

import java.awt.BorderLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar{

	
	public BarraMenu() {
		super();
		setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
        JMenu gestioneStazioni = new JMenu("Gestione Stazioni");
        JMenu controlloMacchinari = new JMenu("Controllo Macchinari");
        JMenu modificaProfilo = new JMenu("Modifica Profilo");
        menuBar.add(gestioneStazioni);
        menuBar.add(controlloMacchinari);
        menuBar.add(modificaProfilo);
        add(menuBar, BorderLayout.CENTER);
	}
	
	

}
