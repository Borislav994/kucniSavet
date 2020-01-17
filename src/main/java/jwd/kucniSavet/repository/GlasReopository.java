package jwd.kucniSavet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.kucniSavet.model.Glas;

@Repository
public interface GlasReopository extends JpaRepository<Glas, Long> {

}
