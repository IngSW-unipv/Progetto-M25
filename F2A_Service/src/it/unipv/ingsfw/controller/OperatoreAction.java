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

		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				addLoginListener();
				return null;
			}
		}.execute();

	}

	public OperatoreAction(Operatore op, MainFrameOperatore m) {
		this.op = op;
		this.m = m;

		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() { // per esecuzione su background thread
				op.setStazioniAssociate();
				return null;
			}

			@Override
			protected void done() {
				m.aggiornaStazioni();
				addMainListenersResponsabile();
			}
		}.execute(); // per eseguirlo su un worker thread

		// op.setStazioniAssociate();
		// m.aggiornaStazioni();
		// addMainListenersResponsabile();
	}

	public OperatoreAction(Operatore op, MainFrameManutentore man) {
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
									if (op.getTipoOperatore() == TipoOperatore.RESPONSABILE_STAZIONE) {
										JOptionPane.showMessageDialog(man, "Benvenuto responsabile!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
										new MainFrameOperatore(op).setVisible(true);
									}/* else {
										JOptionPane.showMessageDialog(man, "Benvenuto manutentore!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
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

	private void addMainListenersResponsabile() {
		Operatore responsabile = op;
		// DipendenteDAO d = new DipendenteDAO();

		m.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() -> m.aggiornaStazioni()));
		m.getMenu().getGestioneStazioni().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Stazioni")));
		m.getMenu().getControlloMacchinari().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Macchinari")));
		m.getMenu().getModificaProfilo().addMenuListener(new MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(), "Profilo")));

		for (; i < m.getBottoni().size(); i++) {
			if (responsabile.getStazioniAssociate().get(i) == null || responsabile.getStazioniAssociate().isEmpty()) {
				System.out.println("Errore: Nessuna stazione associata trovata.");
			} else {
				//responsabile.getStazioniAssociate().get(i).addObserver(this);
				responsabile.getStazioniAssociate().get(i).aggiungiObserver(this);
				System.out.println("Aggiunto observer alla stazione " + i);
			}

			m.getBottoni().get(i).addActionListener(new ActionListener() {
				int j = i;

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("j: " + j);
					i = j;
					responsabile.setTipoOperatore(F2aFacade.getInstance().getDipendentiFacade().selectTipoOperatoreById(responsabile));
					JButton button = m.getBottoni().get(j); // Prendi il bottone

					try {
						if (button.getBackground().equals(Color.RED)) {
							if (responsabile.fermaStazione(j) == 1)
								JOptionPane.showMessageDialog(m, "Lavorazione avviata con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
								button.setBackground(Color.GREEN);// Se è rosso, passa a verde

						} else if ((button.getBackground().equals(Color.GREEN))) { // Se è verde o altro, passa a rosso
							if (responsabile.avviaStazione(j) == 1)
								JOptionPane.showMessageDialog(m, "Lavorazione conclusa con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
								button.setBackground(Color.RED);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
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
								JOptionPane.showMessageDialog(man, "Guasto risolto", "Gestione guasto",
										JOptionPane.INFORMATION_MESSAGE);
								if (F2aFacade.getInstance().getStazioneLavoroFacade().chiusuraAssegnazioneOperatoreNoto(manutentore.getStazioniAssociate().get(i), manutentore))
									JOptionPane.showMessageDialog(man, "Assegnazione chiusa", "Chiusura assegnazione",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(man, "Anomalia persistente", "Attenzione",
										JOptionPane.ERROR_MESSAGE);
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
	 * @Override public void update(Observable o, Object arg) {
	 * System.out.println("UPDATEEEEEEEEEEEEEEEEEEE"); ObservableStazioneLavoro staz
	 * = (ObservableStazioneLavoro) o;
	 * if(staz.getStatoStazione().toString().equalsIgnoreCase("WORKING")) {
	 * m.getBottoni().get(i).setBackground(Color.RED);
	 * JOptionPane.showMessageDialog(m, "Lavorazione avviata con successo",
	 * "Avviso", JOptionPane.INFORMATION_MESSAGE); } else if
	 * (staz.getStatoStazione().toString().equalsIgnoreCase("READY")){
	 * m.getBottoni().get(i).setBackground(Color.GREEN);
	 * JOptionPane.showMessageDialog(m, "Lavorazione conclusa con successo",
	 * "Avviso", JOptionPane.INFORMATION_MESSAGE); }
	 * 
	 * }
	 */

	@Override
	public void update(Observable o, Object arg) {
		ObservableStazioneLavoro staz = (ObservableStazioneLavoro) o;
		System.out.println(staz.getStatoStazione());
		StatoStazione c = (StatoStazione) arg;
		System.out.println(c);
		JButton button = m.getBottoni().get(i);
		if (staz.getStatoStazione() == StatoStazione.WORKING) {
			button.setBackground(Color.RED);
			JOptionPane.showMessageDialog(m, "Lavorazione avviata con successo", "Avviso",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (staz.getStatoStazione() == StatoStazione.READY) {
			button.setBackground(Color.GREEN);
			JOptionPane.showMessageDialog(m, "Lavorazione conclusa con successo", "Avviso",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/*
	 * 
	 * public class OperatoreAction implements Observer{
	 * 
	 * private Operatore op; // Model private GUILoginOperatore g; // Login View
	 * private MainFrameOperatore m; // Main View int i = 0;
	 * 
	 * public OperatoreAction(Operatore op, GUILoginOperatore g) { this.op = op;
	 * this.g = g; addLoginListener(); }
	 * 
	 * public OperatoreAction(Operatore op, MainFrameOperatore m) { this.op = op;
	 * this.m = m; op.setStazioniAssociate(); m.aggiornaStazioni();
	 * addMainListeners(); }
	 * 
	 * private void addLoginListener() {
	 * g.getPannello().getLoginButton().addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { if
	 * (g.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase(
	 * "accesso")) { boolean isValid = op.verificaCredenzialiAccesso(
	 * g.getPannello().getUserText().getText(),
	 * g.getPannello().getPassText().getText());
	 * 
	 * if (isValid) { JOptionPane.showMessageDialog(g, "Benvenuto!", "Accesso",
	 * JOptionPane.INFORMATION_MESSAGE); g.dispose(); DipendenteDAO d = new
	 * DipendenteDAO(); op = new Operatore(d.selectIdByEmailPassword(new
	 * Operatore(g.getPannello().getUserText().getText(),
	 * g.getPannello().getPassText().getText()))); MainFrameOperatore loginOp = new
	 * MainFrameOperatore(op); loginOp.setVisible(true); } else {
	 * JOptionPane.showMessageDialog(g, "Credenziali errate", "Errore",
	 * JOptionPane.ERROR_MESSAGE); } } } }); }
	 * 
	 * private void addMainListeners() { DipendenteDAO d = new DipendenteDAO();
	 * 
	 * m.getMenu().getRefreshButton().addMenuListener(new MenuListenerAdapter(() ->
	 * m.aggiornaStazioni())); m.getMenu().getGestioneStazioni().addMenuListener(new
	 * MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(),
	 * "Stazioni"))); m.getMenu().getControlloMacchinari().addMenuListener(new
	 * MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(),
	 * "Macchinari"))); m.getMenu().getModificaProfilo().addMenuListener(new
	 * MenuListenerAdapter(() -> m.getCardLayout().show(m.getPannello(),
	 * "Profilo")));
	 * 
	 * for (; i < m.getBottoni().size(); i++) {
	 * op.getStazioniAssociate().get(i).addObserver(this);
	 * m.getBottoni().get(i).addActionListener(new ActionListener() { int j = i;
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { System.out.println(j);
	 * op.setTipoOperatore(d.selectTipoOperatoreById(op)); JButton button =
	 * m.getBottoni().get(j); // Prendi il bottone
	 * 
	 * try { if (button.getBackground().equals(Color.RED)) { // Se è rosso, passa a
	 * verde if (op.fermaStazione(j)) { button.setBackground(Color.GREEN); // Cambia
	 * colore a verde JOptionPane.showMessageDialog(m,
	 * "Lavorazione conclusa con successo", "Avviso",
	 * JOptionPane.INFORMATION_MESSAGE); } else { JOptionPane.showMessageDialog(m,
	 * "Errore nella chiusura della lavorazione", "Errore",
	 * JOptionPane.ERROR_MESSAGE); } } else { // Se è rosso o altro, passa a verde
	 * if (op.avviaStazione(j)) { button.setBackground(Color.RED); // Cambia colore
	 * a verde JOptionPane.showMessageDialog(m, "Lavorazione avviata con successo",
	 * "Avviso", JOptionPane.INFORMATION_MESSAGE); } else {
	 * JOptionPane.showMessageDialog(m, "Errore nell'avvio della lavorazione",
	 * "Errore", JOptionPane.ERROR_MESSAGE); } } } catch (Exception e1) {
	 * e1.printStackTrace(); } } }); } }
	 */

}
