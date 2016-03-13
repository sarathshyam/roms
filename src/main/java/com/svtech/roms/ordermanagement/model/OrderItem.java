package com.svtech.roms.ordermanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.ordermanagement.dto.OrderItemDetailsDto;
import com.svtech.roms.ordermanagement.dto.OrderItemDto;

@Entity
@Table(name = "roms_live_order_dtls")
public class OrderItem implements Serializable {
	
	/**
	 * Surrogate key of orderitem
	 */
	@Id
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequenceName", value = "roms_order_dtls_pk_seq") })
	@GeneratedValue(generator = "sequence", strategy=GenerationType.SEQUENCE)	
	@Column(name="order_item_id")
	private long orderItemId;	
	
	
	@Column(name="item_name")
	private String itemName;
	
	/**
	 * discount for the item
	 */
	@Column(name="discount")
	private double discount;
	
	/**
	 * = itemPrice*quantity - discount
	 */
	@Column(name="item_total_price")
	private double itemTotalPrice;

	/**
	 * id of the food item
	 */
	@Column(name="food_item_id")
	private long foodItemId;
	
	@Column(name="item_price")
	private double itemPrice;
	
	@Column(name="item_quantity")
	private int itemQuantity;
	
	@Column(name="item_remarks")
	private String remarks;
	
	@ManyToOne
    @JoinColumn(name="order_id", nullable=false)
	private Order order;

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}	

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(double itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	public OrderItem(){
		
	}
	
	public OrderItem(FoodItem foodItem, int quantity, Order order){
		
		this.order = order;
		
		this.foodItemId = foodItem.getId();
		this.itemName = foodItem.getItemName();
		this.itemPrice = foodItem.getPrice();
		this.itemQuantity = quantity;
		
		this.itemTotalPrice = itemPrice*quantity;		
	}
	
	public OrderItemDetailsDto getOrderItemDetailsDtos(){
		OrderItemDetailsDto orderItemDetailsDto = new OrderItemDetailsDto();
		orderItemDetailsDto.setDiscount(discount);
		orderItemDetailsDto.setFoodItemId(foodItemId);
		orderItemDetailsDto.setItemName(itemName);
		orderItemDetailsDto.setItemPrice(itemPrice);
		orderItemDetailsDto.setItemQuantity(itemQuantity);
		orderItemDetailsDto.setItemTotalPrice(itemTotalPrice);
		orderItemDetailsDto.setOrderItemId(orderItemId);
		orderItemDetailsDto.setRemarks(remarks);
		
		return orderItemDetailsDto;
	}
	
	
	

}
