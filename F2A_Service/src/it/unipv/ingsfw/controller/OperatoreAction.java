package it.unipv.ingsfw.controller;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.view.operatore.MainFrameOperatore;
import it.unipv.ingsfw.view.operatore.MenuListenerAdapter;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class OperatoreAction implements Observer {

    private Operatore op; // Model
    private GUILoginOperatore g; // Login View
    private MainFrameOperatore m; // Main View
    int i = 0;

    public OperatoreAction(Operatore op, GUILoginOperatore g) {
        this.op = op;
        this.g = g;
        addLoginListener();
    }

    public OperatoreAction(Operatore op, MainFrameOperatore m) {
        this.op = op;
        this.m = m;
        op.setStazioniAssociate();      
        m.aggiornaStazioni();
        addMainListeners();
    }

    private void addLoginListener() {
        g.getPannello().getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (g.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {
                    boolean isValid = op.verificaCredenzialiAccesso(
                        g.getPannello().getUserText().getText(),
                        g.getPannello().getPassText().getText());

                    if (isValid) {
                        JOptionPane.showMessageDialog(g, "Benvenuto!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
                        g.dispose();
                        DipendenteDAO d = new DipendenteDAO();
                        op = new Operatore(d.selectIdByEmailPassword(new Operatore(g.getPannello().getUserText().getText(), g.getPannello().getPassText().getText())));
                        MainFrameOperatore loginOp = new MainFrameOperatore(op);
                        loginOp.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(g, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void addMainListeners() {
        DipendenteDAO d = new DipendenteDAO();

        m.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() -> m.aggiornaStazioni()));
        m.getMenu().getGestioneStazioni().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Stazioni")));
        m.getMenu().getControlloMacchinari().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Macchinari")));
        m.getMenu().getModificaProfilo().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Profilo")));

        for (; i < m.getBottoni().size(); i++) {
        	
        	if (op.getStazioniAssociate().get(i) == null || op.getStazioniAssociate().isEmpty()) {
        	    System.out.println("Errore: Nessuna stazione associata trovata.");
        	} else {
        	    op.getStazioniAssociate().get(i).addObserver(this);
        	    System.out.println("Aggiunto observer alla stazione " + i);
        	}
        	
            m.getBottoni().get(i).addActionListener(new ActionListener() {
                int j = i;
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("j: " + j);
                    i=j;
                    op.setTipoOperatore(d.selectTipoOperatoreById(op));
                    JButton button = m.getBottoni().get(j); // Prendi il bottone
                    
                    try {
						if (button.getBackground().equals(Color.RED)) {
							button.setBackground(Color.GREEN);// Se è rosso, passa a verde
							op.fermaStazione(j);
        						
						} else if((button.getBackground().equals(Color.GREEN))){ // Se è verde o altro, passa a rosso
							button.setBackground(Color.RED);
    						op.avviaStazione(j);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}  
                }
            });
        }
    }

	/*@Override
	public void update(Observable o, Object arg) {
		System.out.println("UPDATEEEEEEEEEEEEEEEEEEE");
		ObservableStazioneLavoro staz = (ObservableStazioneLavoro) o;
		if(staz.getStatoStazione().toString().equalsIgnoreCase("WORKING")) {
			m.getBottoni().get(i).setBackground(Color.RED);
			JOptionPane.showMessageDialog(m, "Lavorazione avviata con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
		} else if (staz.getStatoStazione().toString().equalsIgnoreCase("READY")){
			m.getBottoni().get(i).setBackground(Color.GREEN);
			JOptionPane.showMessageDialog(m, "Lavorazione conclusa con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
		}
            
     }*/
	
	
	@Override
	public void update(Observable o, Object arg) {
	        ObservableStazioneLavoro staz = (ObservableStazioneLavoro) o;
	        System.out.println(staz.getStatoStazione());
	        int c = (Integer) arg;
	        System.out.println(c);
	        JButton button = m.getBottoni().get(c);
	        if (staz.getStatoStazione() == StatoStazione.WORKING) {
	             button.setBackground(Color.RED);
	             JOptionPane.showMessageDialog(m, "Lavorazione avviata con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
	        } else if (staz.getStatoStazione() == StatoStazione.READY) {
	             button.setBackground(Color.GREEN);
	             JOptionPane.showMessageDialog(m, "Lavorazione conclusa con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
	        }
	}

	

/*	
	
	public class OperatoreAction implements Observer{

	    private Operatore op; // Model
	    private GUILoginOperatore g; // Login View
	    private MainFrameOperatore m; // Main View
	    int i = 0;

	    public OperatoreAction(Operatore op, GUILoginOperatore g) {
	        this.op = op;
	        this.g = g;
	        addLoginListener();
	    }

	    public OperatoreAction(Operatore op, MainFrameOperatore m) {
	        this.op = op;
	        this.m = m;
	        op.setStazioniAssociate();      
	        m.aggiornaStazioni();
	        addMainListeners();
	    }

	    private void addLoginListener() {
	        g.getPannello().getLoginButton().addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (g.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {
	                    boolean isValid = op.verificaCredenzialiAccesso(
	                        g.getPannello().getUserText().getText(),
	                        g.getPannello().getPassText().getText());

	                    if (isValid) {
	                        JOptionPane.showMessageDialog(g, "Benvenuto!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
	                        g.dispose();
	                        DipendenteDAO d = new DipendenteDAO();
	                        op = new Operatore(d.selectIdByEmailPassword(new Operatore(g.getPannello().getUserText().getText(), g.getPannello().getPassText().getText())));
	                        MainFrameOperatore loginOp = new MainFrameOperatore(op);
	                        loginOp.setVisible(true);
	                    } else {
	                        JOptionPane.showMessageDialog(g, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            }
	        });
	    }

	    private void addMainListeners() {
	        DipendenteDAO d = new DipendenteDAO();

	        m.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() -> m.aggiornaStazioni()));
	        m.getMenu().getGestioneStazioni().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Stazioni")));
	        m.getMenu().getControlloMacchinari().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Macchinari")));
	        m.getMenu().getModificaProfilo().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Profilo")));

	        for (; i < m.getBottoni().size(); i++) {
	        	op.getStazioniAssociate().get(i).addObserver(this);
	            m.getBottoni().get(i).addActionListener(new ActionListener() {
	                int j = i;
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    System.out.println(j);
	                    op.setTipoOperatore(d.selectTipoOperatoreById(op));
	                    JButton button = m.getBottoni().get(j); // Prendi il bottone
	                    
	                    try {
    						if (button.getBackground().equals(Color.RED)) { // Se è rosso, passa a verde
								if (op.fermaStazione(j)) {
            						button.setBackground(Color.GREEN); // Cambia colore a verde
            						JOptionPane.showMessageDialog(m, "Lavorazione conclusa con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
        						} else {
            						JOptionPane.showMessageDialog(m, "Errore nella chiusura della lavorazione", "Errore", JOptionPane.ERROR_MESSAGE);
        						}
   							} else { // Se è rosso o altro, passa a verde
        						if (op.avviaStazione(j)) {
            						button.setBackground(Color.RED); // Cambia colore a verde
            						JOptionPane.showMessageDialog(m, "Lavorazione avviata con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
        						} else {
            						JOptionPane.showMessageDialog(m, "Errore nell'avvio della lavorazione", "Errore", JOptionPane.ERROR_MESSAGE);
        						}
    						}
						} catch (Exception e1) {
    						e1.printStackTrace();
						}  
	                }
	            });
	        }
	    }*/
	
	
}
