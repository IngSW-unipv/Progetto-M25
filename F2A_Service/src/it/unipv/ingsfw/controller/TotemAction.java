package it.unipv.ingsfw.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.model.users.TipoOperatore;
import it.unipv.ingsfw.view.cliente.*;
import it.unipv.ingsfw.view.operatore.MainFrameOperatore;
public class TotemAction {
		
    private Cliente cl; // Model
    private ClienteFrameLog cfl; // Login View
    private ClienteFrameRegLog cfrl; // Main View
    int i = 0;


	    

	    public TotemAction(ClienteFrameRegLog cfrl) {
	        this.cfrl = cfrl;
	        addLogListener();
	    }
	    
	    public TotemAction(Cliente cl, ClienteFrameLog cfl) {
	    	this.cl = cl;
	    	this.cfl = cfl;
	    	addLoginListener();
	    }  
	    

	    private void addLogListener() {
	    	cfrl.getBrlp().getLogButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {
					
					if (cfrl.getBrlp().getLogButton().getActionCommand().equalsIgnoreCase("Log")) {	
						cfrl.dispose();
						ClienteFrameLog cfl = new ClienteFrameLog(cl);
						cfl.setVisible(true);
						}
					}
	    	});
			    	
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
	    
	    private void addLoginListener() {
	        cfl.getPannello().getLoginButton().addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (cfl.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {
	                	
	                    boolean isValid = cl.verificaCredenzialiAccesso(cfl.getPannello().getUserText().getText(),cfl.getPannello().getPassText().getText());
	                    Cliente cl1 = new Cliente(cfl.getPannello().getUserText().getText(), cfl.getPannello().getPassText().getText());
	                    if (isValid==false) {
	                    cl = (F2aFacade.getInstance().getGestioneNegozioFacade().selectClienteByEmailEPassword(cl1));
	                    JOptionPane.showMessageDialog(cfl, "Credenziali corrette");
	                    
	                    }
	                    else {
	                    JOptionPane.showMessageDialog(cfl, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            }
	        });
	    }
	
}
		


