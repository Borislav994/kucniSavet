package jwd.kucniSavet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.kucniSavet.model.Zgrada;

@Repository
public interface ZgradaRepository extends JpaRepository<Zgrada, Long> {

}
