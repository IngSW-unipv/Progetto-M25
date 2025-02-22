package it.unipv.ingsfw.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.view.cliente.*;

//parti commentate sono le originali

public class TotemAction {
		
    private Cliente cl; // Model
    private Totem t;
    private ClienteFrameLog cfl; // Login View
    private ClienteFrameReg cfr; // Registrazione View
    private ClienteFrameRegLog cfrl; // Main View
    private ClienteFrameDepRit cfdr; // Scelta tra Deposito-Ritiro View
    private ClienteFrameDeposito cfd; //Scelta servizio
    private ClienteFrameRiepilogo cfrie; // Riepilogo e pagamento
    private String so;
	

	    public TotemAction(ClienteFrameRegLog cfrl) {
	        this.cfrl = cfrl;
	        t=new Totem();
	        addLogRegListener();
	    }
	    
	    public TotemAction(ClienteFrameDepRit cfdr) {
	        this.cfdr = cfdr;
	        t=new Totem();
	        addDepRitListener();
	    } 
	    
	    public TotemAction(Totem t, ClienteFrameDeposito cfd) {
	    	this.t = t;
	    	this.cfd = cfd;
	    	addDepositoListener();
	    } 
	    
	    public TotemAction(Totem t, ClienteFrameLog cfl) {
	    	this.t = t;
	    	this.cfl = cfl;
	    	addLoginListener();
	    }  
	    
	    public TotemAction(Totem t, ClienteFrameReg cfr) {
	    	this.t = t;
	    	this.cfr = cfr;
	    	addRegisterListener();
	    }  
	    
	    public TotemAction(Totem t, ClienteFrameRiepilogo cfrie) {
	    	this.t = t;
	    	this.cfrie = cfrie;
	    	addRiepilogoListener();
	    }  

	    private void addLogRegListener() {
	    	cfrl.getBrlp().getLogButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfrl.getBrlp().getLogButton().getActionCommand().equalsIgnoreCase("Log")) {	
						cfrl.dispose();
						ClienteFrameLog cfl = new ClienteFrameLog(t);
						cfl.setVisible(true);
						}
					}
	    	});
			    	
			cfrl.getBrlp().getRegButton().addActionListener(new ActionListener() {
			    @Override
					public void actionPerformed(ActionEvent e) {						
					if(cfrl.getBrlp().getRegButton().getActionCommand().equalsIgnoreCase("Reg")) {	
						cfrl.dispose();
						//ClienteFrameReg cfr = new ClienteFrameReg(cl);
						//Totem t=new Totem();
						ClienteFrameReg cfr = new ClienteFrameReg(t);
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
						ClienteFrameDeposito cfd = new ClienteFrameDeposito(t);
						cfd.setVisible(true);
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
	                	t.setCliente(new Cliente(cfl.getPannello().getUserText().getText(), cfl.getPannello().getPassText().getText()));
	                	boolean isValid = t.verificaCredenzialiAccesso();	                    
	                    if (isValid==true) { 
	                    cl = (F2aFacade.getInstance().getGestioneNegozioFacade().selectClienteByEmailEPassword(t.getCliente()));
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

	    private void addRegisterListener() {
	        cfr.getPannello().getRegButton().addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (cfr.getPannello().getRegButton().getActionCommand().equalsIgnoreCase("Registrazione")) {
	                	String idcl = (F2aFacade.getInstance().getGestioneNegozioFacade().getNewIdCliente());
	                	t.setCliente(new Cliente(idcl,cfr.getPannello().getUserTextNome().getText(), cfr.getPannello().getUserTextCognome().getText(),
	                			cfr.getPannello().getUserTextCf().getText(), cfr.getPannello().getUserTextEmail().getText(), cfr.getPannello().getUserTextPw().getText()));
	                	boolean isValid = F2aFacade.getInstance().getGestioneNegozioFacade().insertCliente(t.getCliente());
	                	if (isValid==true) {
		                    JOptionPane.showMessageDialog(cfr, "Registrazione effettuata" + "\n" +"Il suo codice cliente è: "+idcl);	
		                    cfr.dispose();
							ClienteFrameLog cfl = new ClienteFrameLog(t);
							cfl.setVisible(true);
		                    }
		                    else {
		                    JOptionPane.showMessageDialog(cfr, "registrazione non avvenuta", "Errore", JOptionPane.ERROR_MESSAGE);
		                    }
	                }	
                
	            }
	        });
	    }	
	    
	    private void addDepositoListener() {
	    	cfd.getPannello().getScegliNegButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {
					
					if (cfd.getPannello().getScegliNegButton().getActionCommand().equalsIgnoreCase("Negozio")) {	
				        ArrayList<Negozio> n =t.getNegoziAttivi();
				        Negozio[] optionsArray= n.toArray(new Negozio[0]);

				        // Creazione del menu a tendina con JOptionPane
				        Negozio selectedOption = (Negozio) JOptionPane.showInputDialog(
				            null,                  // Finestra principale (null usa la finestra di default)
				            "Scegli un negozio:",  // Messaggio che appare sopra il menu a tendina
				            "Negozi Disponibili",      // Titolo della finestra
				            JOptionPane.PLAIN_MESSAGE,  // Tipo di finestra di dialogo (senza icona)
				            null,                  // Icona (null per nessuna icona)
				            optionsArray,               // I valori delle opzioni nel menu a tendina
				            optionsArray[0]             // Il valore predefinito (la prima opzione)
				        );
				        //System.out.println(selectedOption);
						}
					}
	    	});
	    	
	    	cfd.getPannello().getScegliLavButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {
					
					if (cfd.getPannello().getScegliLavButton().getActionCommand().equalsIgnoreCase("Lavaggio")) {	
						HashMap<Integer, String> lav =t.getTipologiaLavaggi();
						String[] opzioniLavaggio = lav.values().toArray(new String[0]);

				        // Creazione del menu a tendina con JOptionPane
				        String selectedOption = (String) JOptionPane.showInputDialog(
				            null,                  // Finestra principale (null usa la finestra di default)
				            "Scegli un negozio:",  // Messaggio che appare sopra il menu a tendina
				            "Negozi Disponibili",      // Titolo della finestra
				            JOptionPane.PLAIN_MESSAGE,  // Tipo di finestra di dialogo (senza icona)
				            null,                  // Icona (null per nessuna icona)
				            opzioniLavaggio,               // I valori delle opzioni nel menu a tendina
				            opzioniLavaggio[0]             // Il valore predefinito (la prima opzione)
				        );
				        System.out.println(selectedOption);
						}
					
					
					}
	    	});
	    	
	    	cfd.getPannello().getVerificaDatiButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfd.getPannello().getVerificaDatiButton().getActionCommand().equalsIgnoreCase("Verifica")) {	
						cfd.dispose();
						//JOptionPane.showMessageDialog(cfl, "Riepilogo non implementato", "Errore", JOptionPane.ERROR_MESSAGE);
						ClienteFrameRiepilogo cfr = new ClienteFrameRiepilogo(t);
						cfr.setVisible(true);
						}
					}
	    	});	    	
	    }

	    
	    private void addRiepilogoListener() {
	    
	    	cfrie.getPannello().getVisualizzaRiepilogoButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfrie.getPannello().getVisualizzaRiepilogoButton().getActionCommand().equalsIgnoreCase("Riepilogo")) {	
						cfrie.dispose();
						String idc = (F2aFacade.getInstance().getCapoFacade().getNewIdCapo());
						//int costo = (F2aFacade.getInstance().getLavaggioFacade().getCostoLavaggio(/*tipo lavaggio*/)));
						System.out.println(idc);
						//System.out.println(so);
						
						
						
						//JOptionPane.showMessageDialog("Codice prenotazione: ",idc , "\n Tipo lavaggio: ",, "Errore", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showMessageDialog(cfl, "in implementazione", "Errore", JOptionPane.ERROR_MESSAGE);

						}
					}
	    	});
			/*    	
			cfrl.getBrlp().getRegButton().addActionListener(new ActionListener() {
			    @Override
					public void actionPerformed(ActionEvent e) {						
					if(cfrl.getBrlp().getRegButton().getActionCommand().equalsIgnoreCase("Reg")) {	
						cfrl.dispose();
						//ClienteFrameReg cfr = new ClienteFrameReg(cl);
						//Totem t=new Totem();
						ClienteFrameReg cfr = new ClienteFrameReg(t);
						cfr.setVisible(true);
						}
					}	    		
	    	});*/
	    }
	    
}
		


