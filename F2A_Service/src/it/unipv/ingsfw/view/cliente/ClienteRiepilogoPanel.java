package it.unipv.ingsfw.view.cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClienteRiepilogoPanel extends JPanel {
	JButton visualizzaRiepilogoButton;
	JButton pagaButton;
	
		public ClienteRiepilogoPanel() {
		super();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/6, screenHeight/6);
		setLocation(screenWidth/4, screenHeight/4);
		
		setLayout(new BorderLayout());

				
		visualizzaRiepilogoButton = new JButton("Riepilogo ordine");
		visualizzaRiepilogoButton.setActionCommand("Riepilogo");
 	    pagaButton = new JButton("Paga");
 		pagaButton.setActionCommand("Paga");
 		
 		JPanel areaDati = new JPanel(new GridLayout(2, 1));	
 	    areaDati.add(visualizzaRiepilogoButton);
 	    areaDati.add(pagaButton);

 	    add(areaDati, BorderLayout.CENTER);

 		

		}

		public JButton getVisualizzaRiepilogoButton() {
			return visualizzaRiepilogoButton;
		}

		public void setVisualizzaRiepilogoButton(JButton visualizzaRiepilogoButton) {
			this.visualizzaRiepilogoButton = visualizzaRiepilogoButton;
		}

		public JButton getPagaButton() {
			return pagaButton;
		}

		public void setPagaButton(JButton pagaButton) {
			this.pagaButton = pagaButton;
		}
		
		
}
