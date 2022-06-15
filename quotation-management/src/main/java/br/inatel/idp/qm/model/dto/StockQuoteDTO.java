package br.inatel.idp.qm.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import br.inatel.idp.qm.model.entity.Quote;
import br.inatel.idp.qm.model.entity.Stock;

public class StockQuoteDTO {
	
	private String id;
	
	@NotBlank
	private String stockCode;
	
	private Map<LocalDate, BigDecimal> quotes = new HashMap<LocalDate, BigDecimal>();
	
	
	//construtores...
	
	public StockQuoteDTO() {
		
	}
	
	public StockQuoteDTO(Stock stock) {
		this.id = stock.getId();
		this.stockCode = stock.getStockCode();
		
		stock.getQuoteList().stream()
			.forEach( q -> quotes.put(q.getDate(), q.getPrice()) )
			;
	}


	public Stock convertToStock() {
		Stock stock = new Stock();
		stock.setId( id );
		stock.setStockCode(stockCode);
		
		quotes.entrySet().stream()
			.map( e -> new Quote(stock, e.getKey(), e.getValue()) )
			.forEach( q -> stock.addQuote( q ) );
		
		return stock;
	}


	//acessores..
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getStockCode() {
		return stockCode;
	}


	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}


	public Map<LocalDate, BigDecimal> getQuotes() {
		return quotes;
	}


	public void setQuotes(Map<LocalDate, BigDecimal> quotes) {
		this.quotes = quotes;
	}

	
}
