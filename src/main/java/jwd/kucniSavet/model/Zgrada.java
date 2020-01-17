package jwd.kucniSavet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblZgrada")
public class Zgrada {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "adresa", nullable = false, unique = true)
	private String adresa;
	@Column(name = "predsednik", nullable = false)
	private String predsednik;
	@Column(name = "brStanovi")
	private int brStanova;
	@Column(name = "brStanara", nullable = false)
	private int brStanara;
	@OneToMany(mappedBy = "zgrada", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Poruka> poruke = new ArrayList<>();

	public Zgrada() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPredsednik() {
		return predsednik;
	}

	public void setPredsednik(String predsednik) {
		this.predsednik = predsednik;
	}

	public int getBrStanova() {
		return brStanova;
	}

	public void setBrStanova(int brStanova) {
		this.brStanova = brStanova;
	}

	public int getBrStanara() {
		return brStanara;
	}

	public void setBrStanara(int brStanara) {
		this.brStanara = brStanara;
	}

	public List<Poruka> getPoruke() {
		return poruke;
	}

	public void setPoruke(List<Poruka> poruke) {
		this.poruke = poruke;
	}

	public void addPoruka(Poruka poruka) {
		this.poruke.add(poruka);
		if (poruka.getZgrada() != null && !poruka.getZgrada().equals(this)) {
			poruka.setZgrada(this);
		}
	}

}
