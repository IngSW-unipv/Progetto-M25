package it.unipv.ingsfw.model.scontistica;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class DiscountFactory {
	
	private IScontisticaStrategy discount;
	private final String PROPERTYNAME="discount.strategy.class.name";
	
	public IScontisticaStrategy getDiscountStrategy() {	
		if(discount==null) {
			Properties p = new Properties(System.getProperties());
			String DiscountClassName;			
			try {
				p.load(new FileInputStream("properties/properties"));
				DiscountClassName=p.getProperty(PROPERTYNAME);				
				Constructor c = Class.forName(DiscountClassName).getConstructor();
				discount=(IScontisticaStrategy)c.newInstance();				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				discount=null;
			}		
		}		
		return discount;
	}
}
