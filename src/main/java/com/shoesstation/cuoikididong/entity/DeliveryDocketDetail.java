package com.shoesstation.cuoikididong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_docket_detail")
public class DeliveryDocketDetail {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "price")
	private int price;
	@Column(name="delivery_docket_id")
	private int deliveryDocketId;
	@Column(name = "product_id")
	private int productId;
	
	public DeliveryDocketDetail() {
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

	public int getDeliveryDocketId() {
		return deliveryDocketId;
	}

	public void setDeliveryDocketId(int deliveryDocketId) {
		this.deliveryDocketId = deliveryDocketId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
