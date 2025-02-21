package it.unipv.ingsfw.view.operatore;

import java.util.Scanner;

import javax.swing.JFrame;

import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		int i = 0;
		Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci numero di GUI login da aprire:");
        int n = scanner.nextInt();
	        while (i < n) { // ciclo per accettare nuovi login
	        	GUILoginOperatore.startGUI();
	            i++;
	        }
	   scanner.close();
	}
// GUILoginOperatore loginOp = new GUILoginOperatore(op);
// loginOp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// loginOp.setVisible(true);

}
