package it.unipv.ingsfw.view.cliente;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonDepRitPanel extends JPanel{
	JButton depButton;
	JButton ritButton;
	
	public ButtonDepRitPanel() {
		
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		
		depButton = new JButton("Deposita Capo");
	    depButton.setActionCommand("Dep");	
		
		ritButton = new JButton("Ritira Capo");
	    ritButton.setActionCommand("Rit");
		
		add(ritButton);
		add(depButton);	
	
	}

	public JButton getDepButton() {
		return depButton;
	}


	public void setDepButton(JButton depButton) {
		this.depButton = depButton;
	}
	
	public JButton getRitButton() {
		return ritButton;
	}


	public void setRitButton(JButton ritButton) {
		this.ritButton = ritButton;
	}
	
	
}



