package jwd.kucniSavet.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.kucniSavet.model.Zgrada;
import jwd.kucniSavet.web.dto.ZgradaDTO;

@Component
public class ZgradaToZgradaDTO implements Converter<Zgrada, ZgradaDTO> {
	@Override
	public ZgradaDTO convert(Zgrada zgrada) {
		ZgradaDTO retValue = new ZgradaDTO();
		retValue.setId(zgrada.getId());
		retValue.setAdresa(zgrada.getAdresa());
		retValue.setBrStanara(zgrada.getBrStanara());
		retValue.setBrStanova(zgrada.getBrStanara());
		retValue.setPredsednik(zgrada.getPredsednik());

		return retValue;
	}

	public List<ZgradaDTO> convert(List<Zgrada> zgrade) {
		List<ZgradaDTO> ret = new ArrayList<>();

		for (Zgrada zgrada : zgrade) {
			ret.add(convert(zgrada));
		}

		return ret;
	}
}
