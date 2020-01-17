package jwd.kucniSavet.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jwd.kucniSavet.model.Glas;
import jwd.kucniSavet.model.Poruka;
import jwd.kucniSavet.service.PorukaService;
import jwd.kucniSavet.support.GlasToGlasDTO;
import jwd.kucniSavet.support.PorukaDTOtoPoruka;
import jwd.kucniSavet.support.PorukaToPorukaDTO;
import jwd.kucniSavet.web.dto.GlasDTO;
import jwd.kucniSavet.web.dto.PorukaDTO;


@Controller
@RequestMapping("/api/poruke")
public class ApiPorukaController {
	
	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private PorukaToPorukaDTO toDTO;
	
	@Autowired
	private PorukaDTOtoPoruka toPoruka;
	
	@Autowired
	private GlasToGlasDTO toDtog; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PorukaDTO>> get(
			@RequestParam(required = false) String zgradaUlica,
			@RequestParam(required = false) String naslov,
			@RequestParam(required = false) String tip,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Poruka> recordsPage = null;

		if (zgradaUlica != null || naslov != null || tip != null) {
			recordsPage = porukaService.search(zgradaUlica, naslov, tip, pageNum);
		} else {
			recordsPage = porukaService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(recordsPage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(recordsPage.getContent()), headers, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PorukaDTO> get(@PathVariable Long id) {

		Poruka poruka = porukaService.findOne(id);

		return new ResponseEntity<>(toDTO.convert(poruka), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PorukaDTO> add(@Validated @RequestBody PorukaDTO newPoruka) {

		Poruka persisted = porukaService.save(toPoruka.convert(newPoruka));

		porukaService.save(persisted);

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<PorukaDTO> edit(@PathVariable Long id, @RequestBody PorukaDTO editedPoruka) {

		if (id == null || !id.equals(editedPoruka.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Poruka persisted = porukaService.save(toPoruka.convert(editedPoruka));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PorukaDTO> delete(@PathVariable Long id) {
		Poruka po = porukaService.findOne(id);
		if (po != null) {
			porukaService.delete(id);

			return new ResponseEntity<>(toDTO.convert(po), HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@RequestMapping(value = "{id}", method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<GlasDTO> glasaj(@PathVariable Long id,
		@RequestBody GlasDTO glasDTO){
	
	Glas glas = porukaService.glasaj(id, glasDTO);
	
	return new ResponseEntity<>(
			toDtog.convert(glas), 
			HttpStatus.CREATED);
	}
	

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
