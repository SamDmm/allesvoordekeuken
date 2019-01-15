package be.vdab.allesvoordekeuken.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.allesvoordekeuken.entities.Artikel;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertArtikel.sql")
@Import(JpaArtikelRepository.class)
public class JpaArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final String ARTIKELS = "artikels";
	private Artikel artikel;
	
	@Before
	public void before() {
		artikel = new Artikel("testtest", BigDecimal.TEN, BigDecimal.ONE);
	}
	@Autowired
	private JpaArtikelRepository repository;
	private long idVanTestArtikel() {
		return super.jdbcTemplate.queryForObject("select id from artikels where naam = 'test';", Long.class);
	}
	@Test
	public void read() {
		Artikel artikel = repository.read(idVanTestArtikel()).get();
		assertEquals("test", artikel.getNaam());
	}
	@Test
	public void readOnbestaandArtikel() {
		assertFalse(repository.read(-1).isPresent());
	}
	@Test
	public void create() {
		int aantalArtikels = super.countRowsInTable(ARTIKELS);
		repository.create(artikel);
		assertEquals(aantalArtikels + 1, super.countRowsInTable(ARTIKELS));
		assertEquals(1, super.countRowsInTableWhere(ARTIKELS, "id=" + artikel.getId()));
		assertNotEquals(0, artikel.getId());
	}
}
