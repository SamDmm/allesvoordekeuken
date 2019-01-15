package be.vdab.allesvoordekeuken.repositories;

import java.util.Optional;

import be.vdab.allesvoordekeuken.entities.Artikel;

interface ArtikelRepository {
	Optional<Artikel> read(long id);
	void create(Artikel artikel);
}
