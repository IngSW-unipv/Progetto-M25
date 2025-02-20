package it.unipv.ingsfw.view.cliente;
import java.awt.*;
import javax.swing.*;
	
public class RegistrazionePanel extends JPanel  {
	public void paintComponent (Graphics g) {
		super. paintComponent (g) ;
		// Testo da disegnare
		String message = "Inserire i dati richiesti per la registrazione";
		
		// Otteniamo la larghezza e l'altezza del testo
		FontMetrics metrics = g.getFontMetrics();
		int textWidth = metrics.stringWidth(message);
		int textHeight = metrics.getHeight();
		
		// Calcoliamo le coordinate per centrare il testo orizzontalmente e posizionarlo in alto
		int x = (getWidth() - textWidth) / 2; // Centriamo orizzontalmente
		int y = textHeight; // Posizioniamo in alto (con margine)

		// Disegniamo il testo
		g.drawString(message, x, y);
	}
}




