package it.unipv.ingsfw.view.operatore;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

import it.unipv.ingsfw.controller.ManutentoreAction;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.users.Operatore;

public class MainFrameManutentore extends JFrame implements Runnable{
	private BarraMenu menu;
	private JPanel pannello;
	private JPanel pannelloStazioni;
	private JPanel pannelloGuasti;
	private JPanel pannelloProfilo;
	private Container c;
	private final List<JButton> bottoni = new ArrayList<>();
	private ButtonGroup group;
	private Operatore op;

	private CardLayout cardLayout;

	public MainFrameManutentore(Operatore o) throws HeadlessException {
		super();
		this.op = o;

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		Image img = kit.getImage("properties/Logo_F2A_zoom.PNG");
		setIconImage(img);
		setTitle("MainFrame manutentore");

		cardLayout = new CardLayout();
		pannello = new JPanel(cardLayout);

		// Creazione pannelli
		pannelloGuasti = new JPanel();
		pannelloGuasti.setLayout(new BoxLayout(pannelloGuasti, BoxLayout.Y_AXIS));
		
		pannelloStazioni = new JPanel();
		pannelloStazioni.add(new JLabel("GESTIONE STAZIONI LAVORO NON DISPONIBILE"));
		

		pannelloProfilo = new JPanel();
		pannelloProfilo.add(new JLabel("Gestione Profilo - In costruzione"));

		// Aggiunta pannelli al CardLayout
		pannello.add(pannelloGuasti, "Macchinari");
		pannello.add(pannelloStazioni, "Stazioni");
		pannello.add(pannelloProfilo, "Profilo");

		// Creazione menu personalizzato
		menu = new BarraMenu();
		setJMenuBar(menu);

		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(menu, BorderLayout.NORTH);
		c.add(pannello, BorderLayout.WEST);

		// aggiornaStazioni();
		new ManutentoreAction(o, this);
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

	public JPanel getPannelloStazioni() {
		return pannelloStazioni;
	}

	public void setPannelloStazioni(JPanel pannelloStazioni) {
		this.pannelloStazioni = pannelloStazioni;
	}

	public JPanel getPannelloGuasti() {
		return pannelloGuasti;
	}

	public void setPannelloGuasti(JPanel pannelloGuasti) {
		this.pannelloGuasti = pannelloGuasti;
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

	public Operatore getOp() {
		return op;
	}

	public void setOp(Operatore op) {
		this.op = op;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public List<JButton> getBottoni() {
		return bottoni;
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

	public void aggiornaStazioni() {
		ObservableStazioneLavoroDAO staz = new ObservableStazioneLavoroDAO();
		ArrayList<ObservableStazioneLavoro> stazioni = staz.selectStazioniByOperatore(this.op);

		pannelloGuasti.removeAll();
		bottoni.clear();
		group = new ButtonGroup();
		JLabel noStazioni;

		for (ObservableStazioneLavoro stazione : stazioni) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Stazione " + stazione.getIdStazione());
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(30, 30));

			updateButtonColor(button, stazione.getStatoStazione());
			bottoni.add(button);
			panel.add(label);
			panel.add(button);
			group.add(button);
			pannelloGuasti.add(panel);
			button.setEnabled(true);
		}

		if (stazioni.size() == 0) {
			JPanel panelNoStazioni = new JPanel();
			noStazioni = new JLabel("NESSUNA STAZIONE ASSEGNATA, AGGIORNA LA PAGINA");
			panelNoStazioni.add(noStazioni);
			pannelloGuasti.add(panelNoStazioni);
		}

		pannelloGuasti.revalidate();
		pannelloGuasti.repaint();
	}


	public static void main(String[] args) {
		Operatore op = new Operatore();
		SwingUtilities.invokeLater(() -> {
			MainFrameManutentore loginOp = new MainFrameManutentore(op);
			loginOp.setDefaultCloseOperation(EXIT_ON_CLOSE);
			loginOp.setVisible(true);
		});
	}

	@Override
	public void run() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
		
	}
	
	public static void startMainFrameManutentore(Operatore operatore) {
        Thread t = new Thread(new MainFrameManutentore(operatore));
        t.start();
    }
}