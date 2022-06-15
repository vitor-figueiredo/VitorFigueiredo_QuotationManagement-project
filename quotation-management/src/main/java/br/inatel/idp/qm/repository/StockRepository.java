package br.inatel.idp.qm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.idp.qm.model.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

	Optional<Stock> findByStockCode(String stockCode);
	

}
