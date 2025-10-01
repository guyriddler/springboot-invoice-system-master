package com.rt.springboot.app.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "items_invoices")
public class ItemInvoice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	
	public Double calculateImport() {
		return amount.doubleValue() * product.getPrice();
	}
	
	/*----- Getters & Setters -----*/
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public Integer getAmount() { return amount; }
	
	public void setAmount(Integer amount) { this.amount = amount; }
	
	public Product getProduct() { return product; }
	
	public void setProduct(Product product) { this.product = product; }
}
