package it.unipv.ingsfw.model.scontistica;

public class ClienteFortunatoStrategy implements IScontisticaStrategy{

	private final double MULTIPLIER=0.50;
	@Override
	public double getTotal(TotemContext sale) {
		// TODO Auto-generated method stub
		return sale.getCosto()*MULTIPLIER;
	}

}

