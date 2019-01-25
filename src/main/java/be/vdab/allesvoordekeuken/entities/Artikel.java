package be.vdab.allesvoordekeuken.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import be.vdab.allesvoordekeuken.valueobjects.Korting;

@Entity
@Table(name = "artikels")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
public class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	@ElementCollection  @OrderBy("vanafAantal")
	@CollectionTable(name = "kortingen", joinColumns = @JoinColumn(name = "artikelid"))
	private Set<Korting> kortingen;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artikelgroepid")
	private ArtikelGroep artikelGroep;
	
	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, ArtikelGroep artikelGroep) {
		this.naam = naam;
		this.aankoopprijs = aankoopprijs;
		this.verkoopprijs = verkoopprijs;
		this.kortingen = new LinkedHashSet<>();
		setArtikelGroep(artikelGroep);
	}
	protected Artikel() {
	}

	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	public BigDecimal getAankoopprijs() {
		return aankoopprijs;
	}
	public BigDecimal getVerkoopprijs() {
		return verkoopprijs;
	}
	public ArtikelGroep getArtikelGroep() {
		return artikelGroep;
	}
	
	
	public Set<Korting> getKortingen() {
		return Collections.unmodifiableSet(kortingen);
	}
	public void setArtikelGroep(ArtikelGroep artikelGroep) {
		if (artikelGroep == null) {
			throw new NullPointerException();
		}
		if (!artikelGroep.getArtikels().contains(this)) {
			artikelGroep.add(this);
		}
		this.artikelGroep = artikelGroep;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.toUpperCase().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Artikel))
			return false;
		Artikel other = (Artikel) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equalsIgnoreCase(other.naam))
			return false;
		return true;
	}
}
