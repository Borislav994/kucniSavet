package jwd.kucniSavet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.kucniSavet.model.Glas;
import jwd.kucniSavet.model.Poruka;
import jwd.kucniSavet.model.Zgrada;
import jwd.kucniSavet.repository.GlasReopository;
import jwd.kucniSavet.repository.PorukaRepository;
import jwd.kucniSavet.service.PorukaService;
import jwd.kucniSavet.web.dto.GlasDTO;

@Service
public class JpaPorukaService implements PorukaService {

	@Autowired
	public PorukaRepository porukaRepository;
	
	@Autowired
	private GlasReopository glasRepository;

	@Override
	public Poruka findOne(Long id) {
		// TODO Auto-generated method stub
		return porukaRepository.findOne(id);
	}

	@Override
	public Page<Poruka> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return porukaRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Poruka save(Poruka poruka) {
		// TODO Auto-generated method stub
		return porukaRepository.save(poruka);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		porukaRepository.delete(id);
		
	}

	@Override
	public List<Poruka> findByZgradaId(Long zgradaId) {
		// TODO Auto-generated method stub
		return porukaRepository.findByZgradaId(zgradaId);
	}

	@Override
	public List<Poruka> findByZgrada(Zgrada zgrada) {
		// TODO Auto-generated method stub
		return porukaRepository.findByZgrada(zgrada);
	}

	@Override
	public Page<Poruka> search(String zgradaUlica, String naslov, String tip, int pageNum) {
		// TODO Auto-generated method stub
		if(zgradaUlica != null) {
			zgradaUlica = '%' + zgradaUlica + '%';
		}
		if(naslov != null) {
			naslov = '%' + naslov + '%';
		}
		if(tip != null) {
			tip = '%' + tip + '%';
		}
		return porukaRepository.search(zgradaUlica, naslov, tip, new PageRequest(pageNum, 5));
	}

	@Override
	public Glas glasaj(Long id, GlasDTO glasDTO) {
		// TODO Auto-generated method stub
		Poruka poruka = findOne(id);
		if(poruka == null) {
			throw new IllegalArgumentException("Pokusavate da glasate za "
					+ "nepostojecu poruku");
		}
		
		Glas glas = new Glas();
		glas.setPoruka(poruka);
		glas.setPredlogPodrzan(glasDTO.getPredlogPodrzan());
		poruka.getGlasovi().add(glas);
		int glasovi = poruka.getGlasovi().size() - 1;
		int stanari = poruka.getZgrada().getBrStanara();
		
		double procenat = (100 * glasovi)/stanari;
		System.out.println("glasovi, stanari, procenat" +" /" + glasovi+" /" + stanari+" /" + procenat);
		if(procenat >= poruka.getPotrebanProcenat()) {
			poruka.setPrihvacen(true);
		}
		
		
		porukaRepository.save(poruka);
	
		return glas;
	}




}

