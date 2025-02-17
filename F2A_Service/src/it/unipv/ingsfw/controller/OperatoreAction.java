package it.unipv.ingsfw.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.sun.tools.javac.Main;

import it.unipv.ingsfw.model.users.DipendenteDAO;
import it.unipv.ingsfw.model.users.Operatore;
import it.unipv.ingsfw.view.operatore.MainFrameOperatore;
import it.unipv.ingsfw.view.operatore.viewLogin.GUILoginOperatore;

public class OperatoreAction {

	Operatore op; // facade model
	private GUILoginOperatore g; // facade view
	private MainFrameOperatore m; // facade view

	/**
	 * @param op
	 * @param o
	 */
	public OperatoreAction(Operatore op, GUILoginOperatore g) {
		super();
		this.op = op;
		this.g = g;
		// this.m = new MainFrameOperatore(op);
		addListeners();
	}

	public OperatoreAction(Operatore op, MainFrameOperatore m) {
		super();
		this.op = op;
		this.m = m;
		addListeners2();
	}

	private void addListeners() {
		g.getPannello().getLoginButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (g.getPannello().getLoginButton().getActionCommand().equalsIgnoreCase("accesso")) {

					Boolean b = op.verificaCredenzialiAccesso(g.getPannello().getUserText().getText(),
							g.getPannello().getPassText().getText());
					//System.out.println(b);
					if (b) {
						JOptionPane.showMessageDialog(g, "Benvenuto!", "Pop-up", JOptionPane.OK_CANCEL_OPTION);
						// JOptionPane.showInputDialog(g);
						g.dispose();
						DipendenteDAO d = new DipendenteDAO();
						MainFrameOperatore loginOp = new MainFrameOperatore(new Operatore(
								d.selectIdByEmailPassword(new Operatore(g.getPannello().getUserText().getText(),
										g.getPannello().getPassText().getText()))));
						loginOp.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(g, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
					}
					// g.getPannello().getUserText().setText("");
					// g.getPannello().getPassText().setText("");
				}
			}
		});
	}

	private void addListeners2() {
		/*
		 * m.getPannello().getScelta().addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * m.getPannello().getSelectedRadioButton();
		 * 
		 * } });
		 */
	}

}
