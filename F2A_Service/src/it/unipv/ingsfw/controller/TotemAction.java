package it.unipv.ingsfw.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.ingsfw.view.cliente.*;
public class TotemAction {
		
	   	//private Cliente cl; // Model
	   	//private ButtonRegLogPanel brlp;
	    //private ClienteFrameLog cfl; // Login View
	    private ClienteFrameRegLog cfrl; // Main View

	    public TotemAction(ClienteFrameRegLog cfrl) {
	        this.cfrl = cfrl;
	        addLogListener();
	    }

	    private void addLogListener() {
	    	cfrl.getBrlp().getLogButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {
					
					if (cfrl.getBrlp().getLogButton().getActionCommand().equalsIgnoreCase("Log")) {	
						cfrl.dispose();
						ClienteFrameLog finestraLogin = new ClienteFrameLog();
						finestraLogin.setVisible(true);
					}}});
			    	
			 cfrl.getBrlp().getRegButton().addActionListener(new ActionListener() {
			     @Override
					 public void actionPerformed(ActionEvent e) {	
					
					if(cfrl.getBrlp().getRegButton().getActionCommand().equalsIgnoreCase("Reg")) {	
						cfrl.dispose();
						ClienteFrameReg finestraRegistrazione = new ClienteFrameReg();
						finestraRegistrazione.setVisible(true);
					}
				}	    		
	    	});
	    }
	

	    
}
		
	



