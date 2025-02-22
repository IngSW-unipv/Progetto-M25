package it.unipv.ingsfw.view.cliente;
import java.awt.*;
import javax.swing.*;

public class ClienteDepositoPanel extends JPanel {    
    	JLabel dataLabel;
 		JTextField dataText;
 		JLabel spaceLabel1;
 		JButton verificaDatiButton;
 		JButton scegliNegButton;
 		JButton scegliLavButton;
 		
 		public ClienteDepositoPanel() {
 		super();
 		
 		Toolkit kit = Toolkit.getDefaultToolkit();
 		Dimension screenSize = kit.getScreenSize();
 		int screenHeight = screenSize.height;
 		int screenWidth = screenSize.width;
 		setSize(screenWidth/6, screenHeight/6);
 		setLocation(screenWidth/4, screenHeight/4);
 		
 		setLayout(new BorderLayout());
 		dataLabel = new JLabel("inserisce la data entro cui vuoi ritirare il capo: (aaaa-mm-gg)");
 	    dataText = new JTextField(25);
 		spaceLabel1 = new JLabel("    ");
 		scegliNegButton = new JButton("Scegli il negozio per il ritiro");
 		scegliNegButton.setActionCommand("Negozio");
 		spaceLabel1 = new JLabel("    ");
 		scegliLavButton = new JButton("Scegli il tipo di lavaggio");
 		scegliLavButton.setActionCommand("Lavaggio");
 		spaceLabel1 = new JLabel("    ");
 		spaceLabel1 = new JLabel("    ");
 		
 		JPanel areaDati = new JPanel(new GridLayout(6, 1));
 	    areaDati.add(dataLabel);
 	    areaDati.add(dataText);
 	    areaDati.add(scegliNegButton);
 	    areaDati.add(scegliLavButton);
 	    add(areaDati, BorderLayout.CENTER);
 	    
 	    
 	    verificaDatiButton = new JButton("Verifica il riepilogo e paga ");
 		verificaDatiButton.setActionCommand("Verifica");
 		add(verificaDatiButton, BorderLayout.SOUTH);
 	    


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
			return scegliNegButton;
		}


		public void setScegliNegButton(JButton scegliNegButton) {
			this.scegliNegButton = scegliNegButton;
		}


		public JButton getVerificaDatiButton() {
			return verificaDatiButton;
		}


		public void setVerificaDatiButton(JButton verificaDatiButton) {
			this.verificaDatiButton = verificaDatiButton;
		}
 	
 	
}