package it.unipv.ingsfw.model.scontistica;

public class AnniversarioAziendaStrategy implements IScontisticaStrategy{

	private final double MULTIPLIER=0.25;
	@Override
	public double getTotal(TotemContext sale) {
		// TODO Auto-generated method stub
		return sale.getCosto()*MULTIPLIER;
	}


}
