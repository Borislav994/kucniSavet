package jwd.kucniSavet.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.kucniSavet.model.Poruka;
import jwd.kucniSavet.model.Zgrada;
import jwd.kucniSavet.service.PorukaService;
import jwd.kucniSavet.service.ZgradaService;
import jwd.kucniSavet.support.PorukaToPorukaDTO;
import jwd.kucniSavet.support.ZgradaToZgradaDTO;
import jwd.kucniSavet.web.dto.PorukaDTO;
import jwd.kucniSavet.web.dto.ZgradaDTO;



@RestController
@RequestMapping("/api/zgrade")
public class ApiZgradaController {

	@Autowired
	private ZgradaService zgradaService;
	
	@Autowired
	private PorukaService porukaService;

	@Autowired
	private ZgradaToZgradaDTO toDTO;
	
	@Autowired
	private PorukaToPorukaDTO toDTOp;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ZgradaDTO>> getActivities(){
		
		List<Zgrada> zgrade = zgradaService.findAll();
		
		return new ResponseEntity<>(toDTO.convert(zgrade), HttpStatus.OK);
	} 
	
	@RequestMapping(value = "{zgradaId}/poruke", method = RequestMethod.GET)
	public ResponseEntity<List<PorukaDTO>> getAllId(@PathVariable Long zgradaId){
		List<Poruka> poruke = porukaService.findByZgradaId(zgradaId);

		return new ResponseEntity<>(toDTOp.convert(poruke), HttpStatus.OK);
	}
}
