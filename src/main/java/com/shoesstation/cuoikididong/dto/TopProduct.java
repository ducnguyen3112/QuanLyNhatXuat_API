package com.shoesstation.cuoikididong.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopProduct {
	@Id
	private int id;
	private String name;
	private int value;
	public String getName() {
		return name;
	}
	public TopProduct() {
		// TODO Auto-generated constructor stub
	}
	
	public TopProduct(String name, int value,int id) {
		super();
		this.name = name;
		this.value = value;
		this.id=id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
