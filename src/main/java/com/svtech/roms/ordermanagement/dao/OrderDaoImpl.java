package com.svtech.roms.ordermanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.ordermanagement.dto.OrderDto;
import com.svtech.roms.ordermanagement.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void saveOrder(Order order) {
		entityManager.persist(order);
	}

	public void updateOrder(Order order) {
		entityManager.merge(order);

	}

	public Order retrieveOrderById(long orderId) {
		return entityManager.find(Order.class, orderId);
	}

	/**
	 * Assuming orders are archived from live table each day			
	 */
	public List<Order> listAllOrders() {
		return entityManager.createQuery("from Order", Order.class).getResultList();		
	}

	public List<Order> listNewOrders() {
		return entityManager.createQuery("from Order where Order.orderStatus = NEW", Order.class).getResultList();		
	}

	public List<Order> listOrderForUser(String userId) {
		return entityManager.createQuery("from Order where Order.waiterId = " +userId, Order.class).getResultList();
	}

}
