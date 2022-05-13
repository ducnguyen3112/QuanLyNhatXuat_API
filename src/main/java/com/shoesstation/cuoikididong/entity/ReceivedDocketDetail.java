package com.shoesstation.cuoikididong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "received_docket_detail")
public class ReceivedDocketDetail {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "price")
	private int price;
	@Column(name = "received_docket_id")
	private int receivedDocketId;
	@Column(name = "product_id")
	private int productId;
	public ReceivedDocketDetail() {
		// TODO Auto-generated constructor stub
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


	public int getReceivedDocketId() {
		return receivedDocketId;
	}


	public void setReceivedDocketId(int receivedDocketId) {
		this.receivedDocketId = receivedDocketId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
