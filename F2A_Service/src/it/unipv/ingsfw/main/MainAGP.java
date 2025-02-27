package it.unipv.ingsfw.main;

import javax.swing.JFrame;

import it.unipv.ingsfw.view.corriere.viewLogin.GUILoginCorriere;

public class MainAGP {

	public static void main(String[] args) {
		GUILoginCorriere loginCor = new GUILoginCorriere();
		loginCor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginCor.setVisible(true);

	}

}
