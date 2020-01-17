package jwd.kucniSavet.service;

import java.util.List;

import jwd.kucniSavet.model.Zgrada;


public interface ZgradaService {
	
	List<Zgrada> findAll();
	Zgrada save(Zgrada zgrada);
	Zgrada findOne(Long id);
}
