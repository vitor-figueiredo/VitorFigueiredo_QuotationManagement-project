package br.inatel.idp.qm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.idp.qm.model.entity.Quote;
import br.inatel.idp.qm.model.entity.Stock;
import br.inatel.idp.qm.model.exception.BusinessException;
import br.inatel.idp.qm.repository.QuoteRepository;
import br.inatel.idp.qm.repository.StockRepository;

/**
 * Service principal do Quotation Manager
 * @author vitor.figueiredo 
 * @since 15 jun 2022
 */
@Service
@Transactional
public class QuotationService {

	@Autowired
	StockRepository stockRepo;
	
	@Autowired
	QuoteRepository quoteRepo;
	
	
	/**
	 * Salva o stock e as quotes associadas
	 * @param stock
	 * @return
	 */
	public Stock save(Stock stock) {
		//1.verifica se code ja existe
		verifyIfStockCodeExistsLocallye(stock);
		
		//2.salva o stock
		stockRepo.save( stock );
		//3.salva cada quote
		List<Quote> quoteList = stock.getQuoteList();
		quoteList.forEach( q -> quoteRepo.save(q) );

		return stock;
	}


	/**
	 * Verifica (somente para novo) se stockCode já existe
	 * @param stock
	 */
	private void verifyIfStockCodeExistsLocallye(Stock stock) {
		//eh novo?
		if (stock.isTransient()) {
			//entao busca stock pelo code
			Optional<Stock> opStock = stockRepo.findByStockCode( stock.getStockCode() );
			//exist?
			if (opStock.isPresent()) {
				//entao lança erro
				String msg = String.format("StockCode [%s] já cadastrado localmente", stock.getStockCode());
				throw new BusinessException( msg );
			}
		}
	}
	
	
//	/**
//	 * Verifica
//	 * @param stock
//	 */
//	private void verifyIfStockCodeExistsRemotely(Stock stock) {
//		
//	}


	/**
	 * Busca o stock pelo code
	 * @param stockCode
	 * @return
	 */
	public Optional<Stock> findOneStockAndQuotesByStockCode(String stockCode) {
		//1.busca pelo code
		Optional<Stock> opStock = stockRepo.findByStockCode(stockCode);
		//2. força o load das quotes
		opStock.map( s -> s.getQuoteList().size() );
		
		return opStock;
	}
	

	/**
	 * Busca todos stocks
	 * @return
	 */
	public List<Stock> findAllStockAndQuotes() {
		//1.busca todos stocks
		List<Stock> stockList = stockRepo.findAll();
		//2.força do loading das quotes de cada stock
		stockList.forEach( s -> s.getQuoteList().size() );
		
		return stockList;
	}

	
	

}
