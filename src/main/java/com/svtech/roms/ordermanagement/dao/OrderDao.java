package com.svtech.roms.ordermanagement.dao;

import java.util.List;

import com.svtech.roms.ordermanagement.dto.OrderDto;
import com.svtech.roms.ordermanagement.model.Order;

public interface OrderDao {
	
	/**
	 * To live order table(Order Queue)
	 * @param order
	 */
	public void saveOrder(Order order);
	
	/**
	 * live order table(Order Queue)
	 * @param order
	 */
	public void updateOrder(Order order);
	
	/**
	 * live order table(Order Queue)
	 * @param orderId
	 * @return
	 */
	public Order retrieveOrderById(long orderId);
	
	/**
	 * live order table(Order Queue)
	 * @return
	 */
	public List<Order> listAllOrders();
	
	/**
	 * live order table(Order Queue)
	 * @return
	 */
	public List<Order> listNewOrders();
	
	/**
	 * live order table(Order Queue)
	 * @return
	 */
	public List<Order> listOrderForUser(String userId);
	
	
	

}
