package com.shoesstation.cuoikididong.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@Entity
@Table(name = "delivery_docket")
public class DeliveryDocket {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "created_at")
	private Date CreatedAt;
	@Column(name = "status")
	private int status;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="delivery_docket_id")
	private List<DeliveryDocketDetail> deliveryDocketDetails;
	public DeliveryDocket() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<DeliveryDocketDetail> getDeliveryDocketDetails() {
		return deliveryDocketDetails;
	}
	public void setDeliveryDocketDetails(List<DeliveryDocketDetail> deliveryDocketDetails) {
		this.deliveryDocketDetails = deliveryDocketDetails;
	}
	
}
