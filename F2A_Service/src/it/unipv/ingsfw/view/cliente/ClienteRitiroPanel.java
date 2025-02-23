package it.unipv.ingsfw.view.cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClienteRitiroPanel extends JPanel {
	
		JLabel idcLabel;
		JTextField idcText;
		JButton rititiroButton;
		JButton esciButton;
		JButton depositaButton;
		
		public ClienteRitiroPanel() {
	 		super();
	 		
	 		Toolkit kit = Toolkit.getDefaultToolkit();
	 		Dimension screenSize = kit.getScreenSize();
	 		int screenHeight = screenSize.height;
	 		int screenWidth = screenSize.width;
	 		setSize(screenWidth/6, screenHeight/6);
	 		setLocation(screenWidth/4, screenHeight/4);
	 		
	 		setLayout(new BorderLayout());
			idcLabel = new JLabel("inserire codice capo");
	 	    idcText = new JTextField(25);
	 		rititiroButton = new JButton("RitiraCapo");
	 		rititiroButton.setActionCommand("Ritira");
	 		
	 		JPanel areaDati = new JPanel(new GridLayout(5, 1));
	 	    areaDati.add(idcLabel);
	 	    areaDati.add(idcText);
	 	    areaDati.add(rititiroButton);
	 	    add(areaDati, BorderLayout.CENTER);
	 	    
	 		depositaButton = new JButton("Deposita capi");
	 		depositaButton.setActionCommand("Deposita");
	 		esciButton = new JButton("Effettua logout");
	 		esciButton.setActionCommand("Logout");
	 		JPanel verificaPagaArea = new JPanel(new GridLayout(2, 1));
	 		verificaPagaArea.add(depositaButton);
	 		verificaPagaArea.add(esciButton);
	 		add(verificaPagaArea, BorderLayout.SOUTH);

		}

		public JButton getEsciButton() {
			return esciButton;
		}

		public void setEsciButton(JButton esciButton) {
			this.esciButton = esciButton;
		}

		public JButton getDepositaButton() {
			return depositaButton;
		}

		public void setDepositaButton(JButton depositaButton) {
			this.depositaButton = depositaButton;
		}

		public JTextField getIdcText() {
			return idcText;
		}

		public void setIdcText(JTextField idcText) {
			this.idcText = idcText;
		}

		public JButton getRitButton() {
			return rititiroButton;
		}

		public void setRitButton(JButton ritButton) {
			this.rititiroButton = ritButton;
		}
		
		
		
}
