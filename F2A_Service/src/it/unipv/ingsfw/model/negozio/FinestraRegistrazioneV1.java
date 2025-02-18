package it.unipv.ingsfw.model.negozio;

import javax.swing.*;

import it.unipv.ingsfw.model.users.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraRegistrazioneV1 {

    public void showRegistrazione() {
        // Creiamo una nuova finestra
        JFrame finestraRegistrazione = new JFrame("Registrazione Cliente");
        finestraRegistrazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        finestraRegistrazione.setSize(300, 250);
        finestraRegistrazione.setLayout(new GridLayout(6, 2)); // Creiamo una griglia con 6 righe e 2 colonne

        // Creiamo i campi di input
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        JLabel lblCognome = new JLabel("Cognome:");
        JTextField txtCognome = new JTextField();
        JLabel lblNumero = new JLabel("Numero:");
        JTextField txtNumero = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        // Creiamo il pulsante di registrazione
        JButton btnRegistrati = new JButton("Registrati");

        // Aggiungiamo i componenti alla finestra
        finestraRegistrazione.add(lblNome);
        finestraRegistrazione.add(txtNome);
        finestraRegistrazione.add(lblCognome);
        finestraRegistrazione.add(txtCognome);
        finestraRegistrazione.add(lblNumero);
        finestraRegistrazione.add(txtNumero);
        finestraRegistrazione.add(lblEmail);
        finestraRegistrazione.add(txtEmail);
        finestraRegistrazione.add(lblPassword);
        finestraRegistrazione.add(txtPassword);
        finestraRegistrazione.add(btnRegistrati);

        // Azione per il pulsante di registrazione
        btnRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Recuperiamo i dati dai campi
                String nome = txtNome.getText();
                String cognome = txtCognome.getText();
                String numero = txtNumero.getText();
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());

                //Creiamo idCliente con metodo di totem
                String idCliente = Totem.creaIdCliente(nome,cognome,numero);

                // Creiamo il cliente con metodo di totem
                Cliente cliente = Totem.creaCliente (idCliente, nome, cognome, numero, email, password);


                // Visualizziamo i dati del cliente
                JOptionPane.showMessageDialog(finestraRegistrazione, "Cliente creato: \n"
                        +"ID Cliente: " + cliente.getIdCliente()+"\n"
                        +"Nome: " + cliente.getNome()+"\n"
                        +"Cognome: " + cliente.getCognome()+"\n"
                        +"Numero telefonico: " + cliente.getTelefono()+"\n"
                        +"Email: " + cliente.getEmail()+"\n");

                // Chiudiamo la finestra di registrazione
                finestraRegistrazione.dispose();
                // Apriamo la finestra di login
                new FinestraLoginV1().showLogin();
            }
        });

        // Posiziona finestra al centro
        finestraRegistrazione.setLocationRelativeTo(null);

        // Visualizziamo la finestra
        finestraRegistrazione.setVisible(true);
    }
}
