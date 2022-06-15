package br.inatel.idp.qm.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.inatel.idp.qm.model.entity.Quote;
import br.inatel.idp.qm.model.entity.Stock;

public class QuoteDTO {

	private Long id;
	
	private String stockId;
	
	private String stockCode;
	
	private LocalDate quoteDate;

	private BigDecimal quotePrice;

	
	public QuoteDTO(Quote q) {
		this.id = q.getId();
		this.stockId = q.getStock().getId();
		this.stockCode = q.getStock().getStockCode();
		this.quoteDate = q.getDate();
		this.quotePrice = q.getPrice();	
	}
	
	public Quote convertToQuote() {
		Stock stock = new Stock(this.stockId, this.stockCode);
		Quote quote = new Quote( stock, quoteDate, quotePrice);
		return quote;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public LocalDate getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(LocalDate quoteDate) {
		this.quoteDate = quoteDate;
	}

	public BigDecimal getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}
	
	
}
