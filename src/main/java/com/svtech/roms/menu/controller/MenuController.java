package com.svtech.roms.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.menu.service.MenuService;


@Controller
@RequestMapping("/menu")
@SessionAttributes("foodItem")
public class MenuController {

	@Autowired
	MenuService menuService;

	/**
	 * Bind all employees list
	 */
	@ModelAttribute("allItems")
	public List<FoodItem> populateMenu() {
		List<FoodItem> items = menuService.listMenu();
		return items;
	}

	/**
	 * Method will be called in initial page load at GET /menu
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		FoodItem foodItem = new FoodItem();
		model.addAttribute("foodItem", foodItem);
		return "listMenu";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("foodItem") FoodItem foodItem, BindingResult result,
			SessionStatus status) {

			
		// Store the employee information in database
		menuService.addItemToMenu(foodItem);

		// Mark Session Complete and redirect to URL so that page refresh do not
		// re-submit the form
		status.setComplete();
		return "redirect:menu";
	}

}
