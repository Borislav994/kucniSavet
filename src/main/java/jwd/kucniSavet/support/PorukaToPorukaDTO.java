package jwd.kucniSavet.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.kucniSavet.model.Poruka;
import jwd.kucniSavet.web.dto.PorukaDTO;

@Component
public class PorukaToPorukaDTO implements Converter<Poruka, PorukaDTO>{

	@Override
	public PorukaDTO convert(Poruka poruka) {
		PorukaDTO retValue = new PorukaDTO();
		retValue.setId(poruka.getId());
		retValue.setNaslov(poruka.getNaslov());
		retValue.setOpis(poruka.getOpis());
		retValue.setPotrebanProcenat(poruka.getPotrebanProcenat());
		retValue.setPrihvacen(poruka.getPrihvacen());
		retValue.setTip(poruka.getTip());
		retValue.setZgradaId(poruka.getZgrada().getId());
		retValue.setZgradaAdresa(poruka.getZgrada().getAdresa());

		return retValue;
	}

	public List<PorukaDTO> convert(List<Poruka> poruke) {
		List<PorukaDTO> ret = new ArrayList<>();

		for (Poruka poruka : poruke) {
			ret.add(convert(poruka));
		}

		return ret;
	}
}
