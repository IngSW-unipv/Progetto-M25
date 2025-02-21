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
    private ClienteFrameReg cfr; // Registrazione View
    private ClienteFrameRegLog cfrl; // Main View
    private ClienteFrameDepRit cfdr; // Scelta tra Deposito-Ritiro View


	    

	    public TotemAction(ClienteFrameRegLog cfrl) {
	        this.cfrl = cfrl;
	        addLogRegListener();
	    }
	    
	    public TotemAction(ClienteFrameDepRit cfdr) {
	        this.cfdr = cfdr;
	        addDepRitListener();
	    }
	    
	    public TotemAction(Cliente cl, ClienteFrameLog cfl) {
	    	this.cl = cl;
	    	this.cfl = cfl;
	    	addLoginListener();
	    }  
	    
	    /*public TotemAction(Cliente cl, ClienteFrameReg cfr) {
	    	this.cl = cl;
	    	this.cfr = cfr;
	    	addRegisterListener();
	    } */
	    
	    public TotemAction(Cliente cl, ClienteFrameReg cfr) {
	    	this.cl = cl;
	    	this.cfr = cfr;
	    	addRegisterListener();
	    }  
	    

	    private void addLogRegListener() {
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
						ClienteFrameReg cfr = new ClienteFrameReg(cl);
						cfr.setVisible(true);
						}
					}	    		
	    	});
	    }
	    
	    private void addDepRitListener() {
	    	cfdr.getBdrp().getDepButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {
					
					if (cfdr.getBdrp().getDepButton().getActionCommand().equalsIgnoreCase("Dep")) {	
						cfdr.dispose();
						JOptionPane.showMessageDialog(cfl, "Deposito non implementato", "Errore", JOptionPane.ERROR_MESSAGE);
						}
					}
	    	});
			    	
			cfdr.getBdrp().getRitButton().addActionListener(new ActionListener() {
			    @Override
					public void actionPerformed(ActionEvent e) {	
					
					if(cfdr.getBdrp().getRitButton().getActionCommand().equalsIgnoreCase("Rit")) {	
						cfdr.dispose();
						JOptionPane.showMessageDialog(cfl, "Ritiro non implementato", "Errore", JOptionPane.ERROR_MESSAGE);
						}
					}	    		
	    	});
	    }
	    
	    
	    private void addLoginListener() {
	        cfl.getPannello().getLoginButton().addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (cfl.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {
	                	
	                	Cliente cl1 = new Cliente(cfl.getPannello().getUserText().getText(), cfl.getPannello().getPassText().getText());
	                    boolean isValid = cl1.verificaCredenzialiAccesso(cfl.getPannello().getUserText().getText(),cfl.getPannello().getPassText().getText());
	                    if (isValid==true) {                    
	                    cl = (F2aFacade.getInstance().getGestioneNegozioFacade().selectClienteByEmailEPassword(cl1));
	                    JOptionPane.showMessageDialog(cfl, "Credenziali corrette"+"\n"+"Bentornato "+cl.getNome());
	                    cfl.dispose();
	                    ClienteFrameDepRit cfdr= new ClienteFrameDepRit();
	                    cfdr.setVisible(true);
	                    
	                    }
	                    else {
	                    JOptionPane.showMessageDialog(cfl, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            }
	        });
	    }
	
	    /*private void addRegisterListener() {
	        cfr.getPannello().getRegButton().addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (cfr.getPannello().getRegButton().getActionCommand().equalsIgnoreCase("Registrazione")) {
	                	String idcl = (F2aFacade.getInstance().getGestioneNegozioFacade().getNewIdCliente());
	                	Cliente cl1 = new Cliente(idcl,cfr.getPannello().getUserTextNome().getText(), cfr.getPannello().getUserTextCognome().getText(),
	                			cfr.getPannello().getUserTextCf().getText(), cfr.getPannello().getUserTextEmail().getText(), cfr.getPannello().getUserTextPw().getText());
	                	boolean isValid = F2aFacade.getInstance().getGestioneNegozioFacade().insertCliente(cl1);
	                	if (isValid==true) {
		                    JOptionPane.showMessageDialog(cfr, "Registrazione effettuata" + "\n" +"Il suo codice cliente è: "+idcl);	
		                    cfr.dispose();
							ClienteFrameLog cfl = new ClienteFrameLog(cl);
							cfl.setVisible(true);
		                    }
		                    else {
		                    JOptionPane.showMessageDialog(cfr, "registrazione non avvenuta", "Errore", JOptionPane.ERROR_MESSAGE);
		                    }
	                }	
                
	            }
	        });
	    }*/
	    private void addRegisterListener() {
	        cfr.getPannello().getRegButton().addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (cfr.getPannello().getRegButton().getActionCommand().equalsIgnoreCase("Registrazione")) {
	                	String idcl = (F2aFacade.getInstance().getGestioneNegozioFacade().getNewIdCliente());
	                	Cliente cl1 = new Cliente(idcl,cfr.getPannello().getUserTextNome().getText(), cfr.getPannello().getUserTextCognome().getText(),
	                			cfr.getPannello().getUserTextCf().getText(), cfr.getPannello().getUserTextEmail().getText(), cfr.getPannello().getUserTextPw().getText());
	                	boolean isValid = F2aFacade.getInstance().getGestioneNegozioFacade().insertCliente(cl1);
	                	if (isValid==true) {
		                    JOptionPane.showMessageDialog(cfr, "Registrazione effettuata" + "\n" +"Il suo codice cliente è: "+idcl);	
		                    cfr.dispose();
							ClienteFrameLog cfl = new ClienteFrameLog(cl);
							cfl.setVisible(true);
		                    }
		                    else {
		                    JOptionPane.showMessageDialog(cfr, "registrazione non avvenuta", "Errore", JOptionPane.ERROR_MESSAGE);
		                    }
	                }	
                
	            }
	        });
	    }	 
	    
}
		


