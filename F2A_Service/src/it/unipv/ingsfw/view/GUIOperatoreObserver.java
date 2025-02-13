package it.unipv.ingsfw.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class GUIOperatoreObserver extends JFrame implements Observer, ActionListener{
	
	@Override
	public void update(Observable o, Object arg) {
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
	}
}
