package com.svtech.roms.menu.dao;

import java.util.List;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.menu.model.FoodItemUpdateRequest;

public interface MenuDao {
	
	public List<FoodItem> listMenu();
	
	public void addItem(FoodItem foodItem);
	
	public FoodItem getFoodItemById(Long foodItemId);
	
	public void updateFoodItem(FoodItem foodItem);

}
