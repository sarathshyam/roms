package com.svtech.roms.ordermanagement.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.ordermanagement.dto.OrderDto;
import com.svtech.roms.ordermanagement.dto.OrderItemDetailsDto;

/**
 * 
 * The root class of the Order domain model. Contains the state and logic 
 * 
 * 
 * 
 * @author Sarath
 *
 */
@Entity
@Table(name = "roms_live_order")
public class Order {
	
	@Id
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequenceName", value = "roms_order_pk_seq") })
	@GeneratedValue(generator = "sequence", strategy=GenerationType.SEQUENCE)
	@Column(name = "order_id")
	private long orderId;
	
	@Column(name = "order_taken_time")
	private Calendar orderTakenTime;	
	
	/**
	 * The unique Id of a table in the restaurant
	 */
	@Column(name = "table_id")
	private String tableId;
	
	/**
	 * The waiter(user) who created this order
	 */
	@Column(name = "waiter_id")
	private String waiterId;
	
	/**
	 * when the order status changed to READY
	 */
	@Column(name = "order_ready_time")
	private Calendar orderReadyTime;
	
	/**
	 * When the order is finally Billed
	 */
	@Column(name = "order_billed_time")
	private Calendar orderBilledTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	private OrderStatus orderStatus;
	
	/**
	 * price before discounts
	 */
	@Column(name = "total_actual_price")
	private double totalActualPrice;
	
	/**
	 * discount applied for the order
	 */
	@Column(name = "discount")
	private double discount;
	
	/**
	 * totalActualPrice - discounts
	 */
	@Column(name = "total_final_price")
	private double totalFinalPrice;	
	
	@Column(name = "remarks")
	private String remarks;
	
	@OneToMany(mappedBy = "order", cascade={CascadeType.ALL})
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	
	
	
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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Order(){
		
	}
	
	
	public Order(String tableId, String waiterId){		
		this.orderStatus = OrderStatus.NEW;
		this.tableId = tableId;
		this.waiterId = waiterId;
		this.orderTakenTime = Calendar.getInstance();			
	}
	
	/**
	 * Realises the addOrderItem to existing order use case
	 * @param foodItem
	 * @param quantity
	 * @param remarks
	 */
	public void addOrderItem(FoodItem foodItem, int quantity, String remarks){
		OrderItem orderItem = new OrderItem(foodItem, quantity, this);
		orderItem.setRemarks(remarks);
				
		/*
		 * Adding to existing order
		 */
		orderItems.add(orderItem);
		
		/*
		 * re-calculate price
		 */
		recalculateOrderTotal();
		
	}
	
	/**
	 * NOTE: 
	 * 
	 * Excuse 1: Lame but currency is assumed as INR 
	 * Excuse 2: Currency rounding not done, since price & quantity will be whole numbers!
	 *  
	 * Discounts not considered now
	 */
	private void recalculateOrderTotal(){

		double totalPrice = 0.0;
		
		for(OrderItem orderItem : orderItems){
			totalPrice += orderItem.getItemTotalPrice();
		}
		
		setTotalActualPrice(totalPrice);
		setTotalFinalPrice(totalPrice);
	}
	
	/**
	 * Get the state of the Order model
	 * @return
	 */
	public OrderDto getOrderDto(){
		OrderDto orderDto = new OrderDto();
		orderDto.setDiscount(discount);
		orderDto.setOrderBilledTime(orderBilledTime);
		orderDto.setOrderId(orderId);		
		orderDto.setOrderReadyTime(orderReadyTime);
		orderDto.setOrderStatus(orderStatus);
		orderDto.setOrderTakenTime(orderTakenTime);
		orderDto.setRemarks(remarks);
		orderDto.setTableId(tableId);
		orderDto.setTotalActualPrice(totalActualPrice);
		orderDto.setTotalFinalPrice(totalFinalPrice);
		orderDto.setWaiterId(waiterId);
		
		List<OrderItemDetailsDto> orderItemDetails = new ArrayList<OrderItemDetailsDto>();
		for(OrderItem orderItem : orderItems){
			orderItemDetails.add(orderItem.getOrderItemDetailsDtos());
		}
		
		orderDto.setOrderItems(orderItemDetails);
		
		return orderDto;
	}
	
	
	
	

}
