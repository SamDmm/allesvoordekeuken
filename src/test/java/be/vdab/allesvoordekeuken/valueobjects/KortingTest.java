package be.vdab.allesvoordekeuken.valueobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class KortingTest {
	private Korting korting1, nogEensKorting1, korting2;
	@Before
	public void before() {
		korting1 = new Korting(1, BigDecimal.ONE);
		nogEensKorting1 = new Korting(1, BigDecimal.ONE);
		korting2 = new Korting(2,BigDecimal.TEN);
	}
	@Test
	public void kortingenZijnGelijkAlsHunAantallenGelijkZijn() {
		assertEquals(korting1, nogEensKorting1);
	}
	@Test
	public void kortingenZijnVerschillendAlsHunVanafAantallenVerschillen() {
		assertNotEquals(korting1, korting2);
	}
	@Test
	public void eenKortingIsNietGelijkAanNull() {
		assertNotEquals(korting1, null);
	}
	@Test
	public void eenKortingIsNietGelijkAanEenAnderTypeObject() {
		assertNotEquals(korting1, "");
	}
	@Test
	public void gelijkeKortingenGevenDezelfdeHashCode() {
		assertEquals(korting1.hashCode(), nogEensKorting1.hashCode());
	}
}
