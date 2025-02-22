package it.unipv.ingsfw.view.corriere;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar{

	private JMenu gestioneTicket;
	private JMenu modificaProfilo;
	
	public BarraMenu() {
		super();
		setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
		gestioneTicket = new JMenu("Finestra Scelta Ticket");
        modificaProfilo = new JMenu("Modifica Profilo");
        
        menuBar.add(gestioneTicket);
        menuBar.add(modificaProfilo);
        add(menuBar, BorderLayout.EAST);
	}

	public JMenu getGestioneTicket() {
		return gestioneTicket;
	}

	public void setGestioneTicket(JMenu gestioneTicket) {
		this.gestioneTicket = gestioneTicket;
	}

	public JMenu getModificaProfilo() {
		return modificaProfilo;
	}

	public void setModificaProfilo(JMenu modificaProfilo) {
		this.modificaProfilo = modificaProfilo;
	}



}
