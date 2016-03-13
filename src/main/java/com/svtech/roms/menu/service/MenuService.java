package com.svtech.roms.menu.service;


import java.util.List;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.menu.model.FoodItemUpdateRequest;


public interface MenuService {
	
	public List<FoodItem> listMenu();
	
	public void addItemToMenu(FoodItem foodItem);
	
	public FoodItem getFoodItemById(Long foodItemId);
	
	public void updateFoodItem(FoodItemUpdateRequest foodItemUpdateRequest);

}
