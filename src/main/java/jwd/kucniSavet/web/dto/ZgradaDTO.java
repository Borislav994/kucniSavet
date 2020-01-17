package jwd.kucniSavet.web.dto;

public class ZgradaDTO {

	private long id;
	private String adresa;
	private String predsednik;
	private int brStanova;
	private int brStanara;

	public ZgradaDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
