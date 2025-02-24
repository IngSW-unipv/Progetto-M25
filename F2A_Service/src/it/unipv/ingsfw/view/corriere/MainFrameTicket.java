package it.unipv.ingsfw.view.corriere;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

import it.unipv.ingsfw.controller.OperatoreAction;
import it.unipv.ingsfw.controller.TicketAction;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.tickets.Ticket;
import it.unipv.ingsfw.model.users.Corriere;
import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

/**
 * top-level Container--> contenitore principale, mi permette di aggiungere
 * altri componenti --> visualizza la finestra dell'interfaccia --> contiene
 * anche riferimenti alle classi che gestisccono eventi --> finestra di max
 * livello visualizzata direttamente da S.O.
 **/
public class MainFrameTicket extends JFrame {
	// componenti:
	private BarraMenu menu;
	private JPanel pannello;
	private JPanel pannelloTicket;
	private JPanel pannelloProfilo;
	private Container c;
	// dichiarare final le variabili a cui si accede con i listeners
	// private final List<JButton> bottoni = new ArrayList<>();
	private ButtonGroup group;
	private Ticket tik;
	private CardLayout cardLayout;
	private JButton bottoneScelta;
	private JButton bottonePresaCaricoTicket;

	public JButton getBottonePresaCaricoTicket() {
		return bottonePresaCaricoTicket;
	}

	public void setBottonePresaCaricoTicket(JButton bottonePresaCaricoTicket) {
		this.bottonePresaCaricoTicket = bottonePresaCaricoTicket;
	}

	// componenti per visualizzare le informazioni del ticket
	private JLabel labelId;
	private JTextArea fieldId;
	private JLabel labelTipologia;
	private JLabel labelMezzoAssociato;
	private JTextArea fieldMezzoAssociato;
	private JTextArea fieldTipologia;
	private JLabel labelItinerario;
	private JTextArea areaItinerario;

	/**
	 * ad ogni componentebisogna associare un Listener per il relativo evento in
	 * modo che interagendoci succeda qualcosa. Associo i listeners ai vari
	 * componenti (con metodi specifici del componente)
	 **/

	public MainFrameTicket(Ticket tik) throws HeadlessException {
		super();
		this.tik = tik;

		// Sto ottenendo il default di qualcosa, ovvero è un factory/singleton
		// ---porzione di codice che c'entra il frame-----
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, (int) (screenHeight / (1.3)));
		setLocation(screenWidth / 4, screenHeight / 10);
		Image img = kit.getImage("properties/Logo_F2A_zoom.PNG");
		setIconImage(img);
		setTitle("MainFrame Corriere");
		// ------------------------------------------------
		// layout del panel che mi permette di "sovrapporre" altri componenti a
		// "finestra"
		cardLayout = new CardLayout();
		// istanzio il pannello nel costruttore del frame
		pannello = new JPanel(cardLayout);

		bottoneScelta = new JButton("Visualizza Ticket Assegnati");
		bottoneScelta.setActionCommand("Visualizza Ticket Assegnati");
		
		// Creazione pannelli
		pannelloTicket = new JPanel();
		pannelloTicket.setLayout(new BoxLayout(pannelloTicket, BoxLayout.Y_AXIS));
		pannelloTicket.add(bottoneScelta, BorderLayout.WEST);
		// aggiungo bottone utile a prendere in carico il ticket
		bottonePresaCaricoTicket = new JButton("Prendi In Carico");
		bottonePresaCaricoTicket.setActionCommand("Prendi In Carico");
		// inizialmente il bottone non viene mostrato
		bottonePresaCaricoTicket.setVisible(false);
		// verrà mostrato solo dopo aver scelto il ticket dal menu a tendina
		pannelloTicket.add(bottonePresaCaricoTicket, BorderLayout.EAST);
		pannelloProfilo = new JPanel();
		pannelloProfilo.add(new JLabel("Gestione Profilo - In costruzione"));

		// Aggiunta pannelli al CardLayout
		pannello.add(pannelloTicket, "Ticket");
		pannello.add(pannelloProfilo, "Profilo");

		// Creazione menu personalizzato
		menu = new BarraMenu();
		setJMenuBar(menu);

		// restituisce la reference del JFrame
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(menu, BorderLayout.NORTH);
		c.add(pannello, BorderLayout.WEST);

		// creo i componenti per visualizzare le informazioni del ticket
		labelId = new JLabel("ID Ticket:");
		labelId.setVisible(false);
		fieldId = new JTextArea(2, 10);
		fieldId.setVisible(false);
		fieldId.setEditable(false);
		labelTipologia = new JLabel("Tipologia:");
		labelTipologia.setVisible(false);
		fieldTipologia = new JTextArea(2, 10);
		fieldTipologia.setVisible(false);
		fieldTipologia.setEditable(false);
		labelItinerario = new JLabel("Itinerario:");
		labelItinerario.setVisible(false);
		areaItinerario = new JTextArea(3, 15);
		areaItinerario.setVisible(false);
		areaItinerario.setEditable(false);
		labelMezzoAssociato = new JLabel("Mezzo:");
		labelMezzoAssociato.setVisible(false);
		fieldMezzoAssociato = new JTextArea(2, 10);
		fieldMezzoAssociato.setVisible(false);
		fieldMezzoAssociato.setEditable(false);

		// aggiungo i componenti al pannello
		pannelloTicket.add(labelId);
		pannelloTicket.add(fieldId);
		pannelloTicket.add(labelTipologia);
		pannelloTicket.add(fieldTipologia);
		pannelloTicket.add(labelItinerario);
		pannelloTicket.add(areaItinerario);
		pannelloTicket.add(labelMezzoAssociato);
		pannelloTicket.add(fieldMezzoAssociato);
		new TicketAction(tik, this);
	}


	public JLabel getLabelItinerario() {
		return labelItinerario;
	}

	public void setLabelItinerario(JLabel labelItinerario) {
		this.labelItinerario = labelItinerario;
	}

	public JTextArea getAreaItinerario() {
		return areaItinerario;
	}

	public void setAreaItinerario(JTextArea areaItinerario) {
		this.areaItinerario = areaItinerario;
	}

	public JLabel getLabelTipologia() {
		return labelTipologia;
	}

	public void setLabelTipologia(JLabel labelTipologia) {
		this.labelTipologia = labelTipologia;
	}

	public JLabel getLabelMezzoAssociato() {
		return labelMezzoAssociato;
	}

	public void setLabelMezzoAssociato(JLabel labelMezzoAssociato) {
		this.labelMezzoAssociato = labelMezzoAssociato;
	}

	public JTextArea getFieldMezzoAssociato() {
		return fieldMezzoAssociato;
	}

	public void setFieldMezzoAssociato(JTextArea fieldMezzoAssociato) {
		this.fieldMezzoAssociato = fieldMezzoAssociato;
	}

	public JTextArea getFieldTipologia() {
		return fieldTipologia;
	}

	public void setFieldTipologia(JTextArea fieldTipologia) {
		this.fieldTipologia = fieldTipologia;
	}

	public JLabel getLabelId() {
		return labelId;
	}

	public void setLabelId(JLabel labelId) {
		this.labelId = labelId;
	}

	public JTextArea getFieldId() {
		return fieldId;
	}

	public void setFieldId(JTextArea fieldId) {
		this.fieldId = fieldId;
	}

	public JLabel getLabelDescrizione() {
		return labelItinerario;
	}

	public void setLabelDescrizione(JLabel labelDescrizione) {
		this.labelItinerario = labelDescrizione;
	}

	public JTextArea getAreaDescrizione() {
		return areaItinerario;
	}

	public void setAreaDescrizione(JTextArea areaDescrizione) {
		this.areaItinerario = areaDescrizione;
	}

	public Ticket getTik() {
		return tik;
	}

	public void setTik(Ticket tik) {
		this.tik = tik;
	}

	public JButton getBottoneScelta() {
		return bottoneScelta;
	}

	public void setBottoneScelta(JButton bottoneScelta) {
		this.bottoneScelta = bottoneScelta;
	}

	public void setPannelloTicket(JPanel pannelloTicket) {
		this.pannelloTicket = pannelloTicket;
	}

	public BarraMenu getMenu() {
		return menu;
	}

	public void setMenu(BarraMenu menu) {
		this.menu = menu;
	}

	public JPanel getPannello() {
		return pannello;
	}

	public void setPannello(JPanel pannello) {
		this.pannello = pannello;
	}

	public JPanel getPannelloTicket() {
		return pannelloTicket;
	}

	public void setPannelloStazioni(JPanel pannelloTicket) {
		this.pannelloTicket = pannelloTicket;
	}

	public JPanel getPannelloProfilo() {
		return pannelloProfilo;
	}

	public void setPannelloProfilo(JPanel pannelloProfilo) {
		this.pannelloProfilo = pannelloProfilo;
	}

	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	/**
	 * public List<JButton> getBottoni() { return bottoni; }
	 **/

	/**
	 * private void updateButtonColor(JButton button, StatoStazione stato) { Color
	 * color; switch (stato) { case READY -> color = Color.GREEN; case MAINTENANCE
	 * -> color = Color.ORANGE; case WORKING -> color = Color.RED; default -> color
	 * = Color.GRAY; } button.setBackground(color); }
	 **/

	/**
	 * public void aggiornaStazioni() { ObservableStazioneLavoroDAO staz = new
	 * ObservableStazioneLavoroDAO(); ArrayList<ObservableStazioneLavoro> stazioni =
	 * staz.selectStazioniByOperatore(this.op);
	 * 
	 * pannelloStazioni.removeAll(); bottoni.clear(); group = new ButtonGroup();
	 * JLabel noStazioni;
	 * 
	 * for (ObservableStazioneLavoro stazione : stazioni) { JPanel panel = new
	 * JPanel(); JLabel label = new JLabel("Stazione " + stazione.getIdStazione());
	 * JButton button = new JButton(); button.setPreferredSize(new Dimension(30,
	 * 30));
	 * 
	 * updateButtonColor(button, stazione.getStatoStazione()); bottoni.add(button);
	 * panel.add(label); panel.add(button); group.add(button);
	 * pannelloStazioni.add(panel); button.setEnabled(true); }
	 * 
	 * if (stazioni.size() == 0) { JPanel panelNoStazioni = new JPanel(); noStazioni
	 * = new JLabel("NESSUNA STAZIONE ASSEGNATA, AGGIORNA LA PAGINA");
	 * panelNoStazioni.add(noStazioni); pannelloStazioni.add(panelNoStazioni); }
	 * 
	 * pannelloStazioni.revalidate(); pannelloStazioni.repaint(); }
	 **/

	public static void main(String[] args) {
		Ticket tik = new Ticket();
		// evita conflitti quando uso view diverse
		SwingUtilities.invokeLater(() -> {
			MainFrameTicket loginOp = new MainFrameTicket(tik);
			loginOp.setDefaultCloseOperation(EXIT_ON_CLOSE);
			loginOp.setVisible(true);
		});
	}
}