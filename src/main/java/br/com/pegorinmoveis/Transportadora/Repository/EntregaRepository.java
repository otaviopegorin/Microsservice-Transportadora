package br.com.pegorinmoveis.Transportadora.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pegorinmoveis.Transportadora.modelo.Entrega;

public interface EntregaRepository extends PagingAndSortingRepository<Entrega, String>{

	@Query(value = "SELECT * FROM entrega where data <= CURRENT_DATE - 10 order by data desc", nativeQuery = true)
	List<Entrega> findByDataMenorQueDezDias();
	
	@Query(value = "SELECT * FROM entrega where data <= CURRENT_DATE - 5 and data > CURRENT_DATE - 10 order by data desc", nativeQuery = true)
	List<Entrega> findByDataMenorQueCincoDias();
	
	Page<Entrega> findAll(Pageable pageable);
}
