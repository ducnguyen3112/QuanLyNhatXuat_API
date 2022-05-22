package com.shoesstation.cuoikididong.entity;

import java.util.ArrayList;
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
@Table(name = "customers")
public class Customer {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "address")
	private String address;
	@Column(name = "email")
	private String email;
	@Column(name = "avatar")
	private String avatar;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private List<DeliveryDocket> deliveryDockets;
	@JoinColumn(name="status")
	private int status;
	public Customer() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<DeliveryDocket> getDeliveryDockets() {
		return deliveryDockets;
	}
	public void setDeliveryDockets(List<DeliveryDocket> deliveryDockets) {
		this.deliveryDockets = deliveryDockets;
	}
	public void addDeliveryDocket(DeliveryDocket deliveryDocket) {
		if (deliveryDockets==null) {
			deliveryDockets=new ArrayList<>();
		}
		deliveryDockets.add(deliveryDocket);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
