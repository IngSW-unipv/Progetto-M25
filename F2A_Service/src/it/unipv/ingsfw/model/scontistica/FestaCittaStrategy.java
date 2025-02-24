package it.unipv.ingsfw.model.scontistica;

public class FestaCittaStrategy implements IScontisticaStrategy{

	private final double MULTIPLIER=0.95;
	@Override
	public double getTotal(TotemContext sale) {
		// TODO Auto-generated method stub
		return sale.getCosto()*MULTIPLIER;
	}


}
