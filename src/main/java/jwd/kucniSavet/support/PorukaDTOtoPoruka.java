package jwd.kucniSavet.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.kucniSavet.model.Poruka;
import jwd.kucniSavet.model.Zgrada;
import jwd.kucniSavet.service.PorukaService;
import jwd.kucniSavet.service.ZgradaService;
import jwd.kucniSavet.web.dto.PorukaDTO;

@Component
public class PorukaDTOtoPoruka implements Converter<PorukaDTO, Poruka>{
	
	@Autowired
	private ZgradaService zgradaService;
	
	@Autowired
	private PorukaService porukaService;
	
	@Override
	public Poruka convert(PorukaDTO porukaDTO) {
		Zgrada zgrada = zgradaService.findOne(porukaDTO.getZgradaId());

		if (zgrada != null) {

			Poruka poruka = null;

			if (porukaDTO.getId() != null) {
				poruka = porukaService.findOne(porukaDTO.getId());
			} else {
				poruka = new Poruka();
			}

			poruka.setId(porukaDTO.getId());
			poruka.setNaslov(porukaDTO.getNaslov());
			poruka.setOpis(porukaDTO.getOpis());
			poruka.setPotrebanProcenat(porukaDTO.getPotrebanProcenat());
			poruka.setPrihvacen(porukaDTO.getPrihvacen());
			poruka.setTip(porukaDTO.getTip());
			poruka.setZgrada(zgrada);

			return poruka;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}		
	}
	
	public List<Poruka> convert(List<PorukaDTO> porukeDTO) {
		List<Poruka> ret = new ArrayList<>();

		for (PorukaDTO poruka : porukeDTO) {
			ret.add(convert(poruka));
		}

		return ret;
	}


}
