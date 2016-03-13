package com.svtech.roms.ordermanagement.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;


public class OrderItemDetailsDto implements Serializable{
	
	/**
	 * Surrogate key of orderitem
	 */
	private long orderItemId;
	
	@JsonIgnore
	private OrderItemDto orderItemDto = new OrderItemDto();
	
	private String itemName;
	
	/**
	 * discount for the item
	 */
	private double discount;
	
	/**
	 * = itemPrice*quantity - discount
	 */
	private double itemTotalPrice;
	

	

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public long getFoodItemId() {
		return orderItemDto.getFoodItemId();
	}

	public void setFoodItemId(long foodItemId) {
		this.orderItemDto.setFoodItemId(foodItemId);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return orderItemDto.getItemPrice();
	}

	public void setItemPrice(double itemPrice) {
		this.orderItemDto.setItemPrice(itemPrice);
	}

	public int getItemQuantity() {
		return orderItemDto.getItemQuantity();
	}

	public void setItemQuantity(int itemQuantity) {
		this.orderItemDto.setItemQuantity(itemQuantity);
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	

	public OrderItemDto getOrderItemDto() {
		return orderItemDto;
	}

	public void setOrderItemDto(OrderItemDto orderItemDto) {
		this.orderItemDto = orderItemDto;
	}

	public double getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(double itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public String getRemarks() {
		return orderItemDto.getRemarks();
	}

	public void setRemarks(String remarks) {
		this.orderItemDto.setRemarks(remarks);
	}	
}
