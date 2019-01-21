package be.vdab.allesvoordekeuken.entities;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel {
	private static final long serialVersionUID = 1L;
	private int garantie;
	
	public NonFoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int garantie) {
		super(naam, aankoopprijs, verkoopprijs);
		this.garantie = garantie;
	}
	protected NonFoodArtikel() {
	}
	
	public int getGarantie() {
		return garantie;
	}
	public void setGarantie(int garantie) {
		this.garantie = garantie;
	}
}
