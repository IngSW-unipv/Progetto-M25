package it.unipv.ingsfw.view.corriere;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.users.Operatore;

public class BottoneStatoStazione extends JButton{

	private JButton statoButton;
	private ObservableStazioneLavoro stazione; // Istanza di Stazione
	JComboBox<StatoStazione> statoComboBox;

	public BottoneStatoStazione(int index, Operatore o) {

		ObservableStazioneLavoroDAO s = new ObservableStazioneLavoroDAO();
		
		try {
			this.stazione = s.selectStazioniByOperatore(o).get(index); // Associa l'oggetto Stazione alla GUI

			statoButton = new JButton("Stato: " + stazione.getStatoStazione().toString());
			statoButton.setPreferredSize(new Dimension(50, 50));
			aggiornaColoreBottone();

			// ComboBox per cambiare lo stato manualmente
			statoComboBox = new JComboBox<>(StatoStazione.values());
			
		}catch(IndexOutOfBoundsException e){
			System.err.println("Errore");
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		/*
		statoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StatoStazione nuovoStato = (StatoStazione) statoComboBox.getSelectedItem();
				stazione.setStatoStazione(nuovoStato); // Modifica lo stato dell'istanza
				aggiornaColoreBottone();
			}
		});*/
	}

	public JButton getStatoButton() {
		return statoButton;
	}

	public void setStatoButton(JButton statoButton) {
		this.statoButton = statoButton;
	}

	public ObservableStazioneLavoro getStazione() {
		return stazione;
	}

	public void setStazione(ObservableStazioneLavoro stazione) {
		this.stazione = stazione;
	}

	public JComboBox<StatoStazione> getStatoComboBox() {
		return statoComboBox;
	}

	public void setStatoComboBox(JComboBox<StatoStazione> statoComboBox) {
		this.statoComboBox = statoComboBox;
	}



	// Metodo per aggiornare il colore del bottone
	private void aggiornaColoreBottone() {
		StatoStazione stato = stazione.getStatoStazione();
		switch (stato) {
		case READY:
			statoButton.setBackground(Color.GREEN);
			break;
		case MAINTENANCE:
			statoButton.setBackground(Color.ORANGE);
			break;
		case WORKING:
			statoButton.setBackground(Color.RED);
			break;
		}
		statoButton.setText("Stato: " + stato);
	}
	
	

}
