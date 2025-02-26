package it.unipv.ingsfw.view.corriere;

import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.controller.TicketAction;
import it.unipv.ingsfw.model.tickets.Ticket;
/**
 * top-level Container--> contenitore principale, mi permette di aggiungere
 * altri componenti --> visualizza la finestra dell'interfaccia --> contiene
 * anche riferimenti alle classi che gestisccono eventi --> finestra di max
 * livello visualizzata direttamente da S.O.
 **/
public class FrameSvolgimentoTicket extends JFrame {
	// componenti:
	//private BarraMenu menu;
	private JPanel pannello;
	private JPanel pannelloTicket;
	private Container c;
	// dichiarare final le variabili a cui si accede con i listeners
	// private final List<JButton> bottoni = new ArrayList<>();
	private ButtonGroup group;
	private Ticket tik;
	private CardLayout cardLayout;
	private JButton bottoneRitiroAvvenuto;
	private JButton bottoneConsegnaAvvenuta;
	private JButton bottoneCompletaTicket;
	// componenti per visualizzare le informazioni del ticket
	private JTextArea fieldId;
	private JLabel labelFieldId;
	private JTextArea fieldTappaCorrente;
	private JLabel labelTappaCorrente;
	private JLabel labelCapiDaRitirarePressoTappa;
	private JLabel labelCapiDaConsegnarePressoTappa;
	private JTextArea fieldListaCapiDaRitirarePressoTappa;
	private JTextArea fieldListaCapiDaConsegnarePressoTappa;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;

	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public JScrollPane getScrollPane2() {
		return scrollPane2;
	}


	public void setScrollPane2(JScrollPane scrollPane2) {
		this.scrollPane2 = scrollPane2;
	}


	/**
	 * ad ogni componentebisogna associare un Listener per il relativo evento in
	 * modo che interagendoci succeda qualcosa. Associo i listeners ai vari
	 * componenti (con metodi specifici del componente)
	 **/

	public FrameSvolgimentoTicket(Ticket tik) throws HeadlessException {
		super();
		this.tik = tik;

		// Sto ottenendo il default di qualcosa, ovvero è un factory/singleton
		// ---porzione di codice che c'entra il frame-----
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		Image img = kit.getImage("properties/Logo_F2A_zoom.PNG");
		setIconImage(img);
		setTitle("FrameSvolgimentoTicket");
		// ------------------------------------------------
		// layout del panel che mi permette di "sovrapporre" altri componenti a
		// "finestra"
		cardLayout = new CardLayout();
		// istanzio il pannello nel costruttore del frame
		pannello = new JPanel(cardLayout);

		// Creazione pannelli
		pannelloTicket = new JPanel();
		pannelloTicket.setLayout(new BoxLayout(pannelloTicket, BoxLayout.Y_AXIS));

		// Aggiunta pannelli al CardLayout
		pannello.add(pannelloTicket, "Ticket");

		// Creazione menu personalizzato
		//menu = new BarraMenu();
		setJMenuBar(null);

		// restituisce la reference del JFrame
		c = getContentPane();
		c.setLayout(new BorderLayout());
		//c.add(menu, BorderLayout.NORTH);
		c.add(pannello, BorderLayout.CENTER);


		labelFieldId= new JLabel("TICKET IN SVOLGIMENTO: ");
		fieldId = new JTextArea(2, 10);
		labelTappaCorrente= new JLabel("Tappa Corrente: ");
		fieldTappaCorrente = new JTextArea(2, 3);
		pannelloTicket.add(labelFieldId);
		pannelloTicket.add(fieldId);
		pannelloTicket.add(labelTappaCorrente);
		pannelloTicket.add(fieldTappaCorrente);
		labelCapiDaRitirarePressoTappa= new JLabel("Capi Da Ritirare: ");
		labelCapiDaConsegnarePressoTappa= new JLabel("Capi Da Consegnare: ");
		fieldListaCapiDaRitirarePressoTappa=new JTextArea (10,20);
		fieldListaCapiDaConsegnarePressoTappa=new JTextArea (10,20);
		pannelloTicket.add(labelCapiDaRitirarePressoTappa);
		pannelloTicket.add(labelCapiDaConsegnarePressoTappa);
		pannelloTicket.add(fieldListaCapiDaRitirarePressoTappa);
		pannelloTicket.add(fieldListaCapiDaConsegnarePressoTappa);
		//inizialmente non visibili
		fieldListaCapiDaRitirarePressoTappa.setVisible(false);
		fieldListaCapiDaConsegnarePressoTappa.setVisible(false);
		labelCapiDaRitirarePressoTappa.setVisible(false);
		labelCapiDaConsegnarePressoTappa.setVisible(false);
		scrollPane = new JScrollPane(fieldListaCapiDaRitirarePressoTappa);
		scrollPane2 = new JScrollPane(fieldListaCapiDaConsegnarePressoTappa);
		pannelloTicket.add(scrollPane, BorderLayout.CENTER);
		pannelloTicket.add(scrollPane2, BorderLayout.CENTER);
		scrollPane.setVisible(false);
		scrollPane2.setVisible(false);
		bottoneRitiroAvvenuto = new JButton("Ritiro presso tappa corrente avvenuto");
		bottoneRitiroAvvenuto.setActionCommand("Ritiro presso tappa corrente avvenuto");
		bottoneConsegnaAvvenuta = new JButton("Consegna presso tappa corrente avvenuta");
		bottoneConsegnaAvvenuta.setActionCommand("Consegna presso tappa corrente avvenuta");
		bottoneCompletaTicket = new JButton("Completa Ticket, Torna Alla Home");
		bottoneCompletaTicket.setActionCommand("Completa Ticket");
		pannelloTicket.add(bottoneRitiroAvvenuto);
		pannelloTicket.add(bottoneConsegnaAvvenuta);
		pannelloTicket.add(bottoneCompletaTicket);
		//textArea.setPreferredSize(new Dimension(600, 400));
		//inizialmente entrambi non visibili, fatti vedere in base al fatto che il ticket è di tipo consegna o di tipo ritiro
		bottoneRitiroAvvenuto.setVisible(false);
		bottoneConsegnaAvvenuta.setVisible(false);
		bottoneCompletaTicket.setVisible(false);
		new TicketAction(tik, this);
	}
	

	public JButton getBottoneCompletaTicket() {
		return bottoneCompletaTicket;
	}


	public void setBottoneCompletaTicket(JButton bottoneCompletaTicket) {
		this.bottoneCompletaTicket = bottoneCompletaTicket;
	}


	public JLabel getLabelFieldId() {
		return labelFieldId;
	}


	public void setLabelFieldId(JLabel labelFieldId) {
		this.labelFieldId = labelFieldId;
	}


	public JLabel getLabelTappaCorrente() {
		return labelTappaCorrente;
	}


	public void setLabelTappaCorrente(JLabel labelTappaCorrente) {
		this.labelTappaCorrente = labelTappaCorrente;
	}


	public JLabel getLabelCapiDaRitirarePressoTappa() {
		return labelCapiDaRitirarePressoTappa;
	}


	public void setLabelCapiDaRitirarePressoTappa(JLabel labelCapiDaRitirarePressoTappa) {
		this.labelCapiDaRitirarePressoTappa = labelCapiDaRitirarePressoTappa;
	}


	public JLabel getLabelCapiDaConsegnarePressoTappa() {
		return labelCapiDaConsegnarePressoTappa;
	}


	public void setLabelCapiDaConsegnarePressoTappa(JLabel labelCapiDaConsegnarePressoTappa) {
		this.labelCapiDaConsegnarePressoTappa = labelCapiDaConsegnarePressoTappa;
	}


	public JButton getBottoneRitiroAvvenuto() {
		return bottoneRitiroAvvenuto;
	}


	public void setBottoneRitiroAvvenuto(JButton bottoneRitiroAvvenuto) {
		this.bottoneRitiroAvvenuto = bottoneRitiroAvvenuto;
	}


	public JButton getBottoneConsegnaAvvenuta() {
		return bottoneConsegnaAvvenuta;
	}


	public void setBottoneConsegnaAvvenuta(JButton bottoneConsegnaAvvenuta) {
		this.bottoneConsegnaAvvenuta = bottoneConsegnaAvvenuta;
	}


	public JTextArea getFieldTappaCorrente() {
		return fieldTappaCorrente;
	}


	public void setFieldTappaCorrente(JTextArea fieldTappaCorrente) {
		this.fieldTappaCorrente = fieldTappaCorrente;
	}


	public JTextArea getFieldListaCapiDaRitirarePressoTappa() {
		return fieldListaCapiDaRitirarePressoTappa;
	}


	public void setFieldListaCapiDaRitirarePressoTappa(JTextArea fieldListaCapiDaRitirarePressoTappa) {
		this.fieldListaCapiDaRitirarePressoTappa = fieldListaCapiDaRitirarePressoTappa;
	}


	public JTextArea getFieldListaCapiDaConsegnarePressoTappa() {
		return fieldListaCapiDaConsegnarePressoTappa;
	}


	public void setFieldListaCapiDaConsegnarePressoTappa(JTextArea fieldListaCapiDaConsegnarePressoTappa) {
		this.fieldListaCapiDaConsegnarePressoTappa = fieldListaCapiDaConsegnarePressoTappa;
	}


	public JTextArea getFieldId() {
		return fieldId;
	}

	public void setFieldId(JTextArea fieldId) {
		this.fieldId = fieldId;
	}

	public Ticket getTik() {
		return tik;
	}

	public void setTik(Ticket tik) {
		this.tik = tik;
	}

	public void setPannelloTicket(JPanel pannelloTicket) {
		this.pannelloTicket = pannelloTicket;
	}
/**
	public BarraMenu getMenu() {
		return menu;
	}

	public void setMenu(BarraMenu menu) {
		this.menu = menu;
	}
**/
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
			FrameSvolgimentoTicket loginOp = new FrameSvolgimentoTicket(tik);
			loginOp.setDefaultCloseOperation(EXIT_ON_CLOSE);
			loginOp.setVisible(true);
		});
	}
}