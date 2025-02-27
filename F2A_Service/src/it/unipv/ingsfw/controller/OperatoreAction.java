package it.unipv.ingsfw.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.model.users.TipoOperatore;
import it.unipv.ingsfw.view.operatore.MainFrameManutentore;
import it.unipv.ingsfw.view.operatore.MainFrameOperatore;
import it.unipv.ingsfw.view.operatore.MenuListenerAdapter;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class OperatoreAction implements Observer {

	private Operatore op; // Model
	private GUILoginOperatore g; // Login View
	private MainFrameOperatore m; // Main View Responsabile
	private MainFrameManutentore man; // Main View Manutentore
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
		//m.aggiornaStazioni();
		aggiornaStazioni();
		addMainListenersResponsabile();
	}

	/*
	public OperatoreAction(Operatore op, MainFrameManutentore man) {
		this.op = op;
		this.man = man;
		op.setStazioniAssociate();
		man.aggiornaStazioni();
		addMainListenersManutentore();
	}*/
	/*
	 * private void addLoginListener() {
	 * g.getPannello().getLoginButton().addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { if
	 * (g.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase(
	 * "accesso")) {
	 * 
	 * boolean isValid =
	 * op.verificaCredenzialiAccesso(g.getPannello().getUserText().getText(),g.
	 * getPannello().getPassText().getText());
	 * 
	 * op = new Operatore(F2aFacade.getInstance().getDipendentiFacade().
	 * selectIdByEmailPassword(new
	 * Operatore(g.getPannello().getUserText().getText(),
	 * g.getPannello().getPassText().getText())));
	 * 
	 * if (isValid &&
	 * F2aFacade.getInstance().getDipendentiFacade().selectTipoOperatoreById(op) ==
	 * TipoOperatore.RESPONSABILE_STAZIONE) { JOptionPane.showMessageDialog(g,
	 * "Benvenuto responsabile!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
	 * g.dispose(); //DipendenteDAO d = new DipendenteDAO(); //MainFrameOperatore
	 * loginOp = new MainFrameOperatore(op); //loginOp.setVisible(true);
	 * MainFrameOperatore.startMainFrameOperatore(op); } else if (isValid &&
	 * F2aFacade.getInstance().getDipendentiFacade().selectTipoOperatoreById(op) ==
	 * TipoOperatore.MANUTENTORE){ JOptionPane.showMessageDialog(g,
	 * "Benvenuto manutentore!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
	 * g.dispose(); //MainFrameManutentore loginMan = new MainFrameManutentore(op);
	 * //loginMan.setVisible(true);
	 * MainFrameManutentore.startMainFrameManutentore(op); }else {
	 * JOptionPane.showMessageDialog(g, "Credenziali errate", "Errore",
	 * JOptionPane.ERROR_MESSAGE); } } } }); }
	 */

	private void addLoginListener() {
		g.getPannello().getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (g.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {

					new SwingWorker<Boolean, Void>() {
						@Override
						protected Boolean doInBackground() throws Exception {
							return op.verificaCredenzialiAccesso(g.getPannello().getUserText().getText(),g.getPannello().getPassText().getText());
						}

						@Override
						protected void done() {
							try {
								boolean isValid = get();
								op = new Operatore(F2aFacade.getInstance().getDipendentiFacade().selectIdByEmailPassword(new Operatore(g.getPannello().getUserText().getText(),
										g.getPannello().getPassText().getText())));

								if (isValid) {
									g.dispose();
									op.setTipoOperatore(F2aFacade.getInstance().getDipendentiFacade().selectTipoOperatoreById(op));
									if (op.getTipoOperatore() == TipoOperatore.RESPONSABILE_STAZIONE) {
										JOptionPane.showMessageDialog(man, "Benvenuto responsabile!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
										SwingUtilities.invokeLater(() -> {
											MainFrameOperatore loginOp = new MainFrameOperatore(op);
											loginOp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
											loginOp.setVisible(true);
										});
										//new MainFrameOperatore(op).setVisible(true);
									} /*else {
										JOptionPane.showMessageDialog(man, "Benvenuto manutentore!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
										SwingUtilities.invokeLater(() -> {
											MainFrameManutentore loginMan = new MainFrameManutentore(op);
											loginMan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
											loginMan.setVisible(true);
										});
										new MainFrameManutentore(op).setVisible(true);
									}*/
								} else {
									JOptionPane.showMessageDialog(g, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}.execute();
				}
			}
		});
	}
	
	private void aggiornamenti() {
		//m.aggiornaStazioni();
		aggiornaStazioni();
		assegnazioneListenerBottoni();
	}

	private void addMainListenersResponsabile() {
		//Operatore responsabile = op;
		// DipendenteDAO d = new DipendenteDAO();

		m.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() -> aggiornamenti()));
		m.getMenu().getGestioneStazioni().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Stazioni")));
		m.getMenu().getControlloMacchinari().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Macchinari")));
		m.getMenu().getModificaProfilo().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Profilo")));

		for (int i = 0; i < m.getBottoni().size(); i++) {
			final int index = i; // Crea una variabile final per catturare il valore di i
			if (op.getStazioniAssociate().get(index) == null || op.getStazioniAssociate().isEmpty()) {
				System.out.println("Errore: Nessuna stazione associata trovata.");
			} else {
				//responsabile.getStazioniAssociate().get(i).addObserver(this);
				op.getStazioniAssociate().get(index).aggiungiObserver(this);
				//op.getStazioniAssociate().get(index).notificaObservers(index);
				System.out.println("Aggiunto observer alla stazione " + index);
			}
		}
		assegnazioneListenerBottoni();
	}
	
	private void assegnazioneListenerBottoni() {
		
		for (int i = 0; i < m.getBottoni().size(); i++) {
			final int index = i; // Crea una variabile final per catturare il valore di i
			
			m.getBottoni().get(index).addActionListener(new ActionListener() {
				//int j = i;

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("j: " + index);
					//i = j;
					op.setTipoOperatore(F2aFacade.getInstance().getDipendentiFacade().selectTipoOperatoreById(op));
					JButton button = m.getBottoni().get(index); // Prendi il bottone

					try {
						if (button.getBackground().equals(Color.RED)) {
							if (op.fermaStazione(index) == 1) {
								//JOptionPane.showMessageDialog(m, "Lavorazione conclusa con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
								//button.setBackground(Color.GREEN);// Se è rosso, passa a verde
							}
						} else if ((button.getBackground().equals(Color.GREEN))) { // Se è verde o altro, passa a rosso
							if (op.avviaStazione(index) == 1) {
								//JOptionPane.showMessageDialog(m, "Lavorazione avviata con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
								//button.setBackground(Color.RED);
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}

	/*
	private void addMainListenersManutentore() {
		Operatore manutentore = op;
		// DipendenteDAO d = new DipendenteDAO();

		man.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() -> man.aggiornaStazioni()));
		man.getMenu().getGestioneStazioni().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Stazioni")));
		man.getMenu().getControlloMacchinari().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Macchinari")));
		man.getMenu().getModificaProfilo().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Profilo")));

		for (int i = 0; i < man.getBottoni().size(); i++) {
			 final int index = i; // Crea una variabile final per catturare il valore di i
			if (manutentore.getStazioniAssociate().get(index) == null || manutentore.getStazioniAssociate().isEmpty()) {
				System.out.println("Errore: Nessuna stazione associata trovata.");
			} else {
				manutentore.getStazioniAssociate().get(index).addObserver(this);
				System.out.println("Aggiunto observer alla stazione " + index);
			}
			man.getBottoni().get(index).addActionListener(new ActionListener() {
				//int j = i;

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("j: " + index);
					//i = j;
					JButton button = man.getBottoni().get(index); // Prendi il bottone

					try {
						if (button.getBackground().equals(Color.ORANGE)) {
							if (manutentore.correzioneGuasto(index)) {
								button.setBackground(Color.GREEN);// Se è arancione, passa a verde
								JOptionPane.showMessageDialog(man, "Guasto risolto", "Gestione guasto",JOptionPane.INFORMATION_MESSAGE);
								if (F2aFacade.getInstance().getStazioneLavoroFacade().chiusuraAssegnazioneOperatoreNoto(manutentore.getStazioniAssociate().get(index), manutentore))
									JOptionPane.showMessageDialog(man, "Assegnazione chiusa", "Chiusura assegnazione",JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(man, "Anomalia persistente", "Attenzione",JOptionPane.ERROR_MESSAGE);
							}

						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}*/

	private void aggiornaStazioni() {
        ArrayList<ObservableStazioneLavoro> stazioni = F2aFacade.getInstance().getLavorazioneCapiFacade().selectStazioniByOperatore(this.op);

        m.getPannelloStazioni().removeAll();
        m.getBottoni().clear();
        m.setGroup(new ButtonGroup());
        JLabel noStazioni;
        int numCapi = 0;
        boolean check = false;

        for (ObservableStazioneLavoro stazione : stazioni) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Stazione " + stazione.getIdStazione());
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(30, 30));

            check = stazione.checkPresenzaCapi();
            
            if(check) {
            	numCapi = stazione.getListaCapiDaLavorare().size();
            }
            updateButtonColor(button, stazione.getStatoStazione());
            m.getBottoni().add(button);
            panel.add(label);
            panel.add(button);
            panel.add(new JLabel(" | Numero capi assegnati: " + String.valueOf(numCapi)));
            m.getGroup().add(button);
            m.getPannelloStazioni().add(panel);
            button.setEnabled(true);
            numCapi = 0;
        }

        if (stazioni.size() == 0) {
            JPanel panelNoStazioni = new JPanel();
            noStazioni = new JLabel("NESSUNA STAZIONE ASSEGNATA, AGGIORNA LA PAGINA");
            panelNoStazioni.add(noStazioni);
            m.getPannelloStazioni().add(panelNoStazioni);
        }

        m.getPannelloStazioni().revalidate();
        m.getPannelloStazioni().repaint();
    }

    private void updateButtonColor(JButton button, StatoStazione stato) {
        Color color;
        switch (stato) {
            case READY -> color = Color.GREEN;
            case MAINTENANCE -> color = Color.ORANGE;
            case WORKING -> color = Color.RED;
            default -> color = Color.GRAY;
        }
        button.setBackground(color);
    }

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("UPDATE");
		ObservableStazioneLavoro staz = (ObservableStazioneLavoro) o;
		System.out.println(staz.getStatoStazione());
		int c = (Integer) arg;
		System.out.println(c);
		JButton button = m.getBottoni().get(c);
		if (staz.getStatoStazione() == StatoStazione.WORKING) {
			button.setBackground(Color.RED);
			JOptionPane.showMessageDialog(m, "UPDATE: Lavorazione avviata con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
		} else if (staz.getStatoStazione() == StatoStazione.READY) {
			button.setBackground(Color.GREEN);
			JOptionPane.showMessageDialog(m, "UPDATE: Lavorazione conclusa con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
		} else if (staz.getStatoStazione() == StatoStazione.MAINTENANCE) {
			button.setBackground(Color.GREEN);
			JOptionPane.showMessageDialog(m, "UPDATE: Rilevato guasto", "Avviso", JOptionPane.ERROR_MESSAGE);
		}
	}

	

}
