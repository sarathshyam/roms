package com.svtech.roms.menu.model;

import java.io.Serializable;

public class FoodItemUpdateRequest implements Serializable {
	
	
	private long id;
	private FoodItemStatus foodItemStatus;
	private double price;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public FoodItemStatus getFoodItemStatus() {
		return foodItemStatus;
	}
	public void setFoodItemStatus(FoodItemStatus foodItemStatus) {
		this.foodItemStatus = foodItemStatus;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "FoodItemUpdateRequest [foodItemId=" + id + ", foodItemStatus=" + foodItemStatus + ", price="
				+ price + "]";
	}
	
	

}
