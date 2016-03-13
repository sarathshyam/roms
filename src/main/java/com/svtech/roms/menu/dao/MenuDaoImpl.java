package com.svtech.roms.menu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.menu.model.FoodItemUpdateRequest;


@Repository
@Transactional
public class MenuDaoImpl implements MenuDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<FoodItem> listMenu() {
		List<FoodItem> items = entityManager.createQuery("from FoodItem", FoodItem.class).getResultList();
        return items;
	}

	
	public void addItem(FoodItem foodItem) {
		//TODO: Duplicate check for item id, code and name		
		entityManager.persist(foodItem);		
	}
	
	
	public FoodItem getFoodItemById(Long foodItemId){
		System.out.println("Reading from DB....");
		return entityManager.find(FoodItem.class, foodItemId);
	}

	public void updateFoodItem(FoodItem foodItem) {		
		entityManager.merge(foodItem);
	}

}
