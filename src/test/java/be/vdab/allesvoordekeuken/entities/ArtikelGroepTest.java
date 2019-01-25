package be.vdab.allesvoordekeuken.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ArtikelGroepTest {
	ArtikelGroep groep1;
	ArtikelGroep groep2;
	Artikel artikel1;
	@Before
	public void before() {
		groep1 = new ArtikelGroep("test");
		groep2 = new ArtikelGroep("test2");
		artikel1 = new Artikel("test", BigDecimal.ONE, BigDecimal.TEN, groep1);
	}
	@Test
	public void groep1MoetDeArtikelGroepZijnVanArtikel1() {
		assertEquals(groep1, artikel1.getArtikelGroep());
		assertEquals(1, groep1.getArtikels().size());
		assertTrue(groep1.getArtikels().contains(artikel1));
	}
	@Test
	public void artikel1VerhuistNaarGroep2() {
		assertTrue(groep2.add(artikel1));
		assertTrue(groep1.getArtikels().isEmpty());
		assertTrue(groep2.getArtikels().contains(artikel1));
		assertEquals(groep2, artikel1.getArtikelGroep());
	}
}
