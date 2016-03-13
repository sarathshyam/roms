package com.svtech.roms.ordermanagement.webapi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.svtech.roms.misc.DeviceType;
import com.svtech.roms.ordermanagement.dto.OrderCreationRequest;
import com.svtech.roms.ordermanagement.dto.OrderDto;

/**
 * 
 * The Order management RESTful services. 
 * This is a reduntant layer to {@code ordermanagement.service} though 
 * 
 * @author Sarath
 *
 */		
@Path("/order/")
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
public interface OrderRestApi {
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})	
	public OrderDto createOrder(@HeaderParam("userId") String userId, OrderCreationRequest orderCreationRequest);
	
	/**
	 * Cancel an order- Usually by waiter
	 * 
	 * @param userId 
	 * @param orderId
	 * @param remarks
	 */
	@POST
	@Path("/{orderId}/_cancel")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})	
	public void cancelOrder(@HeaderParam("userId") String userId, @PathParam("orderId") long orderId, String remarks);
	
	/**
	 * Order fullfilled and ready to be served- By kitchen agent
	 */
	@POST
	@Path("/{orderId}/_fullfill")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})	
	public void fullFillOrder(@HeaderParam("userId") String userId, @PathParam("orderId") long orderId);
	
	/**
	 * Bill the order- the order is removed from current order queue- done by waiter
	 */
	@POST
	@Path("/{orderId}/_bill")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})	
	public void billOrder(@HeaderParam("userId") String userId, @PathParam("orderId") long orderId);
	
	/**
	 * View current orders(NEW,READY)
	 * This is role based.
	 * 
	 * For Waiter- all orders created by him - device type is {@link DeviceType.WAITER_POS}
	 * For Kitchen agent and admins- All orders in pending queue- device type is {@link DeviceType.KITCHEN_POS}
	 * 
	 * NOTE: Since identity module(with user types) is not done, handled using device types instead
	 */
	@GET
	@Path("/")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	public List<OrderDto> viewCurrentOrders(@HeaderParam("userId") String userId, @HeaderParam("deviceType") DeviceType deviceType);
	
	/**
	 * Retrieve order(from order queue) by id
	 * @param orderId
	 * @return
	 */
	@GET
	@Path("/{orderId}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_XML})
	public OrderDto retrieveOrder(@HeaderParam("userId") String userId, @PathParam("orderId") long orderId);

}
