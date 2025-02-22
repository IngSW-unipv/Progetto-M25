package it.unipv.ingsfw.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
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
import it.unipv.ingsfw.view.operatore.MenuListenerAdapter;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class ManutentoreAction implements Observer {

	private Operatore op; // Model
	private GUILoginOperatore g; // Login View
	private MainFrameManutentore man; // Main View Manutentore
	int i = 0;

	public ManutentoreAction(Operatore op, GUILoginOperatore g) {
		this.op = op;
		this.g = g;
		addLoginListener();

	}

	public ManutentoreAction(Operatore op, MainFrameManutentore man) {
		this.op = op;
		this.man = man;
		 op.setStazioniAssociate();
		 man.aggiornaStazioni();
		 addMainListenersManutentore();
	}

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
									if (op.getTipoOperatore() == TipoOperatore.MANUTENTORE) {
										JOptionPane.showMessageDialog(man, "Benvenuto manutentore!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
										MainFrameManutentore loginMan = new MainFrameManutentore(op);
										loginMan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										loginMan.setVisible(true);
									}
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

	private void addMainListenersManutentore() {
		//Operatore manutentore = op;
		// DipendenteDAO d = new DipendenteDAO();

		man.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() -> aggiornamenti()));
		man.getMenu().getGestioneStazioni().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Stazioni")));
		man.getMenu().getControlloMacchinari().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Macchinari")));
		man.getMenu().getModificaProfilo().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Profilo")));

		
		for (int i = 0; i < man.getBottoni().size(); i++) {
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
		
		for (int i = 0; i < man.getBottoni().size(); i++) {
			final int index = i; // Crea una variabile final per catturare il valore di i
			
			man.getBottoni().get(index).addActionListener(new ActionListener() {
				//int j = i;

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("j: " + index);
					//i = j;
					JButton button = man.getBottoni().get(index); // Prendi il bottone

					try {
						if (button.getBackground().equals(Color.ORANGE)) {
							if (op.correzioneGuasto(index)) {
								//button.setBackground(Color.GREEN);// Se è arancione, passa a verde
								//JOptionPane.showMessageDialog(man, "Guasto risolto", "Gestione guasto",JOptionPane.INFORMATION_MESSAGE);
								if (F2aFacade.getInstance().getStazioneLavoroFacade().chiusuraAssegnazioneOperatoreNoto(op.getStazioniAssociate().get(index), op)) {
									//JOptionPane.showMessageDialog(man, "Assegnazione chiusa", "Chiusura assegnazione",JOptionPane.INFORMATION_MESSAGE);
								}
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
	}

	private void aggiornaStazioni() {
        ArrayList<ObservableStazioneLavoro> stazioni = F2aFacade.getInstance().getStazioneLavoroFacade().selectStazioniByOperatore(this.op);

        man.getPannelloGuasti().removeAll();
        man.getBottoni().clear();
        man.setGroup(new ButtonGroup());
        JLabel noGuasti;

        for (ObservableStazioneLavoro stazione : stazioni) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Stazione " + stazione.getIdStazione());
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(30, 30));

            updateButtonColor(button, stazione.getStatoStazione());
            man.getBottoni().add(button);
            panel.add(label);
            panel.add(button);
            man.getGroup().add(button);
            man.getPannelloGuasti().add(panel);
            button.setEnabled(true);
        }

        if (stazioni.size() == 0) {
            JPanel panelNoStazioni = new JPanel();
            noGuasti = new JLabel("NESSUNA STAZIONE ASSEGNATA, AGGIORNA LA PAGINA");
            panelNoStazioni.add(noGuasti);
            man.getPannelloGuasti().add(panelNoStazioni);
        }

        man.getPannelloGuasti().revalidate();
        man.getPannelloGuasti().repaint();
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
		ObservableStazioneLavoro staz = (ObservableStazioneLavoro) o;
		System.out.println(staz.getStatoStazione());
		int c = (Integer) arg;
		System.out.println(c);
		JButton button = man.getBottoni().get(c);
		if (staz.getStatoStazione() == StatoStazione.WORKING) {
			button.setBackground(Color.RED);
			JOptionPane.showMessageDialog(man, "UPDATE: Guasto risolto", "Gestione guasto",JOptionPane.INFORMATION_MESSAGE);
		} else if (staz.getStatoStazione() == StatoStazione.READY) {
			button.setBackground(Color.GREEN);
			JOptionPane.showMessageDialog(man, "UPDATE: Assegnazione chiusa", "Avviso",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
