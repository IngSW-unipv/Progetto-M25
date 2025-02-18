package it.unipv.ingsfw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.view.operatore.MainFrameOperatore;
import it.unipv.ingsfw.view.operatore.MenuListenerAdapter;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class OperatoreAction {

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
    	
    	
        // EX Refresh delle stazioni
        /*m.getMenu().getRefreshButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.aggiornaStazioni();
            }
        });*/

    	// Refresh delle stazioni
        m.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() -> m.aggiornaStazioni()));
        
        // Navigazione tra le schermate con CardLayout
        m.getMenu().getGestioneStazioni().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Stazioni")));
        m.getMenu().getControlloMacchinari().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Macchinari")));
        m.getMenu().getModificaProfilo().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Profilo")));
        
        
		for (; i < m.getBottoni().size(); i++) {
			m.getBottoni().get(i).addActionListener(new ActionListener() {
				int j = i;
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(j);
					op.setTipoOperatore(d.selectTipoOperatoreById(op));
					try {
						//if(!op.getStazioniAssociate().get(j).getStatoStazione().toString().equalsIgnoreCase("MAINTENANCE"))
							op.avviaStazione(j);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
        
        
    }
}