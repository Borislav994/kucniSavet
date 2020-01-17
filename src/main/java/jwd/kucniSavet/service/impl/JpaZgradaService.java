package jwd.kucniSavet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.kucniSavet.model.Zgrada;
import jwd.kucniSavet.repository.ZgradaRepository;
import jwd.kucniSavet.service.ZgradaService;

@Service
public class JpaZgradaService implements ZgradaService {
	
	@Autowired
	private ZgradaRepository zgradaRepository;

	@Override
	public List<Zgrada> findAll() {
		// TODO Auto-generated method stub
		return zgradaRepository.findAll();
	}

	@Override
	public Zgrada save(Zgrada zgrada) {
		// TODO Auto-generated method stub
		return zgradaRepository.save(zgrada);
	}

	@Override
	public Zgrada findOne(Long id) {
		// TODO Auto-generated method stub
		return zgradaRepository.findOne(id);
	}

}
