package com.svtech.roms.menu.webapi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.svtech.roms.menu.model.FoodItem;
import com.svtech.roms.menu.model.FoodItemUpdateRequest;

@Path("/menu/")
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
public interface MenuRestApi {
	
	@GET
	@Path("/")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	public List<FoodItem> listMenu();
	
	@GET
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	public FoodItem getFoodItemById(@PathParam("id") Long foodItemId);
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	public void addItemToMenu(FoodItem foodItem);
	
	@POST
	@Path("/_update")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	public void update(FoodItemUpdateRequest foodItemUpdateRequest);

}
