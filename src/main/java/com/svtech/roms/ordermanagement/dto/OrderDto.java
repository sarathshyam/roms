package com.svtech.roms.ordermanagement.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import com.svtech.roms.ordermanagement.model.OrderStatus;

/**
 * Represents an Order
 * @author Sarath
 *
 */
public class OrderDto implements Serializable {	
	
	
	/**
	 * NOTE: 
	 * 
	 * Excuse 1: Lame but currency is assumed as INR 
	 * Excuse 2: Currency rounding not done, since price & quantitu will be whole numbers!
	 */
	
	
	
	
	/**
	 * TODO: 
	 * For easiness and convenience of all users, it would be better to have the order ids
	 * start from 1 each day ?? 
	 */	
	private long orderId;
	
	/**
	 * TODO: Json convertors for Calendar types
	 */
	
	private Calendar orderTakenTime;	
	
	/**
	 * The unique Id of a table in the restaurant
	 */
	private String tableId;
	
	/**
	 * The waiter(user) who created this order
	 */
	private String waiterId;
	
	/**
	 * when the order status changed to READY
	 */
	private Calendar orderReadyTime;
	
	/**
	 * When the order is finally Billed
	 */
	private Calendar orderBilledTime;
	
	private OrderStatus orderStatus;
	
	/**
	 * price before discounts
	 */
	private double totalActualPrice;
	
	/**
	 * discount applied for the order
	 */
	private double discount;
	
	/**
	 * totalActualPrice - discounts
	 */
	private double totalFinalPrice;	
	
	private String remarks;	
	
	private List<OrderItemDetailsDto> orderItems = new ArrayList<OrderItemDetailsDto>();	

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Calendar getOrderTakenTime() {
		return orderTakenTime;
	}

	public void setOrderTakenTime(Calendar orderTakenTime) {
		this.orderTakenTime = orderTakenTime;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(String waiterId) {
		this.waiterId = waiterId;
	}

	public Calendar getOrderReadyTime() {
		return orderReadyTime;
	}

	public void setOrderReadyTime(Calendar orderReadyTime) {
		this.orderReadyTime = orderReadyTime;
	}

	public Calendar getOrderBilledTime() {
		return orderBilledTime;
	}

	public void setOrderBilledTime(Calendar orderBilledTime) {
		this.orderBilledTime = orderBilledTime;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getTotalActualPrice() {
		return totalActualPrice;
	}

	public void setTotalActualPrice(double totalActualPrice) {
		this.totalActualPrice = totalActualPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalFinalPrice() {
		return totalFinalPrice;
	}

	public void setTotalFinalPrice(double totalFinalPrice) {
		this.totalFinalPrice = totalFinalPrice;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<OrderItemDetailsDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDetailsDto> orderItems) {
		this.orderItems = orderItems;
	}
		
}
