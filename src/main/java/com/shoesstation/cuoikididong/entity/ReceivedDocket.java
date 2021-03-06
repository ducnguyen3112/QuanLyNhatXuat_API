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

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "received_docket")
public class ReceivedDocket {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "employee_id")
	@NonNull
	private int employeeId;
	@Column(name = "supplier_name")
	private String supplierName;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="received_docket_id")
	private List<ReceivedDocketDetail> receivedDocketDetails;
	public ReceivedDocket() {
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
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public List<ReceivedDocketDetail> getReceivedDocketDetails() {
		return receivedDocketDetails;
	}
	public void setReceivedDocketDetails(List<ReceivedDocketDetail> receivedDocketDetails) {
		this.receivedDocketDetails = receivedDocketDetails;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int emloyeeId) {
		this.employeeId = emloyeeId;
	}
	
	
}
