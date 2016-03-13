package com.svtech.roms.ordermanagement.dto;

public class OrderItemDto {
	
	/**
	 * id of the food item
	 */
	private long foodItemId;
	private double itemPrice;
	private int itemQuantity;
	private String remarks;

	public OrderItemDto() {
	}

	

	public long getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(long foodItemId) {
		this.foodItemId = foodItemId;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}