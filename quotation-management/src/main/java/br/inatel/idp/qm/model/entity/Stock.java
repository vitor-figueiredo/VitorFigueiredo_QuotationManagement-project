package br.inatel.idp.qm.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Represent uma ação
 * @author vitor.figueiredo
 * @since 14 jun 2022
 */
@Entity
public class Stock {
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank
	@Column(unique = true)
	private String stockCode;
	
	
	@OneToMany(mappedBy = "stock")
	private List<Quote> quoteList;

	
	//construtores...
	
	public Stock() {
		
	}
	
	public Stock(String id, @NotNull @NotBlank String stockCode) {
		this.id = id;
		this.stockCode = stockCode;
	}
	

	//listener...

	@PrePersist
	private void onPersist() {
		generateUUID();
		upcaseStockCode();
	}

	@PreUpdate
	private void onUpdate() {
		upcaseStockCode();
	}

	
	private void generateUUID() {
		if (isTransient()) {
			id = UUID.randomUUID().toString();
		}
	}
	
	
	private void upcaseStockCode() {
		this.stockCode = this.stockCode.toUpperCase();
	}
	

	public boolean isTransient() {
		return id==null || id.trim().isEmpty();
	}

	
	// helper...
	
	public void addQuote(Quote q) {
		if (this.quoteList==null) {
			 this.quoteList = new ArrayList<>();
		}
		this.quoteList.add( q );
	}
	

	
	//acesores...
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<Quote> getQuoteList() {
		return quoteList;
	}


	public void setQuoteList(List<Quote> quoteList) {
		this.quoteList = quoteList;
	}


	public String getStockCode() {
		return stockCode;
	}


	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Stock [id=" + id + ", stockCode=" + stockCode + "]";
	}


}
