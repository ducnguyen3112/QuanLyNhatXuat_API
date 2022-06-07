package com.shoesstation.cuoikididong.dto;

import java.util.List;

public class Statistics {
	private List<TopProduct> listProducts;
	private int inventory;
	private int valueInventory;
	private int numDelivery;
	private int valueDelivery;
	private int numReceived;
	private int valueReceived;

	public Statistics() {
		// TODO Auto-generated constructor stub
	}

	public List<TopProduct> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<TopProduct> listProducts) {
		this.listProducts = listProducts;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getValueInventory() {
		return valueInventory;
	}

	public void setValueInventory(int valueInventory) {
		this.valueInventory = valueInventory;
	}

	public int getNumDelivery() {
		return numDelivery;
	}

	public void setNumDelivery(int numDelivery) {
		this.numDelivery = numDelivery;
	}

	public int getValueDelivery() {
		return valueDelivery;
	}

	public void setValueDelivery(int valueDelivery) {
		this.valueDelivery = valueDelivery;
	}

	public int getNumReceived() {
		return numReceived;
	}

	public void setNumReceived(int numReceived) {
		this.numReceived = numReceived;
	}

	public int getValueReceived() {
		return valueReceived;
	}

	public void setValueReceived(int valueReceived) {
		this.valueReceived = valueReceived;
	}

}
