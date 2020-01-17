package jwd.kucniSavet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.kucniSavet.model.Poruka;
import jwd.kucniSavet.model.Zgrada;
import jwd.kucniSavet.service.PorukaService;
import jwd.kucniSavet.service.ZgradaService;


@Component
public class TestData {

	@Autowired
	private ZgradaService zgradaService;
	
	@Autowired
	private PorukaService porukaService;
	
	@PostConstruct
	public void init() {
		
	Zgrada zg1 = new Zgrada();
	zg1.setAdresa("Lenjinova 45");
	zg1.setBrStanara(50);
	zg1.setBrStanova(100);
	zg1.setPredsednik("Borislav Spasic");
	zgradaService.save(zg1);
	
	Zgrada zg2 = new Zgrada();
	zg2.setAdresa("Ive Lole Ribara 45");
	zg2.setBrStanara(45);
	zg2.setBrStanova(50);
	zg2.setPredsednik("Pera Peric");
	zgradaService.save(zg2);
	
	Poruka po1 = new Poruka();
	po1.setNaslov("Naslov1");
	po1.setOpis("Opis1");
	po1.setPotrebanProcenat(10.0);
	po1.setPrihvacen(false);
	po1.setTip("Obavestenje");
	po1.setZgrada(zg1);
	porukaService.save(po1);
	
	Poruka po2 = new Poruka();
	po2.setNaslov("Naslov2");
	po2.setOpis("Opis2");
	po2.setPotrebanProcenat(100.0);
	po2.setPrihvacen(false);
	po2.setTip("Predlog");
	po2.setZgrada(zg2);
	porukaService.save(po2);

	}
}
