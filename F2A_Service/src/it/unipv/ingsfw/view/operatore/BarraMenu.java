package it.unipv.ingsfw.view.operatore;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar{

	private JMenu gestioneStazioni;
	private JMenu controlloMacchinari;
	private JMenu modificaProfilo;
	private JMenu refreshButton;
	
	public BarraMenu() {
		super();
		setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
        gestioneStazioni = new JMenu("Gestione Stazioni");
        controlloMacchinari = new JMenu("Controllo Macchinari");
        modificaProfilo = new JMenu("Modifica Profilo");
        
        refreshButton = new JMenu("Aggiorna stazioni");
        menuBar.add(refreshButton);
        
        
        menuBar.add(gestioneStazioni);
        menuBar.add(controlloMacchinari);
        menuBar.add(modificaProfilo);
        add(menuBar, BorderLayout.CENTER);
	}

	public JMenu getGestioneStazioni() {
		return gestioneStazioni;
	}

	public void setGestioneStazioni(JMenu gestioneStazioni) {
		this.gestioneStazioni = gestioneStazioni;
	}

	public JMenu getControlloMacchinari() {
		return controlloMacchinari;
	}

	public void setControlloMacchinari(JMenu controlloMacchinari) {
		this.controlloMacchinari = controlloMacchinari;
	}

	public JMenu getModificaProfilo() {
		return modificaProfilo;
	}

	public void setModificaProfilo(JMenu modificaProfilo) {
		this.modificaProfilo = modificaProfilo;
	}

	public JMenu getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JMenu refreshButton) {
		this.refreshButton = refreshButton;
	}
	
	

}
