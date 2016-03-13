package com.svtech.roms.menu.webapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.menu.model.FoodItemUpdateRequest;
import com.svtech.roms.menu.service.MenuService;

@Service("menuRestApi")
public class MenuRestApiImpl implements MenuRestApi {
	
	@Autowired
	MenuService menuService;

	public List<FoodItem> listMenu() {
		return menuService.listMenu();		
	}

	public FoodItem getFoodItemById(Long foodItemId) {
		return menuService.getFoodItemById(foodItemId);
	}

	public void addItemToMenu(FoodItem foodItem) {
		menuService.addItemToMenu(foodItem);		
	}

	public void update(FoodItemUpdateRequest foodItemUpdateRequest) {
		menuService.updateFoodItem(foodItemUpdateRequest);		
	}

}
