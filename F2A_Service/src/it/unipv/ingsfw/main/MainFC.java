package it.unipv.ingsfw.main;

import java.util.Scanner;

import javax.swing.JFrame;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class MainFC {

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
}
