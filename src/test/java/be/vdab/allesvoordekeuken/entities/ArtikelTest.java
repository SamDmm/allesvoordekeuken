package be.vdab.allesvoordekeuken.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ArtikelTest {
	private Artikel artikel1;
	private ArtikelGroep groep1;
	private ArtikelGroep groep2;
	@Before
	public void before() {
		groep1 = new ArtikelGroep("test1");
		groep2 = new ArtikelGroep("test2");
		artikel1 = new Artikel("test", BigDecimal.TEN, BigDecimal.ONE, groep1);
	}
	@Test
	public void groep1MoetDeArtikelGoepZijnVanArtikel1() {
		assertEquals(1, groep1.getArtikels().size());
		assertTrue(groep1.getArtikels().contains(artikel1));
		assertEquals(groep1, artikel1.getArtikelGroep());
	}
	@Test
	public void artikel1VerhuistnaarGroep2() {
		artikel1.setArtikelGroep(groep2);
		assertTrue(groep1.getArtikels().isEmpty());
		assertEquals(1, groep2.getArtikels().size());
		assertTrue(groep2.getArtikels().contains(artikel1));
		assertEquals(groep2, artikel1.getArtikelGroep());
	}
}
