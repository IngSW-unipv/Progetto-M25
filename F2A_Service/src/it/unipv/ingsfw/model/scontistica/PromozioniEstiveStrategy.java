package it.unipv.ingsfw.model.scontistica;

public class PromozioniEstiveStrategy implements IScontisticaStrategy{

	private final double MULTIPLIER=0.85;
	@Override
	public double getTotal(TotemContext sale) {
		// TODO Auto-generated method stub
		return sale.getCosto()*MULTIPLIER;
	}


}
