package com.svtech.roms.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class RomsMainController {

	
	/**
	 * Method will be called in initial page load at GET /menu
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setupLandingPage(Model model) {		
		return "index";
	}
}
