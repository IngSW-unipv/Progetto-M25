package it.unipv.ingsfw.model.scontistica;

import java.util.Calendar;
import java.util.Random;

public class TotemContext {
	private IScontisticaStrategy strategy;
	private Integer costo;

	public TotemContext(Integer c) {
		costo=c;
		strategy=getScontoStrategy();
	}

	public Integer getCosto() {
		return costo;
	}
	

	public double getTotal() {
		return strategy==null?costo:strategy.getTotal(this);
	}
	
	
	public IScontisticaStrategy getScontoStrategy() {
        // Verifica se è dicembre (mese 12)
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH è 0-based, quindi aggiungiamo 1
        
        if (month == 12) {
            // Applicare XmasStrategy a dicembre
            return new XmasStrategy();  // Restituisci la strategia natalizia
        }

        Random rand = new Random();
        int randomNumber = rand.nextInt(1000); 
        if (randomNumber % 28 == 0) {        
            return new ClienteFortunatoStrategy(); // Restituisci la strategia del cliente fortunato
        }
        
        DiscountFactory factory = new DiscountFactory();
        return factory.getDiscountStrategy(); 
    }
	
	
	
    public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public void setStrategy(IScontisticaStrategy strategy) {
        this.strategy = strategy;  // Permette di modificare la strategia
    }
}
