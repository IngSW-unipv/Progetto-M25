package it.unipv.ingsfw.controller;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
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

		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				addLoginListener();
				return null;
			}
		}.execute();

	}

	public ManutentoreAction(Operatore op, MainFrameManutentore man) {
		this.op = op;
		this.man = man;

		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() { // per esecuzione su background thread
				op.setStazioniAssociate();
				return null;
			}

			@Override
			protected void done() {
				man.aggiornaStazioni();
				addMainListenersManutentore();

			}
		}.execute(); // per eseguirlo su un worker thread
		// op.setStazioniAssociate();
		// man.aggiornaStazioni();
		// addMainListenersManutentore();
	}

	private void addLoginListener() {
		g.getPannello().getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (g.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {

					new SwingWorker<Boolean, Void>() {
						@Override
						protected Boolean doInBackground() throws Exception {
							return op.verificaCredenzialiAccesso(g.getPannello().getUserText().getText(),
									g.getPannello().getPassText().getText());
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
										new MainFrameManutentore(op).setVisible(true);
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

	private void addMainListenersManutentore() {
		Operatore manutentore = op;
		// DipendenteDAO d = new DipendenteDAO();

		man.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() -> man.aggiornaStazioni()));
		man.getMenu().getGestioneStazioni().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Stazioni")));
		man.getMenu().getControlloMacchinari().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Macchinari")));
		man.getMenu().getModificaProfilo().addMenuListener(new MenuListenerAdapter(() -> man.getCardLayout().show(man.getPannello(), "Profilo")));

		for (; i < man.getBottoni().size(); i++) {
			if (manutentore.getStazioniAssociate().get(i) == null || manutentore.getStazioniAssociate().isEmpty()) {
				System.out.println("Errore: Nessuna stazione associata trovata.");
			} else {
				manutentore.getStazioniAssociate().get(i).addObserver(this);
				System.out.println("Aggiunto observer alla stazione " + i);
			}
			man.getBottoni().get(i).addActionListener(new ActionListener() {
				int j = i;

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("j: " + j);
					i = j;
					JButton button = man.getBottoni().get(j); // Prendi il bottone

					try {
						if (button.getBackground().equals(Color.ORANGE)) {
							if (manutentore.correzioneGuasto(j)) {
								button.setBackground(Color.GREEN);// Se è arancione, passa a verde
								JOptionPane.showMessageDialog(man, "Guasto risolto", "Gestione guasto",JOptionPane.INFORMATION_MESSAGE);
								if (F2aFacade.getInstance().getStazioneLavoroFacade().chiusuraAssegnazioneOperatoreNoto(manutentore.getStazioniAssociate().get(i), manutentore))
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
			JOptionPane.showMessageDialog(man, "Lavorazione avviata con successo", "Avviso",JOptionPane.INFORMATION_MESSAGE);
		} else if (staz.getStatoStazione() == StatoStazione.READY) {
			button.setBackground(Color.GREEN);
			JOptionPane.showMessageDialog(man, "Lavorazione conclusa con successo", "Avviso",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
