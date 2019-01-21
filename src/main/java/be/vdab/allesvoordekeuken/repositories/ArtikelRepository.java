package be.vdab.allesvoordekeuken.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.allesvoordekeuken.entities.Artikel;

interface ArtikelRepository {
	Optional<Artikel> read(long id);
	void create(Artikel artikel);
	List<Artikel> findByNaamContains(String woord);
	int prijsVerhoging(BigDecimal percentage);
}
