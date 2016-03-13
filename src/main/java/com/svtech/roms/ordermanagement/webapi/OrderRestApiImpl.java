package com.svtech.roms.ordermanagement.webapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svtech.roms.misc.DeviceType;
import com.svtech.roms.ordermanagement.dto.OrderCreationRequest;
import com.svtech.roms.ordermanagement.dto.OrderDto;
import com.svtech.roms.ordermanagement.service.OrderService;

/**
 * The Order REST api implementation. 
 * Proper http error codes are not handled and returns 500 for all errors  
 * @author Sarath
 *
 */
@Service("orderRestApi")
public class OrderRestApiImpl implements OrderRestApi {

	@Autowired
	OrderService orderService;

	/**
	 * @param orderCreationRequest
	 */
	public OrderDto createOrder(String userId, OrderCreationRequest orderCreationRequest) {
		return orderService.createOrder(userId, orderCreationRequest);
	}

	/**
	 * 
	 * Cancels an Order- Ususally by a waiter
	 * 
	 * @param userId
	 * @param orderId
	 * @param remarks 
	 */
	public void cancelOrder(String userId, long orderId, String remarks) {
		orderService.cancelOrder(userId, orderId, remarks);
		
	}

	public void fullFillOrder(String userId, long orderId) {
		orderService.fullFillOrder(userId, orderId);		
	}

	public void billOrder(String userId, long orderId) {
		orderService.billOrder(userId, orderId);
	}

	public List<OrderDto> viewCurrentOrders(String userId, DeviceType deviceType) {
		return orderService.viewCurrentOrders(userId, deviceType);
		
	}

	public OrderDto retrieveOrder(String userId, long orderId) {
		return orderService.retrieveOrder(userId, orderId);
	}
	
	

}
