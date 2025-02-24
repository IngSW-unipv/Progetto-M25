package it.unipv.ingsfw.model.scontistica;

public class PulizieDiPrimaveraStrategy implements IScontisticaStrategy{

	private final double MULTIPLIER=0.90;
	@Override
	public double getTotal(TotemContext sale) {
		// TODO Auto-generated method stub
		return sale.getCosto()*MULTIPLIER;
	}


}
