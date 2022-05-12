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

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "address")
	private String address;
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "role")
	private int role;
	@Column(name = "password")
	private String password;
	@Column(name = "status")
	private int status;
	@Column(name = "avatar")
	private String avatar;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private List<DeliveryDocket> deliveryDockets;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private List<ReceivedDocket> receivedDockets;
	public Employee() {
		// TODO Auto-generated constructor stub
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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

	public List<ReceivedDocket> getReceivedDockets() {
		return receivedDockets;
	}

	public void setReceivedDockets(List<ReceivedDocket> receivedDockets) {
		this.receivedDockets = receivedDockets;
	}
	
	
}
