package com.svtech.roms.ordermanagement.service;

import java.util.List;

import com.svtech.roms.misc.DeviceType;
import com.svtech.roms.ordermanagement.dto.OrderCreationRequest;
import com.svtech.roms.ordermanagement.dto.OrderDto;

/**
 * Services exposed by Order domain
 * 
 * @author Sarath
 *
 */
public interface OrderService {
	
	/**
	 * Create New Order- Usually by Waiter
	 * @param orderCreationRequest
	 */
	public OrderDto createOrder(String userId, OrderCreationRequest orderCreationRequest);
	
	/**
	 * Cancel an order- Usually by waiter
	 * @param userId
	 * @param orderId
	 * @param remarks
	 */
	public void cancelOrder(String userId, long orderId, String remarks);
	
	/**
	 * Order fullfilled and ready to be served- By kitchen agent
	 * @param orderId
	 */
	public void fullFillOrder(String userId, long orderId);
	
	/**
	 * Bill the order- the order is removed from current order queue- done by waiter
	 * @param orderId
	 */
	public void billOrder(String userId, long orderId);
	
	/**
	 * View current orders(NEW,READY)
	 * This is role based.
	 * 
	 * For Waiter- all orders created by him - device type is {@link DeviceType.WAITER_POS}
	 * For Kitchen agent and admins- All orders in pending queue- device type is {@link DeviceType.KITCHEN_POS}
	 * 
	 * NOTE: Since identity module(with user types) is not done, handled using device types instead
	 */
	public List<OrderDto> viewCurrentOrders(String userId, DeviceType deviceType);
	
	/**
	 * Retrieve order(from order queue) by id
	 * @param orderId
	 * @return
	 */
	public OrderDto retrieveOrder(String userId, long orderId);

}
