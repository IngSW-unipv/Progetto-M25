package it.unipv.ingsfw.controller;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.view.cliente.*;

//parti commentate sono le originali

public class TotemAction {
		
    private Integer costoInt;
	private Cliente cl; // Model
    private Totem t;
    private ClienteFrameLog cfl; // Login View
    private ClienteFrameReg cfr; // Registrazione View
    private ClienteFrameRegLog cfrl; // Main View
    private ClienteFrameDepRit cfdr; // Scelta tra Deposito-Ritiro View
    private ClienteFrameDeposito cfd; //Scelta servizio lavorazione (deposito capo)
    private ClienteFrameRitiro cfrit; // Ritiro capo
    private String sol="";
    private Negozio sonr;
    private Negozio sond;
    private Date date;
    private String idc;
	

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
	    
	    public TotemAction(Totem t, ClienteFrameRitiro cfrit) {
	    	this.t = t;
	    	this.cfrit = cfrit;
	    	addRitiroListener();
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
						ClienteFrameRitiro cfrit =new ClienteFrameRitiro(t);
						cfrit.setVisible(true);
						//JOptionPane.showMessageDialog(cfl, "Ritiro non implementato", "Errore", JOptionPane.ERROR_MESSAGE);
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
	                    JOptionPane.showMessageDialog(cfl, "Credenziali corrette"+"\n"+"Bentornato "+cl.getNome()+"\n codice cliente"+cl.getIdCliente());
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
	    	cfd.getPannello().getScegliNegDepButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {
					
					if (cfd.getPannello().getScegliNegDepButton().getActionCommand().equalsIgnoreCase("NegozioDep")) {	
				        ArrayList<Negozio> n1 =t.getNegoziAttivi();
				        Negozio[] optionsArray= n1.toArray(new Negozio[0]);

				        // Creazione del menu a tendina con JOptionPane
				        Negozio selectedOption = (Negozio) JOptionPane.showInputDialog(
				            null,                  // Finestra principale (null usa la finestra di default)
				            "Conferma Negozio:",  // Messaggio che appare sopra il menu a tendina
				            "Negozi Disponibili",      // Titolo della finestra
				            JOptionPane.PLAIN_MESSAGE,  // Tipo di finestra di dialogo (senza icona)
				            null,                  // Icona (null per nessuna icona)
				            optionsArray,               // I valori delle opzioni nel menu a tendina
				            optionsArray[0]             // Il valore predefinito (la prima opzione)
				        );
				        sond=selectedOption;
						}
					}
	    	});
	    	cfd.getPannello().getScegliNegRitButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {
					
					if (cfd.getPannello().getScegliNegRitButton().getActionCommand().equalsIgnoreCase("NegozioRit")) {	
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
				        sonr=selectedOption;
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
				        //System.out.println(selectedOption);
				        sol=selectedOption;
						}
					
					
					}
	    	});
	    	
	    	
	    	cfd.getPannello().getVerificaDatiButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfd.getPannello().getVerificaDatiButton().getActionCommand().equalsIgnoreCase("Verifica")) {	
						//cfd.dispose();						
						//ClienteFrameRiepilogo cfr = new ClienteFrameRiepilogo(t);
						//cfr.setVisible(true);
						String idCliente = cfd.getPannello().getIdText().getText().trim();
						String dataUltimaRitiro =cfd.getPannello().getDataText().getText().trim();
						// Controlli sui campi
						if (idCliente.isEmpty()) {
							JOptionPane.showMessageDialog(cfd, "Il campo 'ID Cliente' è obbligatorio.", "Errore", JOptionPane.ERROR_MESSAGE);
							return; // Interrompe l'esecuzione se il campo non è valido
						}
        
			            // Controllo formato della data (aaaa-mm-gg)
			            String regexData = "^\\d{4}-\\d{2}-\\d{2}$";
			            if (!dataUltimaRitiro.matches(regexData)) {
			                JOptionPane.showMessageDialog(cfd, "La data deve essere nel formato aaaa-mm-gg.", "Errore", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
			            
			            // Verifica che la data sia valida
			            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			            sdf.setLenient(false); // Imposta la modalità rigorosa per evitare date invalide (es. 2025-02-31)
			            try {
			                date = sdf.parse(dataUltimaRitiro);
			            } catch (ParseException ex) {
			                JOptionPane.showMessageDialog(cfd, "La data inserita non è valida.", "Errore", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
			            if (sond == null) { // Aggiungi il controllo per sond
			                JOptionPane.showMessageDialog(cfd, "Devi selezionare un negozio di deposito.", "Errore", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
			            if (sonr == null) {
			                JOptionPane.showMessageDialog(cfd, "Devi selezionare un negozio di ritiro.", "Errore", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
			            
			            if (sol == null || sol.isEmpty()) {
			                JOptionPane.showMessageDialog(cfd, "Devi selezionare un tipo di lavaggio.", "Errore", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
						String indirNegoRit=sonr.getIndirizzo();
						
						
						costoInt=F2aFacade.getInstance().getLavaggioFacade().getCostoLavaggio(sol);
						//implementare logica per generare prezzo scontato STRATEGY
						String costoS =Integer.toString(costoInt);
						
					
						JOptionPane.showMessageDialog(cfd, "Riepilogo dati: "+"\n"+ " Codice cliente: "+idCliente +"\n Negozio ritiro: "+indirNegoRit+"\n"
								+"\n Data ultima ritiro: "+dataUltimaRitiro+"\n"+" Tipologia servizio: "+sol+"\n COSTO SERVIZIO: "+costoS+"\n");
						
						}
					}
	    	});
	    	
	    	cfd.getPannello().getPagaButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfd.getPannello().getPagaButton().getActionCommand().equalsIgnoreCase("Paga")) {
						idc = (F2aFacade.getInstance().getCapoFacade().getNewIdCapo());
						TipoLavaggio sol1=TipoLavaggio.valueOf(sol);				
						StatoCapo s=StatoCapo.IN_STORE;
						t.setCliente(new Cliente(cfd.getPannello().getIdText().getText()));
						double costoD = (double) costoInt;
						
						boolean result;
						try {
							result=t.generaPrenotazioneCapo(new Capo(idc,s,sol1,null, date ,sonr,sond,costoD,t.getCliente()));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(cfd,"\n Codice capo: "+idc+"\n");
						
					}
	    		}
	    	});
	    	cfd.getPannello().getEsciButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfd.getPannello().getEsciButton().getActionCommand().equalsIgnoreCase("Logout")) {
						cfd.dispose();
						ClienteFrameRegLog cfrl = new ClienteFrameRegLog();
						cfrl.setVisible(true);
					}
	    		}
	    	});
	    	cfd.getPannello().getRitiraButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfd.getPannello().getRitiraButton().getActionCommand().equalsIgnoreCase("Ritira")) {
						cfd.dispose();
						ClienteFrameRitiro cfrit = new ClienteFrameRitiro(t);
						cfrit.setVisible(true);
					}
	    		}
	    	});
	    	
	    }
	     
	    
	    private void addRitiroListener() {
	    	cfrit.getPannello().getRitButton().addActionListener(new ActionListener() {
	    		@Override
				public void actionPerformed(ActionEvent e) {
	    		if (cfrit.getPannello().getRitButton().getActionCommand().equalsIgnoreCase("Ritira")) {
	    			StatoCapo stato=StatoCapo.PRELEVATO;
	    			String idCapo=cfrit.getPannello().getIdcText().getText();
	    			Boolean esito=F2aFacade.getInstance().getCapoFacade().updateStatoCapo(new Capo(idCapo, stato));
	    			JOptionPane.showMessageDialog(cfrit, "Può ritirare il capo");	    			
	    			}

	    		}
	    	});
	    	cfrit.getPannello().getEsciButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfrit.getPannello().getEsciButton().getActionCommand().equalsIgnoreCase("Logout")) {
						cfrit.dispose();
						ClienteFrameRegLog cfrl = new ClienteFrameRegLog();
						cfrl.setVisible(true);
					}
	    		}
	    	});
	    	cfrit.getPannello().getDepositaButton().addActionListener(new ActionListener() {
	    		@Override
					public void actionPerformed(ActionEvent e) {					
					if (cfrit.getPannello().getDepositaButton().getActionCommand().equalsIgnoreCase("Deposita")) {
						cfrit.dispose();
						ClienteFrameDeposito cfd = new ClienteFrameDeposito(t);
						cfd.setVisible(true);
					}
	    		}
	    	});	    
	    }	  	  
}
		


