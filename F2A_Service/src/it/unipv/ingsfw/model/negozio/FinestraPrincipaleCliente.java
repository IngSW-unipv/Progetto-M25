package it.unipv.ingsfw.model.negozio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraPrincipaleCliente {

    public static void main(String[] args) {
        JFrame finestraIniziale = new JFrame("Benvenuti in F2A");

        // Impostiamo la chiusura della finestra
        finestraIniziale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraIniziale.setSize(300, 200);
        finestraIniziale.setLayout(new BorderLayout());

        // Creiamo i pulsanti di Login e Registrazione
        JButton btnLogin = new JButton("Login");
        JButton btnRegistrazione = new JButton("Registrazione");

        // Creiamo un pannello per i pulsanti
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1)); // Due pulsanti, uno sopra l'altro
        panel.add(btnLogin);
        panel.add(btnRegistrazione);

        // Aggiungiamo il pannello alla finestra principale
        finestraIniziale.add(panel, BorderLayout.CENTER);

        // Azione per il pulsante di registrazione
        btnRegistrazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestraIniziale.dispose();
                // Apre la finestra di registrazione
                new FinestraRegistrazioneV1().showRegistrazione();
            }
        });

        // Azione per il pulsante di login 
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestraIniziale.dispose();
                //Apre la finestra di login
                new FinestraLoginV1().showLogin();
            }
        });

        // Posiziona finestra al centro
        finestraIniziale.setLocationRelativeTo(null);

        // Visualizziamo la finestra
        finestraIniziale.setVisible(true);
    }
}
