package jwd.kucniSavet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblPoruka")
public class Poruka {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "naslov", nullable = false)
	private String naslov;
	@Column(name = "tip")
	private String tip;
	@Column(name = "potrebanProcenat")
	private Double potrebanProcenat;
	@Column(name = "prihvacen")
	private Boolean prihvacen = false;
	@Column(name = "opis", nullable = false)
	private String opis;
	@ManyToOne(fetch = FetchType.EAGER)
	private Zgrada zgrada;
	@OneToMany(mappedBy = "poruka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Glas> glasovi = new ArrayList<>();

	public Poruka() {
		super();
	}

	public List<Glas> getGlasovi() {
		return glasovi;
	}

	public void setGlasovi(List<Glas> glasovi) {
		this.glasovi = glasovi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Double getPotrebanProcenat() {
		return potrebanProcenat;
	}

	public void setPotrebanProcenat(Double potrebanProcenat) {
		this.potrebanProcenat = potrebanProcenat;
	}

	public Boolean getPrihvacen() {
		return prihvacen;
	}

	public void setPrihvacen(Boolean prihvacen) {
		this.prihvacen = prihvacen;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Zgrada getZgrada() {
		return zgrada;
	}

	public void setZgrada(Zgrada zgrada) {
		this.zgrada = zgrada;
		if (!zgrada.getPoruke().contains(this)) {
			zgrada.getPoruke().add(this);
		}
	}

}
