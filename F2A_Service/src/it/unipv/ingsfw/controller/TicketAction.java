package it.unipv.ingsfw.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.StatoTappa;
import it.unipv.ingsfw.model.tickets.StatoTicket;
import it.unipv.ingsfw.model.tickets.Ticket;
import it.unipv.ingsfw.model.users.Corriere;
import it.unipv.ingsfw.view.corriere.FrameSvolgimentoTicket;
import it.unipv.ingsfw.view.corriere.MainFrameTicket;
import it.unipv.ingsfw.view.corriere.viewLogin.GUILoginCorriere;
import it.unipv.ingsfw.view.operatore.MenuListenerAdapter;

public class TicketAction {

	private Ticket tik; // ModelFacade
	private Capo capoFit;
	private Ticket newTik;
	private GUILoginCorriere login; // Login View
	private MainFrameTicket main; // Main View Corriere
	private FrameSvolgimentoTicket svt1; // Main View Svolgimento Ticket

	public TicketAction(GUILoginCorriere login) {
		this.tik = new Ticket(null, null);
		this.login = login;
		addLoginListener();
	}

	public TicketAction(Ticket tik, MainFrameTicket main) {
		this.tik = tik;
		this.main = main;
		addMainListenersCorriere();
		addSvolgimentoTicketListeners();
	}

	public TicketAction(Ticket tik, FrameSvolgimentoTicket svt) {
		this.tik = tik;
		this.svt1 = svt;
		addRitiroAvvenutoPressoTappaListeners();
		addConsegnaAvvenutaPressoTappaListeners();
		// addSvolgimentoTicketListeners();

	}


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
						String selectedOption = (String) JOptionPane.showInputDialog(null,"Scegli un Ticket:","Ticket Assegnati", JOptionPane.PLAIN_MESSAGE, null, optionsArray,
								optionsArray[0] // Il valore predefinito del menù a tendina (la prima opzione)
						);

						System.out.println(selectedOption);
						if (selectedOption != null) {
							// prelevo l'id dall'anteprima
							String idTik = selectedOption.substring(4, 8);
							System.out.println(idTik);
							newTik = new Ticket(idTik);
							showTicketInfo(newTik);
							main.getBottonePresaCaricoTicket().setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(main, "Nessun Ticket Assegnato", "Errore",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});
	}

	private void addSvolgimentoTicketListeners() {

		main.getBottonePresaCaricoTicket().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (main.getBottonePresaCaricoTicket().getActionCommand().equalsIgnoreCase("Prendi In Carico")) {
					newTik.setStato(StatoTicket.PRESO_IN_CARICO);
					F2aFacade.getInstance().getGestioneTicketsFacade().updateStatoTicket(newTik);
					newTik = F2aFacade.getInstance().getGestioneTicketsFacade().selectTicketById(newTik);
					main.setVisible(false);
					main.setJMenuBar(null);
					main.removeAll();
					FrameSvolgimentoTicket svt1 = new FrameSvolgimentoTicket(newTik);
					svt1.setVisible(true);
					svt1.getFieldId().setText("Svolgimento Ticket: " + newTik.getIdTicket());
					showTappaCorrenteECapi(svt1);
				}
			}
		});

	}

	private void addRitiroAvvenutoPressoTappaListeners() {

		svt1.getBottoneRitiroAvvenuto().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (svt1.getBottoneRitiroAvvenuto().getActionCommand()
						.equalsIgnoreCase("Ritiro presso tappa corrente avvenuto")) {
					// Se la lista dei capi ritirati per il ticket non è vuota
					// appena il bottone viene premuto i capi vengono posti allo stato "RITIRATO"
					if (!svt1.getTik().getListaCapiRitOCon().isEmpty()) {
						Capo capoFit = new Capo((Negozio) svt1.getTik().getItinerario().getTappaCorrente(), null);
						capoFit.setStatoCapo((StatoCapo.RITIRATO));
						F2aFacade.getInstance().getCapoFacade().updateStatoCapoByTappa(capoFit);
					}
					// quando il corriere conferma l'avvenuto ritiro presso la tappa viene
					// aggiornato lo stato di quest'ultima in "ATTRAVERSATA"
					aggiornaTappaConsRit(svt1);
				}
			}
		});

	}

	private void addConsegnaAvvenutaPressoTappaListeners() {

		svt1.getBottoneConsegnaAvvenuta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (svt1.getBottoneConsegnaAvvenuta().getActionCommand()
						.equalsIgnoreCase("Consegna presso tappa corrente avvenuta")) {
					// Se la lista dei capi consegnati per il ticket non è vuota
					// appena il bottone viene premuto i capi vengono posti allo stato "CONSEGNATO"
					if (!svt1.getTik().getListaCapiRitOCon().isEmpty()) {
						Capo capo_fit = new Capo(null, (Negozio) svt1.getTik().getItinerario().getTappaCorrente());
						capo_fit.setStatoCapo((StatoCapo.CONSEGNATO));
						F2aFacade.getInstance().getCapoFacade().updateStatoCapoByTappa(capo_fit);
					}
					// quando il corriere conferma l'avvenuta consegna presso la tappa viene
					// aggiornato lo stato di quest'ultima in "ATTRAVERSATA"
					aggiornaTappaConsRit(svt1);
				}
			}
		});

	}

	private void aggiornaTappaConsRit(FrameSvolgimentoTicket svt1) {
		svt1.removeAll();
		svt1.setVisible(false);
		FrameSvolgimentoTicket svt2 = new FrameSvolgimentoTicket(tik);
		svt2.setVisible(true);
		tik.getItinerario().getTappaCorrente().setStato(StatoTappa.ATTRAVERSATA);
		svt2.getFieldId().setText("Svolgimento Ticket: " + tik.getIdTicket());
		// all'ultima tappa
		if (tik.getItinerario().getListaTappeNonAttraversate().size() == 1) {
			svt2.getBottoneRitiroAvvenuto().setVisible(false);
			svt2.getBottoneConsegnaAvvenuta().setVisible(false);
			svt2.getBottoneCompletaTicket().setVisible(true);
			addCompletaTicketListeners(svt2);
			svt2.getLabelTappaCorrente().setVisible(false);
		} else {
			showTappaCorrenteECapi(svt2);
		}
	}

	private void addCompletaTicketListeners(FrameSvolgimentoTicket svt2) {
		svt2.getBottoneCompletaTicket().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (svt2.getBottoneCompletaTicket().getActionCommand().equalsIgnoreCase("Completa Ticket")) {
					//CAMBIO LO STATO DI TUTTI I CAPI DEL TICKET
					int size = svt2.getTik().getListaCapiRitOCon().size();
					for (int i = 0; i < size; i++) {
						Capo capo_fit = new Capo(svt2.getTik().getListaCapiRitOCon().get(i).getIdCapo(),
								StatoCapo.IN_LAVORAZIONE);
						F2aFacade.getInstance().getCapoFacade().updateStatoCapo(capo_fit);
					}
					//CAMBIO LO STATO DEL TICKET IN COMPLETATO
					svt2.getTik().setStato(StatoTicket.COMPLETATO);
					F2aFacade.getInstance().getGestioneTicketsFacade().updateStatoTicket(svt2.getTik());
					// RITORNO ALLA HOME PAGE DEL CORRIERE
					MainFrameTicket main = new MainFrameTicket(svt2.getTik());
					main.setVisible(true);
					svt2.setVisible(false);
				}
			}
		});

	}

	private void showTicketInfo(Ticket ticket) {
		// ottengo tutte le info relative al ticket scelto dal corriere
		ticket = F2aFacade.getInstance().getGestioneTicketsFacade().selectTicketById(ticket);
		// popolo i componenti con le informazioni del ticket
		main.getFieldId().setText(String.valueOf(ticket.getIdTicket()));
		main.getFieldId().setVisible(true);
		// creo font
		Font currentFont = main.getFieldId().getFont();
		Font boldFont = currentFont.deriveFont(Font.ITALIC);
		main.getFieldId().setFont(boldFont);
		main.getFieldTipologia().setText(String.valueOf(ticket.getTipologia()));
		main.getFieldTipologia().setVisible(true);
		main.getFieldTipologia().setFont(boldFont);
		main.getAreaDescrizione().setText(ticket.getItinerario().toString());
		main.getAreaDescrizione().setVisible(true);
		main.getAreaDescrizione().setFont(boldFont);
		main.getFieldMezzoAssociato().setText(ticket.getMezzo().toString());
		main.getFieldMezzoAssociato().setVisible(true);
		main.getFieldMezzoAssociato().setFont(boldFont);
		main.getLabelId().setVisible(true);
		main.getLabelTipologia().setVisible(true);
		main.getLabelItinerario().setVisible(true);
		main.getLabelMezzoAssociato().setVisible(true);
		// aggiorno l'interfaccia grafica
		// main.getPannelloTicket().revalidate();
		// main.getPannelloTicket().repaint();
	}

	private void showTappaCorrenteECapi(FrameSvolgimentoTicket svt) {
		// inizialmente mostrata la prima tappa dell'itinerario
		svt.getFieldTappaCorrente().setText("Tappa Corrente: " + svt.getTik().getItinerario().getTappaCorrente());
		capoFit = new Capo();
		if (svt.getTik().getTipologia().toString().equalsIgnoreCase("RITIRO")) {
			svt.getBottoneRitiroAvvenuto().setVisible(true);
			svt.getBottoneConsegnaAvvenuta().setVisible(false);
			System.out.println(svt.getTik().getItinerario());
			System.out.println((Negozio) svt.getTik().getItinerario().getTappaCorrente());
			capoFit.setNegozioDeposito((Negozio) svt.getTik().getItinerario().getTappaCorrente());
			ArrayList<Capo> capi = F2aFacade.getInstance().getCapoFacade().selectCapiDaRitirareByTappa(capoFit);
			svt.getFieldListaCapiDaRitirarePressoTappa().setText(showCapiOfTappa(capi, svt));
			svt.getLabelCapiDaRitirarePressoTappa().setVisible(true);
			svt.getFieldListaCapiDaRitirarePressoTappa().setVisible(true);
		} else if (svt.getTik().getTipologia().toString().equalsIgnoreCase("CONSEGNA")) {
			svt.getBottoneConsegnaAvvenuta().setVisible(true);
			svt.getBottoneRitiroAvvenuto().setVisible(false);
			capoFit.setNegozioConsegna((Negozio) svt.getTik().getItinerario().getTappaCorrente());
			ArrayList<Capo> capi = F2aFacade.getInstance().getCapoFacade().selectCapiDaConsegnareByTappa(capoFit);
			svt.getFieldListaCapiDaConsegnarePressoTappa().setText(showCapiOfTappa(capi, svt));
			svt.getLabelCapiDaConsegnarePressoTappa().setVisible(true);
			svt.getFieldListaCapiDaConsegnarePressoTappa().setVisible(true);
		}
	}

	private String showCapiOfTappa(ArrayList<Capo> capi, FrameSvolgimentoTicket svt) {
		Font currentFont = svt.getFieldId().getFont();
		Font boldFont = currentFont.deriveFont(Font.ITALIC);
		svt.getFieldId().setFont(boldFont);
		String listaCapi = "";
		for (Capo capo : capi) {
			listaCapi += "\n- " + capo.toStringCor();
			// aggiungo alla lista dei capi ritirati o prelevati i capi della tappa
			
			svt.getTik().getListaCapiRitOCon().add(capo);
		}
		return listaCapi;
	}
}
