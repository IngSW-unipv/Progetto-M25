package it.unipv.ingsfw.view.clientepanel;
import java.awt.*;
import javax.swing.*;

public class ClienteDepositoPanel extends JPanel {    
    	JLabel dataLabel;
 		JTextField dataText;
    	JLabel idLabel;
 		JTextField idText;
 		JLabel spaceLabel1;
 		JButton scegliNegDepButton;
 		JButton verificaDatiButton;
 		JButton scegliNegRitButton;
 		JButton scegliLavButton;
 		JButton pagaButton;
 		JButton esciButton;
 		JButton ritiraButton;
 		
 		public ClienteDepositoPanel() {
 		super();
 		
 		Toolkit kit = Toolkit.getDefaultToolkit();
 		Dimension screenSize = kit.getScreenSize();
 		int screenHeight = screenSize.height;
 		int screenWidth = screenSize.width;
 		setSize(screenWidth/6, screenHeight/6);
 		setLocation(screenWidth/4, screenHeight/4);
 		
 		setLayout(new BorderLayout());
		idLabel = new JLabel("inserire codice cliente per sconto *");
 	    idText = new JTextField(25);
 		spaceLabel1 = new JLabel("    ");
 		dataLabel = new JLabel("data ultima per ritiro capo: (aaaa-mm-gg) *");
 	    dataText = new JTextField(25);
 		spaceLabel1 = new JLabel("    ");
 		scegliNegDepButton = new JButton("Conferma negozio di deposito *");
 		scegliNegDepButton.setActionCommand("NegozioDep");
 		scegliNegRitButton = new JButton("Scegli il negozio per il ritiro *");
 		scegliNegRitButton.setActionCommand("NegozioRit");
 		spaceLabel1 = new JLabel("    ");
 		scegliLavButton = new JButton("Scegli il tipo di lavaggio *");
 		scegliLavButton.setActionCommand("Lavaggio");
 		esciButton = new JButton("Effettua logout");
 		esciButton.setActionCommand("Logout");
 		ritiraButton = new JButton("Ritira capi");
 		ritiraButton.setActionCommand("Ritira");
 		spaceLabel1 = new JLabel("    ");
 		spaceLabel1 = new JLabel("    ");
 		
 		JPanel areaDati = new JPanel(new GridLayout(5, 1));
 	    areaDati.add(idLabel);
 	    areaDati.add(idText);
 	    areaDati.add(dataLabel);
 	    areaDati.add(dataText);
 	    areaDati.add(scegliNegDepButton);
 	    areaDati.add(scegliNegRitButton);
 	    areaDati.add(scegliLavButton);
 	    add(areaDati, BorderLayout.CENTER);
 	    
 	    
 	    verificaDatiButton = new JButton("Verifica i dati di prenotazione ");
 		verificaDatiButton.setActionCommand("Verifica");
	    pagaButton = new JButton("Paga e depostia");
 		pagaButton.setActionCommand("Paga");
 		JPanel verificaPagaArea = new JPanel(new GridLayout(2, 1));
 		verificaPagaArea.add(verificaDatiButton);
 		verificaPagaArea.add(pagaButton);
 		verificaPagaArea.add(ritiraButton);
 		verificaPagaArea.add(esciButton);
 		add(verificaPagaArea, BorderLayout.SOUTH);


 	}


		public JButton getEsciButton() {
			return esciButton;
		}


		public void setEsciButton(JButton esciButton) {
			this.esciButton = esciButton;
		}


		public JButton getRitiraButton() {
			return ritiraButton;
		}


		public void setRitiraButton(JButton ritiraButton) {
			this.ritiraButton = ritiraButton;
		}


		public JButton getScegliNegDepButton() {
			return scegliNegDepButton;
		}


		public void setScegliNegDepButton(JButton scegliNegDepButton) {
			this.scegliNegDepButton = scegliNegDepButton;
		}


		public JButton getScegliNegRitButton() {
			return scegliNegRitButton;
		}


		public void setScegliNegRitButton(JButton scegliNegRitButton) {
			this.scegliNegRitButton = scegliNegRitButton;
		}


		public JLabel getIdLabel() {
			return idLabel;
		}


		public void setIdLabel(JLabel idLabel) {
			this.idLabel = idLabel;
		}


		public JTextField getIdText() {
			return idText;
		}


		public void setIdText(JTextField idText) {
			this.idText = idText;
		}


		public JButton getScegliLavButton() {
			return scegliLavButton;
		}


		public void setScegliLavButton(JButton scegliLavButton) {
			this.scegliLavButton = scegliLavButton;
		}


		public JTextField getDataText() {
			return dataText;
		}


		public void setDataText(JTextField dataText) {
			this.dataText = dataText;
		}

		

		public JButton getScegliNegButton() {
			return scegliNegRitButton;
		}


		public void setScegliNegButton(JButton scegliNegButton) {
			this.scegliNegRitButton = scegliNegButton;
		}


		public JButton getVerificaDatiButton() {
			return verificaDatiButton;
		}


		public void setVerificaDatiButton(JButton verificaDatiButton) {
			this.verificaDatiButton = verificaDatiButton;
		}


		public JButton getPagaButton() {
			return pagaButton;
		}


		public void setPagaButton(JButton pagaButton) {
			this.pagaButton = pagaButton;
		}
 	
 	
}