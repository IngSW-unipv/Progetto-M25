package it.unipv.ingsfw.controller;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.tickets.StatoTicket;
import it.unipv.ingsfw.model.tickets.Ticket;
import it.unipv.ingsfw.model.users.Corriere;
import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.model.users.TipoOperatore;
import it.unipv.ingsfw.view.corriere.MainFrameTicket;
import it.unipv.ingsfw.view.corriere.viewLogin.GUILoginCorriere;
import it.unipv.ingsfw.view.operatore.MainFrameManutentore;
import it.unipv.ingsfw.view.operatore.MainFrameOperatore;
import it.unipv.ingsfw.view.operatore.MenuListenerAdapter;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class TicketAction {

	private Ticket tik; // ModelFacade
	private GUILoginCorriere login; // Login View
	private MainFrameTicket main; // Main View Corriere
	int i = 0;

	public TicketAction(GUILoginCorriere login) {
		this.tik = new Ticket(null, null);
		this.login = login;
		addLoginListener();
	}

	public TicketAction(Ticket tik, MainFrameTicket main) {
		this.tik = tik;
		this.main = main;
		addMainListenersCorriere();
	}

	// setto i Tiket associati al corriere, ma non è meglio fare così anche per
	// operatore
	// questa classe facendo da controller non dovrebbe richiamare direttamente il
	// controllo delle credenziali invece che farlo invocare da corriere
	/**
	 * Ticket ticket = new Ticket(StatoTicket.ASSEGNATO ,cor); ArrayList <Ticket>
	 * ticketAssegnati = new ArrayList <Ticket>(); ticketAssegnati =
	 * F2aFacade.getInstance().getGestioneTicketsFacade().selectTicketByStatoAndCorriere(ticket);
	 * //cor.setStazioniAssociate(); ^ //main.aggiornaStazioni();
	 * addMainListenersCorriere(); }
	 **/

	/**
	 * public CorriereAction(Operatore op, MainFrameManutentore man) { this.op = op;
	 * this.man = man; op.setStazioniAssociate(); man.aggiornaStazioni();
	 * addMainListenersManutentore(); }
	 **/

	// a tutti i bottoni associa un'azione (evento che si deve scatenare)
	// iniziamo con l'associare un Action Listener al bottone prresente nel bottone
	// di login
	private void addLoginListener() {
		login.getPannello().getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (login.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {
					tik.setCorriere(F2aFacade.getInstance().getDipendentiFacade()
							.selectCorriereByEmailPassword(new Corriere(login.getPannello().getUserText().getText(),
									login.getPannello().getPassText().getText())));
					Corriere cor = tik.getCorriere();
					boolean isValid = cor.verificaCredenzialiAccesso(login.getPannello().getUserText().getText(),
							login.getPannello().getPassText().getText());

					cor = new Corriere(F2aFacade.getInstance().getDipendentiFacade().selectIdByEmailPassword(
							new Corriere(login.getPannello().getUserText().getText()/** mail **/
									, login.getPannello().getPassText().getText()/** password **/
					)));

					// se mail e password inseriti sono corretti e i tipo di dipendente è un
					// corriere
					if (isValid && F2aFacade.getInstance().getDipendentiFacade().selectTipoDipendenteById(cor)
							.equalsIgnoreCase("CORRIERE")) {
						JOptionPane.showMessageDialog(login, "Benvenuto Corriere!", "Accesso",
								JOptionPane.INFORMATION_MESSAGE);
						login.dispose();
						// DipendenteDAO d = new DipendenteDAO();
						MainFrameTicket login = new MainFrameTicket(tik);
						login.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(login, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	private void addMainListenersCorriere() {
		// DipendenteDAO d = new DipendenteDAO();
		main.getMenu().getGestioneTicket().addMenuListener(
				new MenuListenerAdapter(() -> main.getCardLayout().show(main.getPannello(), "Ticket")));
		;
		main.getMenu().getModificaProfilo().addMenuListener(
				new MenuListenerAdapter(() -> main.getCardLayout().show(main.getPannello(), "Profilo")));
		;
		main.getBottoneScelta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (main.getBottoneScelta().getActionCommand().equalsIgnoreCase("Visualizza Ticket Assegnati")) {
					tik.setStato(StatoTicket.ASSEGNATO);
					ArrayList<String> anteprimaTicket = F2aFacade.getInstance().getGestioneTicketsFacade()
							.selectIDTipoTicketByStatoAndCorriere(tik);
					String[] optionsArray = anteprimaTicket.toArray(new String[0]);
					if (optionsArray.length > 0) {
						// Creazione del menu a tendina con JOptionPane
						String selectedOption = (String) JOptionPane.showInputDialog(null, // Finestra principale (null
																							// usa
																							// la finestra di default)
								"Scegli un Ticket:", // Messaggio che appare sopra il menu a tendina
								"Ticket Assegnati", // Titolo della finestra
								JOptionPane.PLAIN_MESSAGE, // Tipo di finestra di dialogo (senza icona)
								null, // Icona (null per nessuna icona)
								optionsArray, // I valori delle opzioni nel menu a tendina
								optionsArray[0] // Il valore predefinito (la prima opzione)
						);

						System.out.println(selectedOption);
						if (selectedOption != null) {
							String idTik = selectedOption.substring(4, 8);
							System.out.println(idTik);
						}
					}else {JOptionPane.showMessageDialog(main, "Nessun Ticket Assegnato", "Errore", JOptionPane.ERROR_MESSAGE);}
				}
			}

		});
	}
/**
	private void showTicketInfo() {
	        Ticket anteprimaTicket = F2aFacade.getInstance().getGestioneTicketsFacade().selectTicketByStatoAndCorriere(tik);
	        main.getPannelloTicket().removeAll();
	        ((Collection<String>) main.getBottoneScelta()).clear();

	        for (ObservableStazioneLavoro stazione : stazioni) {
	            JPanel panel = new JPanel();
	            JLabel label = new JLabel("Stazione " + stazione.getIdStazione());
	            JButton button = new JButton();
	            button.setPreferredSize(new Dimension(30, 30));

	            updateButtonColor(button, stazione.getStatoStazione());
	            m.getBottoni().add(button);
	            panel.add(label);
	            panel.add(button);
	            m.getGroup().add(button);
	            m.getPannelloStazioni().add(panel);
	            button.setEnabled(true);
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

}
