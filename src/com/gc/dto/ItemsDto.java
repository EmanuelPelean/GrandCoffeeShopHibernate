package com.gc.dto;

import java.io.Serializable;

public class ItemsDto implements Serializable{

	/**
	 *  the versionID guarantees that we have an object and a table that can
	 * work together
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private String description;
	private int quantity;
	private double price;
	
	public ItemsDto() {
		// you can assign default values
	}
	
	public ItemsDto(String productName, String description, int quantity, double price) {
		this.productName = productName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ItemsDto [productName=" + productName + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

	
}
