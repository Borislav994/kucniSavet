package jwd.kucniSavet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.kucniSavet.model.Poruka;
import jwd.kucniSavet.model.Zgrada;



@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long > {
	
	List<Poruka> findByZgradaId(Long zgradaId);
	
	List<Poruka> findByZgrada(Zgrada zgrada);
	
	@Query("SELECT a FROM Poruka a WHERE "
			+ "(:zgradaUlica IS NULL or a.zgrada.adresa like :zgradaUlica ) AND "
			+ "(:naslov IS NULL or a.naslov like :naslov ) AND "
			+ "(:tip IS NULL OR a.tip like :tip)"
			)
	Page<Poruka> search(
			@Param("zgradaUlica") String zgradaUlica, 
			@Param("naslov") String naslov, 
			@Param("tip") String tip, 
			Pageable pageRequest);


}
