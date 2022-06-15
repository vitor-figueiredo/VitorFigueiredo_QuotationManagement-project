package br.inatel.idp.qm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.inatel.idp.qm.model.entity.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{
	

//	@Query("select q from Quote q join q.stock s where s.stockCode = :pStockCode")
//	public List<Quote> findByStockCode(@Param("pStockCode") String pStockCode);

}
