package it.unipv.ingsfw.model.negozio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraLoginV1 {

    public void showLogin() {
        // Crea la finestra di login
        JFrame finestraLogin = new JFrame("Login");

        // Impostiamo la chiusura della finestra
        finestraLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        finestraLogin.setSize(300, 200);
        finestraLogin.setLayout(new GridLayout(3, 2));

        // Etichette per i campi di testo
        JLabel lblUsername = new JLabel("ID CLiente:");
        JLabel lblPassword = new JLabel("Password:");

        // Campi di testo per l'username e la password
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();

        // Pulsante per il login
        JButton btnLogin = new JButton("Login");

        // Aggiungiamo gli elementi al pannello della finestra
        finestraLogin.add(lblUsername);
        finestraLogin.add(txtUsername);
        finestraLogin.add(lblPassword);
        finestraLogin.add(txtPassword);
        finestraLogin.add(new JLabel()); // Spazio vuoto
        finestraLogin.add(btnLogin);

        // Azione per il pulsante di login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtUsername.getText();
                char[] password = txtPassword.getPassword();

                

                
                //Da implementare logica controllo dati database
                
                
                
                
                
                
                if (nome.equals("admin") && new String(password).equals("password")) {
                    JOptionPane.showMessageDialog(finestraLogin, "Login riuscito!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    finestraLogin.dispose(); // Chiudi la finestra di login
                    
                    
                    
                    
                    
                    // Qui  aprire una nuova finestra dopo il login, RitiroDepositoCapi dell'utente
                    // new RitiroDepositoCapi().showMainWindow();
                    
                    
                    
                    
                    
                    
                    
                    
                } else {
                    JOptionPane.showMessageDialog(finestraLogin, "Credenziali errate. Riprova.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Posiziona la finestra al centro dello schermo
        finestraLogin.setLocationRelativeTo(null);

        // Visualizza la finestra
        finestraLogin.setVisible(true);
    }
}

