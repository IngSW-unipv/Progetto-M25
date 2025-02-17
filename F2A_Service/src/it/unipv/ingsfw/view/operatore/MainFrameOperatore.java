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
import it.unipv.ingsfw.view.operatore.viewLogin.InsertPanel;

public class MainFrameOperatore extends JFrame {
    BarraMenu menu;
    JPanel pannello;
    Container c;
    private final List<JButton> bottoni = new ArrayList<>();
    private final List<JPanel> indicatori = new ArrayList<>();
    ButtonGroup group;

    public MainFrameOperatore(Operatore o) throws HeadlessException {
    	super();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);
        Image img = kit.getImage("properties/Logo_F2A_zoom.PNG");
        setIconImage(img);
        setTitle("MainFrame");

        menu = new BarraMenu();
        setJMenuBar(menu);

        pannello = new JPanel();
        pannello.setLayout(new BoxLayout(pannello, BoxLayout.Y_AXIS));
        c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(menu, BorderLayout.NORTH);
        c.add(pannello, BorderLayout.WEST);
        
        aggiornaStazioni(o);

        OperatoreAction opAction1 = new OperatoreAction(o, this);
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



	public void setPannello(StationsPanel pannello) {
		this.pannello = pannello;
	}



	public Container getC() {
		return c;
	}



	public void setC(Container c) {
		this.c = c;
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

	 public void aggiornaStazioni(Operatore op) {
	        ObservableStazioneLavoroDAO staz = new ObservableStazioneLavoroDAO();
	        ArrayList<ObservableStazioneLavoro> stazioni = staz.selectStazioniByOperatore(op);
	        
	        pannello.removeAll();
	        bottoni.clear();
	        group = new ButtonGroup(); 

	        for (ObservableStazioneLavoro stazione : stazioni) {
	            JPanel panel = new JPanel();
	            //panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	            
	            JLabel label = new JLabel("Stazione " + stazione.getIdStazione());
	            JButton button = new JButton();
	            button.setPreferredSize(new Dimension(30, 30));
	            
	            updateButtonColor(button, stazione.getStatoStazione());
	            bottoni.add(button);
	            panel.add(label);
	            panel.add(button);
	            pannello.add(panel);
	            
	            stazione.addObserver((o, arg) -> updateButtonColor(button, stazione.getStatoStazione()));
	        }

	        pannello.revalidate();
	        pannello.repaint();
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
