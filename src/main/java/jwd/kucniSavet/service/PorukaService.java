package jwd.kucniSavet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.kucniSavet.model.Glas;
import jwd.kucniSavet.model.Poruka;
import jwd.kucniSavet.model.Zgrada;
import jwd.kucniSavet.web.dto.GlasDTO;

public interface PorukaService {
	
	Poruka findOne(Long id);

	Page<Poruka> findAll(int pageNum);

	Poruka save(Poruka poruka);

	void delete(Long id);

	List<Poruka> findByZgradaId(Long zgradaId);
	
	List<Poruka> findByZgrada(Zgrada zgrada);
	
	Page<Poruka> search(
			@Param("zgradaUlica") String zgradaUlica, 
			@Param("naslov") String naslov, 
			@Param("tip") String tip, 
			int pageNum);
	Glas glasaj(Long id, GlasDTO glasDTO);

}
