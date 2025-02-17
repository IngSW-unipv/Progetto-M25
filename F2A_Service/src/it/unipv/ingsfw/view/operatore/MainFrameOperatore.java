package it.unipv.ingsfw.view.operatore;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import it.unipv.ingsfw.controller.OperatoreAction;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoro;
import it.unipv.ingsfw.model.lavorazioneCapi.ObservableStazioneLavoroDAO;
import it.unipv.ingsfw.model.lavorazioneCapi.StatoStazione;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class MainFrameOperatore extends JFrame {
    private BarraMenu menu;
    private JPanel pannello;
    private JPanel pannelloStazioni;
    private JPanel pannelloMacchinari;
    private JPanel pannelloProfilo;
    private Container c;
    private final List<JButton> bottoni = new ArrayList<>();
    private ButtonGroup group;
    private Operatore op;
    
    private CardLayout cardLayout;

    public MainFrameOperatore(Operatore o) throws HeadlessException {
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
        setTitle("MainFrame");

        cardLayout = new CardLayout();
        pannello = new JPanel(cardLayout);

        // Creazione pannelli
        pannelloStazioni = new JPanel();
        pannelloStazioni.setLayout(new BoxLayout(pannelloStazioni, BoxLayout.Y_AXIS));

        pannelloMacchinari = new JPanel();
        pannelloMacchinari.add(new JLabel("Gestione Macchinari - In costruzione"));

        pannelloProfilo = new JPanel();
        pannelloProfilo.add(new JLabel("Modifica Profilo - In costruzione"));

        // Aggiunta pannelli al CardLayout
        pannello.add(pannelloStazioni, "Stazioni");
        pannello.add(pannelloMacchinari, "Macchinari");
        pannello.add(pannelloProfilo, "Profilo");

        // Creazione menu personalizzato
        menu = new BarraMenu();
        setJMenuBar(menu);

        c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(menu, BorderLayout.NORTH);
        c.add(pannello, BorderLayout.WEST);

        aggiornaStazioni();
        new OperatoreAction(o, this);
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

	public JPanel getPannelloMacchinari() {
		return pannelloMacchinari;
	}

	public void setPannelloMacchinari(JPanel pannelloMacchinari) {
		this.pannelloMacchinari = pannelloMacchinari;
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

        pannelloStazioni.removeAll();
        bottoni.clear();
        group = new ButtonGroup();

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
            pannelloStazioni.add(panel);
        }

        pannelloStazioni.revalidate();
        pannelloStazioni.repaint();
    }

    public static void main(String[] args) {
        Operatore op = new Operatore();
        SwingUtilities.invokeLater(() -> {
            MainFrameOperatore loginOp = new MainFrameOperatore(op);
            loginOp.setDefaultCloseOperation(EXIT_ON_CLOSE);
            loginOp.setVisible(true);
        });
    }
}