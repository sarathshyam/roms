package com.svtech.roms.ordermanagement.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.menu.service.MenuService;
import com.svtech.roms.misc.DeviceType;
import com.svtech.roms.ordermanagement.dao.OrderDao;
import com.svtech.roms.ordermanagement.dto.OrderCreationRequest;
import com.svtech.roms.ordermanagement.dto.OrderDto;
import com.svtech.roms.ordermanagement.dto.OrderItemDto;
import com.svtech.roms.ordermanagement.model.Order;
import com.svtech.roms.ordermanagement.model.OrderStatus;

/**
 *  TODO:
 * 1. Authorization
 * 2. Take userId from security context
 * 3. Proper validations and exception
 * 4. Audit
 *
 * @author Sarath
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	MenuService menuService;
	@Autowired
	OrderDao orderDao;

	/**
	 * Creates an Order for a set of order items
	 * @param orderCreationRequest
	 */
	public OrderDto createOrder(String userId, OrderCreationRequest orderCreationRequest) {
		
		Validate.notNull(userId, "User Id cannot be null");
		validateCreationRequest(orderCreationRequest);
		
		//TODO: get user id from security context
		Order order = new Order(orderCreationRequest.getTableId(), userId);
		
		for(OrderItemDto orderItemDto : orderCreationRequest.getOrderItems()){
			FoodItem foodItem = menuService.getFoodItemById(orderItemDto.getFoodItemId());
			Validate.notNull(foodItem, "Invalid Item id");
			
			order.addOrderItem(foodItem, orderItemDto.getItemQuantity(), orderItemDto.getRemarks());
		}
		orderDao.saveOrder(order);
		
		return order.getOrderDto();
		
	}
	

	public void cancelOrder(String userId, long orderId, String remarks) {
		Order order = orderDao.retrieveOrderById(orderId);
		Validate.notNull(order, "Invalid orderId");
		
		order.setOrderStatus(OrderStatus.CANCELLED);
		order.setRemarks(remarks);
		
		//TODO: Remove order for live order queue tables to an archive table
		orderDao.updateOrder(order);
	}

	/**
	 * Current implementation handles only the case of entire Order being ready to be served.
	 * In future, it is better to support individual order item ready cases and notification to
	 * order suppliers etc...
	 */
	public void fullFillOrder(String userId, long orderId) {
		Order order = orderDao.retrieveOrderById(orderId);
		
		order.setOrderStatus(OrderStatus.READY);
		order.setOrderReadyTime(Calendar.getInstance());	
				
		orderDao.updateOrder(order);
		
	}

	public void billOrder(String userId, long orderId) {
		Order order = orderDao.retrieveOrderById(orderId);
		
		order.setOrderStatus(OrderStatus.BILLED);
		order.setOrderBilledTime(Calendar.getInstance());
		
		//TODO: Integration with Printer for bill generation
		
		//TODO: Remove order for live order queue tables to an archive table
		orderDao.updateOrder(order);
		
	}
	
	/**
	 * View current orders(NEW,READY)
	 * This is role based.
	 * 
	 * For Waiter- all orders created by him - device type is {@link DeviceType.WAITER_POS}
	 * For Kitchen agent and admins- All orders in pending queue- device type is {@link DeviceType.KITCHEN_POS}
	 * 
	 * NOTE: Since identity module(with user types) is not done, handled using device types instead
	 */
	public List<OrderDto> viewCurrentOrders(String userId, DeviceType deviceType) {
		List<Order> orders = null;
		if(DeviceType.KITCHEN_POS.equals(deviceType)){
			orders = orderDao.listNewOrders();
		}else{
			orders = orderDao.listOrderForUser(userId);
		}
		
		return convertToDtos(orders);
		
	}

	/**
	 * TODO: Authorization and userId from context
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public OrderDto retrieveOrder(String userId, long orderId) {
		Order order = orderDao.retrieveOrderById(orderId);
		Validate.notNull(order, "Invalid orderId");
		
		return order.getOrderDto();
	}
	
	private void validateCreationRequest(OrderCreationRequest orderCreationRequest){
		
		Validate.notNull(orderCreationRequest.getTableId(), "Table ID cannot be null");
		Validate.notEmpty(orderCreationRequest.getOrderItems(), "Order items cannot be empty");		
		
		for(OrderItemDto orderItemDto : orderCreationRequest.getOrderItems()){
			Validate.isTrue(orderItemDto.getItemQuantity() > 0, "Wrong quantity");
		}
		
	}
	
	private List<OrderDto> convertToDtos(List<Order> orders){
		if(orders == null || orders.isEmpty()){
			return null;
		}
		
		List<OrderDto> orderDtos = new ArrayList<OrderDto>(orders.size());
		for(Order order : orders){
			OrderDto orderDto = order.getOrderDto();
			orderDtos.add(orderDto);
		}
		return orderDtos;
	}

}
