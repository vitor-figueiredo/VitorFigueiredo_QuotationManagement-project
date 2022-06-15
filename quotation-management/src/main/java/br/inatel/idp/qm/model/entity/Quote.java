package br.inatel.idp.qm.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Representa a cotação da ação 
 * relacionando o dia e o preço 
 * @author vitor.figueiredo
 * @since 14 jun 2022
 */
@Entity
public class Quote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@NotNull
	private Stock stock;
	
	
	@NotNull
	private LocalDate date;

	
	@NotNull
	@Positive
	private BigDecimal price;

	
	//construtores...
	
	public Quote() {
	}
	
	public Quote(@NotNull Stock stock, @NotNull LocalDate date, @NotNull @Positive BigDecimal price) {
		super();
		this.stock = stock;
		this.date = date;
		this.price = price;
	}


	//acessores...

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
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
		Quote other = (Quote) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Quote [id=" + id + ", date=" + date + ", price=" + price + "]";
	}
	
	

}
