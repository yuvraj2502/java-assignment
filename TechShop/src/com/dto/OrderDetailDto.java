package com.dto;

public class OrderDetailDto {
	private String productName;
	private int quantity;

	public OrderDetailDto(String productName, int quantity) {
		super();
		this.productName = productName;
		this.quantity = quantity;
	}

	public OrderDetailDto() {
		
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderDetailDto [productName=" + productName + ", quantity=" + quantity + "]";
	}

}
