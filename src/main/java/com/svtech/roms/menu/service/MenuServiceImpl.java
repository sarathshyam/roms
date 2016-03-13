package com.svtech.roms.menu.service;


import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.svtech.roms.menu.dao.MenuDao;
import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.menu.model.FoodItemStatus;
import com.svtech.roms.menu.model.FoodItemUpdateRequest;


@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuDao menuDao;
	
	public List<FoodItem> listMenu() {
		return menuDao.listMenu();
	}

	@CacheEvict(value = "menuItemCache", allEntries=true)
	public void addItemToMenu(FoodItem foodItem) {
		menuDao.addItem(foodItem);		
	}

	@Cacheable(value="menuItemCache", key="#foodItemId")
	public FoodItem getFoodItemById(Long foodItemId) {		
		FoodItem foodItem  = menuDao.getFoodItemById(foodItemId);
		Validate.notNull(foodItem, "Item not found");		
		return foodItem;
	}
	
	/**
	 * Only food item status and price can be updated
	 * @param foodItemUpdateRequest
	 */
	@CacheEvict(value = "menuItemCache", allEntries=true)
	public void updateFoodItem(FoodItemUpdateRequest foodItemUpdateRequest){
		FoodItem foodItem = menuDao.getFoodItemById(foodItemUpdateRequest.getId());
		Validate.notNull(foodItem, "Item not found");
		foodItem.setFoodItemStatus(foodItemUpdateRequest.getFoodItemStatus());
		foodItem.setPrice(foodItemUpdateRequest.getPrice());
		menuDao.updateFoodItem(foodItem);
	}

}
