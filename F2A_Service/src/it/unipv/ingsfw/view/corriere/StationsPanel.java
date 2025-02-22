package it.unipv.ingsfw.view.corriere;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.users.Operatore;


public class StationsPanel extends JPanel{
	
	JRadioButton button1;
	JRadioButton button2;
	JRadioButton button3;
	JLabel mostraScelta;
    JButton scelta;
    BottoneStatoStazione staz1;
    BottoneStatoStazione staz2;
    BottoneStatoStazione staz3;
    Operatore op;
    
    
	public StationsPanel(Operatore o) {
		super();
		
		this.op = o;
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		
		setLayout(new BorderLayout());
		button1 = new JRadioButton("Stazione 1");
		
		button2 = new JRadioButton("Stazione 2");
		button3 = new JRadioButton("Stazione 3");
		
		ButtonGroup group = new ButtonGroup();
		group.add(button1);
		group.add(button2);
		group.add(button3);
		
		mostraScelta = new JLabel("Seleziona una delle stazioni assegnate");
		JPanel areaTesto = new JPanel();
		areaTesto.setLayout(new BoxLayout(areaTesto, BoxLayout.Y_AXIS));
		areaTesto.add(mostraScelta);
		areaTesto.add(Box.createRigidArea(new Dimension(0, 40)));
	    JPanel areaDati = new JPanel();
	    areaDati.setLayout(new BoxLayout(areaDati, BoxLayout.Y_AXIS));
	    areaDati.add(button1);
	    areaDati.add(button2);
	    areaDati.add(button3);
	    add(areaTesto, BorderLayout.NORTH);
	    add(areaDati, BorderLayout.CENTER);
	    
	    
	    staz1 = new BottoneStatoStazione(0, op);
	    staz2 = new BottoneStatoStazione(1, op);
	    staz3 = new BottoneStatoStazione(2, op);
	    
	    
	    JPanel areaStati = new JPanel();
	    areaStati.setLayout(new BoxLayout(areaStati, BoxLayout.Y_AXIS));
	    
	    areaStati.add(staz1);
	    areaStati.add(staz2);
	    areaStati.add(staz3);
	    
	    checkStazioni();
	    
	    JPanel stazioniEstati = new JPanel();
	    stazioniEstati.setLayout(new BoxLayout(stazioniEstati, BoxLayout.Y_AXIS));
	    stazioniEstati.add(areaDati);
	    stazioniEstati.add(areaStati);
	    add(stazioniEstati, BorderLayout.CENTER);
	    
	    scelta = new JButton("Conferma Scelta");
		scelta.setActionCommand("avvioStazione");
		add(scelta, BorderLayout.SOUTH);
	    
	    
		button1.setActionCommand("stazione 1");
		button2.setActionCommand("stazione 2");
		button3.setActionCommand("stazione 3");
		
	    
	}
	
	public void checkStazioni() {
		 ObservableStazioneLavoroDAO s = new ObservableStazioneLavoroDAO();
		 ArrayList<ObservableStazioneLavoro> lista = s.selectStazioniByOperatore(this.op);
		 
		 switch(lista.size()) {
		 case 0:
			 	staz1.setVisible(false);
			    staz2.setVisible(false);
			    staz3.setVisible(false);
			 break;
		 case 1:
			    staz2.setVisible(false);
			    staz3.setVisible(false);
			 break;
		 case 2:
			    staz3.setVisible(false);
			 break;
		 }
	}
	
	public void getSelectedRadioButton() {
		 // Controlla quale radio button è selezionato
        if (button1.isSelected()) {
        	mostraScelta.setText("Scelta stazione 1");
        } else if (button2.isSelected()) {
        	mostraScelta.setText("Scelta stazione 2");
        } else if (button3.isSelected()) {
        	mostraScelta.setText("Scelta stazione 3");
        } else {
        	mostraScelta.setText("Nessuna opzione selezionata!");
        }
	}


	public JRadioButton getButton1() {
		return button1;
	}


	public void setButton1(JRadioButton button1) {
		this.button1 = button1;
	}


	public JRadioButton getButton2() {
		return button2;
	}


	public void setButton2(JRadioButton button2) {
		this.button2 = button2;
	}


	public JRadioButton getButton3() {
		return button3;
	}


	public void setButton3(JRadioButton button3) {
		this.button3 = button3;
	}


	public JButton getScelta() {
		return scelta;
	}


	public void setScelta(JButton scelta) {
		this.scelta = scelta;
	}
	
	
	
}
