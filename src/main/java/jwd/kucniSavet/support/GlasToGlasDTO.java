package jwd.kucniSavet.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.kucniSavet.model.Glas;
import jwd.kucniSavet.web.dto.GlasDTO;



@Component
public class GlasToGlasDTO implements Converter <Glas, GlasDTO>  {
	
	@Override
	public GlasDTO convert(Glas glas){
		
		GlasDTO retValue = new GlasDTO();
		retValue.setId(glas.getId());
		retValue.setPorukaId(glas.getPoruka().getId());
		retValue.setPorukaNaziv(glas.getPoruka().getNaslov());
		retValue.setPredlogPodrzan(glas.getPredlogPodrzan());
		
		return retValue;
	}

	public List<GlasDTO> convert(List<Glas> dtos){
		List<GlasDTO> ret = new ArrayList<>();
		
		for(Glas it : dtos){
			ret.add(convert(it));
		}
		
		return ret;
	}

}
