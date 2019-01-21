package be.vdab.allesvoordekeuken.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import be.vdab.allesvoordekeuken.entities.Artikel;

@Repository
class JpaArtikelRepository implements ArtikelRepository {
	private final EntityManager manager;
	
	public JpaArtikelRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public Optional<Artikel> read(long id) {
		return Optional.ofNullable(manager.find(Artikel.class, id));
	}
	@Override
	public void create(Artikel artikel) {
		manager.persist(artikel);
	}
	@Override
	public List<Artikel> findByNaamContains(String woord) {
		return manager.createNamedQuery("Artikel.findByNaamContains", Artikel.class).setParameter("zoals", '%' + woord + '%').getResultList();
	}
	@Override
	public int prijsVerhoging(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		return manager.createNamedQuery("Artikel.prijsverhoging").setParameter("factor", factor).executeUpdate();
	}
}
