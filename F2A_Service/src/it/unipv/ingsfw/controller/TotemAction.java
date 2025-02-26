package it.unipv.ingsfw.controller;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import it.unipv.ingsfw.facade.F2aFacade;
import it.unipv.ingsfw.model.Capo;
import it.unipv.ingsfw.model.StatoCapo;
import it.unipv.ingsfw.model.TipoLavaggio;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.Totem;
import it.unipv.ingsfw.model.scontistica.TotemContext;
import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.view.clienteframe.ClienteFrameDepRit;
import it.unipv.ingsfw.view.clienteframe.ClienteFrameDeposito;
import it.unipv.ingsfw.view.clienteframe.ClienteFrameLog;
import it.unipv.ingsfw.view.clienteframe.ClienteFrameReg;
import it.unipv.ingsfw.view.clienteframe.ClienteFrameRegLog;
import it.unipv.ingsfw.view.clienteframe.ClienteFrameRitiro;

public class TotemAction {

	private int costoInt;
	private Double costoDScontato;
	private Cliente cl; // Model
	private Totem t;
	private ClienteFrameLog cfl; // Login View
	private ClienteFrameReg cfr; // Registrazione View
	private ClienteFrameRegLog cfrl; // Main View
	private ClienteFrameDepRit cfdr; // Scelta tra Deposito-Ritiro View
	private ClienteFrameDeposito cfd; // Scelta servizio lavorazione (deposito capo)
	private ClienteFrameRitiro cfrit; // Ritiro capo
	private String sol = "";
	private Negozio sonr;
	private Negozio sond;
	private Date date;
	private String idc;

	public TotemAction(ClienteFrameRegLog cfrl) {
		this.cfrl = cfrl;
		t = new Totem();
		addLogRegListener();
	}

	public TotemAction(ClienteFrameDepRit cfdr) {
		this.cfdr = cfdr;
		t = new Totem();
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
		String passwordCorretta = "qwerty1234";

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
				if (cfrl.getBrlp().getRegButton().getActionCommand().equalsIgnoreCase("Reg")) {
					cfrl.dispose();
					ClienteFrameReg cfr = new ClienteFrameReg(t);
					cfr.setVisible(true);
				}
			}
		});
		cfrl.getBrlp().getSpegniButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cfrl.getBrlp().getSpegniButton().getActionCommand().equalsIgnoreCase("Spegni")) {
					richiediPassword(cfrl, passwordCorretta);
				}
			}
		});

	}

	private void richiediPassword(JFrame cfrl, String passwordCorretta) {
		JPasswordField passwordField = new JPasswordField();
		int option = JOptionPane.showConfirmDialog(cfrl, passwordField, "Inserisci Password",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (option == JOptionPane.OK_OPTION) {
			String passwordInserita = new String(passwordField.getPassword());
			if (passwordInserita.equals(passwordCorretta)) {
				cfrl.dispose();
			} else {
				JOptionPane.showMessageDialog(cfrl, "Password errata", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
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
				if (cfdr.getBdrp().getRitButton().getActionCommand().equalsIgnoreCase("Rit")) {
					cfdr.dispose();
					ClienteFrameRitiro cfrit = new ClienteFrameRitiro(t);
					cfrit.setVisible(true);
				}
			}
		});
	}

	private void addLoginListener() {
		cfl.getPannello().getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cfl.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {
					t.setCliente(new Cliente(cfl.getPannello().getUserText().getText(),
							cfl.getPannello().getPassText().getText()));
					boolean isValid = t.verificaCredenzialiAccesso();
					if (isValid == true) {
						cl = (F2aFacade.getInstance().getGestioneNegozioFacade()
								.selectClienteByEmailEPassword(t.getCliente()));
						JOptionPane.showMessageDialog(cfl, "Credenziali corrette" + "\n" + "Bentornato " + cl.getNome()
								+ "\n codice cliente" + cl.getIdCliente());
						cfl.dispose();
						ClienteFrameDepRit cfdr = new ClienteFrameDepRit();
						cfdr.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(cfl, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		cfl.getPannello().getEsciButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cfl.getPannello().getEsciButton().getActionCommand().equalsIgnoreCase("Logout")) {
					cfl.dispose();
					ClienteFrameRegLog cfrl = new ClienteFrameRegLog();
					cfrl.setVisible(true);
				}
			}
		});
	}

	private void addRegisterListener() {
		cfr.getPannello().getRegButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cfr.getPannello().getRegButton().getActionCommand().equalsIgnoreCase("Registrazione")) {

					if (t.cfValido(cfr.getPannello().getUserTextCf().getText()) == false) {
						JOptionPane.showMessageDialog(cfr, "Il codice fiscale non valido.", "Errore",
								JOptionPane.ERROR_MESSAGE);
						return; // Interrompe l'esecuzione se il CF non è valido
					}

					if (t.mailVuota(cfr.getPannello().getUserTextEmail().getText()) == true) {
						JOptionPane.showMessageDialog(cfr, "Il campo email è obbligatorio.", "Errore",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (t.pwValida(cfr.getPannello().getUserTextPw().getText()) == false) {
						JOptionPane.showMessageDialog(cfr,
								"Il campo password è obbligatorio. \nLa password deve essere di almeno 8 caratteri",
								"Errore", JOptionPane.ERROR_MESSAGE);
						return;
					}

					String idcl = (F2aFacade.getInstance().getGestioneNegozioFacade().getNewIdCliente());
					t.setCliente(new Cliente(idcl, cfr.getPannello().getUserTextNome().getText(),
							cfr.getPannello().getUserTextCognome().getText(),
							cfr.getPannello().getUserTextCf().getText(), cfr.getPannello().getUserTextEmail().getText(),
							cfr.getPannello().getUserTextPw().getText()));
					boolean isValid = F2aFacade.getInstance().getGestioneNegozioFacade().insertCliente(t.getCliente());

					if (isValid == true) {
						JOptionPane.showMessageDialog(cfr,
								"Registrazione effettuata" + "\n" + "Il suo codice cliente è: " + idcl);
						cfr.dispose();
						ClienteFrameLog cfl = new ClienteFrameLog(t);
						cfl.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(cfr, "registrazione non avvenuta", "Errore",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		cfr.getPannello().getEsciButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cfr.getPannello().getEsciButton().getActionCommand().equalsIgnoreCase("Logout")) {
					cfr.dispose();
					ClienteFrameRegLog cfrl = new ClienteFrameRegLog();
					cfrl.setVisible(true);
				}
			}
		});
	}

	private void addDepositoListener() {
		cfd.getPannello().getScegliNegDepButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (cfd.getPannello().getScegliNegDepButton().getActionCommand().equalsIgnoreCase("NegozioDep")) {
					ArrayList<Negozio> n1 = t.getNegoziAttivi();
					Negozio[] optionsArray = n1.toArray(new Negozio[0]);

					// Creazione del menu a tendina con JOptionPane
					Negozio selectedOption = (Negozio) JOptionPane.showInputDialog(null, // Finestra principale (null
																							// usa la finestra di
																							// default)
							"Conferma Negozio:", // Messaggio che appare sopra il menu a tendina
							"Negozi Disponibili", // Titolo della finestra
							JOptionPane.PLAIN_MESSAGE, // Tipo di finestra di dialogo (senza icona)
							null, // Icona (null per nessuna icona)
							optionsArray, // I valori delle opzioni nel menu a tendina
							optionsArray[0] // Il valore predefinito (la prima opzione)
					);
					sond = selectedOption;
				}
			}
		});
		cfd.getPannello().getScegliNegRitButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (cfd.getPannello().getScegliNegRitButton().getActionCommand().equalsIgnoreCase("NegozioRit")) {
					ArrayList<Negozio> n = t.getNegoziAttivi();
					Negozio[] optionsArray = n.toArray(new Negozio[0]);

					// Creazione del menu a tendina con JOptionPane
					Negozio selectedOption = (Negozio) JOptionPane.showInputDialog(null, // Finestra principale (null
																							// usa la finestra di
																							// default)
							"Scegli un negozio:", // Messaggio che appare sopra il menu a tendina
							"Negozi Disponibili", // Titolo della finestra
							JOptionPane.PLAIN_MESSAGE, // Tipo di finestra di dialogo (senza icona)
							null, // Icona (null per nessuna icona)
							optionsArray, // I valori delle opzioni nel menu a tendina
							optionsArray[0] // Il valore predefinito (la prima opzione)
					);
					sonr = selectedOption;
				}
			}
		});

		cfd.getPannello().getScegliLavButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (cfd.getPannello().getScegliLavButton().getActionCommand().equalsIgnoreCase("Lavaggio")) {
					HashMap<Integer, String> lav = t.getTipologiaLavaggi();
					String[] opzioniLavaggio = lav.values().toArray(new String[0]);

					// Creazione del menu a tendina con JOptionPane
					String selectedOption = (String) JOptionPane.showInputDialog(null, // Finestra principale (null usa
																						// la finestra di default)
							"Scegli un negozio:", // Messaggio che appare sopra il menu a tendina
							"Negozi Disponibili", // Titolo della finestra
							JOptionPane.PLAIN_MESSAGE, // Tipo di finestra di dialogo (senza icona)
							null, // Icona (null per nessuna icona)
							opzioniLavaggio, // I valori delle opzioni nel menu a tendina
							opzioniLavaggio[0] // Il valore predefinito (la prima opzione)
					);
					sol = selectedOption;
				}

			}
		});

		cfd.getPannello().getVerificaDatiButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cfd.getPannello().getVerificaDatiButton().getActionCommand().equalsIgnoreCase("Verifica")) {

					if (t.idValido(cfd.getPannello().getIdText().getText()) == false) {
						JOptionPane.showMessageDialog(cfd,
								"Il campo 'ID Cliente' è obbligatorio.\nL'ID cliente deve essere nel formato clXXX, dove XXX sono numeri.",
								"Errore", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (t.dataValida(cfd.getPannello().getDataText().getText()) == false) {
						JOptionPane.showMessageDialog(cfd,
								"La data inserita non è valida.\nLa data deve essere successiva di almeno 3 giorni rispetto a quella odierna.",
								"Errore", JOptionPane.ERROR_MESSAGE);
						return;
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					sdf.setLenient(false);
					try {
						date = sdf.parse(cfd.getPannello().getDataText().getText());
					} catch (ParseException ex) {
						JOptionPane.showMessageDialog(cfd, "La data inserita non è valida.", "Errore",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (sond == null) {
						JOptionPane.showMessageDialog(cfd, "Devi selezionare un negozio di deposito.", "Errore",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (sonr == null) {
						JOptionPane.showMessageDialog(cfd, "Devi selezionare un negozio di ritiro.", "Errore",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (sol == null || sol.isEmpty()) {
						JOptionPane.showMessageDialog(cfd, "Devi selezionare un tipo di lavaggio.", "Errore",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					String indirNegoRit = sonr.getIndirizzo();

					costoInt = F2aFacade.getInstance().getLavaggioFacade().getCostoLavaggio(sol);
					t.setTotemContext(new TotemContext(costoInt));
					// t.getTotemContext().setCosto(costoInt);
					costoDScontato = t.getTotemContext().getTotal();
					t.getTotemContext().getTotal();
					String costoSIntero = Integer.toString(costoInt);
					String costoSScontato = Double.toString(costoDScontato);

					JOptionPane.showMessageDialog(cfd,
							"Riepilogo dati: " + "\n" + " Codice cliente: " + cfd.getPannello().getIdText().getText()
									+ "\n Negozio ritiro: " + indirNegoRit + "\n" + "\n Data ultima ritiro: "
									+ cfd.getPannello().getDataText().getText() + "\n" + " Tipologia servizio: " + sol
									+ "\n COSTO INTERO: " + costoSIntero + "\n COSTO SCONTATO: " + costoSScontato);

				}
			}
		});

		cfd.getPannello().getPagaButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cfd.getPannello().getPagaButton().getActionCommand().equalsIgnoreCase("Paga")) {
					idc = (F2aFacade.getInstance().getCapoFacade().getNewIdCapo());
					TipoLavaggio sol1 = TipoLavaggio.valueOf(sol);
					StatoCapo s = StatoCapo.IN_STORE;
					t.setCliente(new Cliente(cfd.getPannello().getIdText().getText()));
					// double costoD = (double) costoInt;

					boolean result;
					try {
						result = t.generaPrenotazioneCapo(
								new Capo(idc, s, sol1, null, date, sonr, sond, costoDScontato, t.getCliente()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(cfd, "\n Codice capo: " + idc + "\n");

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

					if (t.idCapoValido(cfrit.getPannello().getIdcText().getText()) == false) {
						JOptionPane.showMessageDialog(cfrit,
								"ID capo non valido: deve essere nel formato CXXX (dove X è un numero).");
						return;
					}

					String statoCorrente = F2aFacade.getInstance().getCapoFacade()
							.getStatoCapoById(new Capo(cfrit.getPannello().getIdcText().getText()));
					if (statoCorrente.equalsIgnoreCase("CONSEGNATO")) {
						StatoCapo stato = StatoCapo.PRELEVATO;
						Boolean esito = F2aFacade.getInstance().getCapoFacade()
								.updateStatoCapo(new Capo(cfrit.getPannello().getIdcText().getText(), stato));
						if (esito) {
							JOptionPane.showMessageDialog(cfrit, "Può ritirare il capo :)");
						}
					} else {
						JOptionPane.showMessageDialog(cfrit,
								"STATO CAPO: " + statoCorrente + "\nImposibile effettuare ritiro \nSiamo spiacenti :(");
					}
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
