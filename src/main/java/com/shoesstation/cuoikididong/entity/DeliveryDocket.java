package com.shoesstation.cuoikididong.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	@Column(name = "employee_id")
	private int employeeId;
	@Column(name = "customer_id")
	private int customerId;
	@Column(name = "status")
	private int status;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
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
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	public void addDeliveryDocketDetail(DeliveryDocketDetail deliveryDocketDetail) {
		if (deliveryDocketDetails==null) {
			deliveryDocketDetails=new ArrayList<>();
		}
		deliveryDocketDetails.add(deliveryDocketDetail);
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int emloyeeId) {
		this.employeeId = emloyeeId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
