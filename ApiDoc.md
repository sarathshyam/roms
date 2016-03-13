REST API Documentation
=====================

Menu APIs
=======

1. view menu
	url: http://localhost:8080/roms/api/menu
	verb: GET

2. add item to menu
	url: http://localhost:8080/roms/api/menu
	verb: POST

	body:

	{
	    "id": 1,
	    "itemCode": "CB",
	    "itemName": "Chicken Biriyani",
	    "category": "Food",
	    "price": 100,
	    "foodItemStatus": "AVAILABLE"
	  }


3. get item by id

	url: http://localhost:8080/roms/api/menu/{id}
	verb: GET

4. UPdate item price or status
	url: http://localhost:8080/roms/api/menu/_update
	verb: POST

	{
	    "id": 1,	   
	    "price": 100,
	    "foodItemStatus": "AVAILABLE"
	  }



Order management APIs:

1. Create Order

	Url: http://localhost:8080/roms/api/order
	Verb: POST

	Headers
		userId : "user1"

    Body:
	{			  
		  "tableId": "TABLE1",		  
		  "remarks": "priority customer",		  		  		  		  		  		  
		  "orderItems": [
		    {
		      "remarks": "less spicy",
		      "foodItemId": 1,		      
		      "itemQuantity": 2
		    },
		    {
		      "remarks": "less spicy",
		      "foodItemId": 2,		      
		      "itemQuantity": 1
		    }
		  ]
		}

2. Retrieve Order

	Url: http://localhost:8080/roms/api/order/{orderId}
	Verb: GET
	Headers
		userId : "user1"
	Body:  	
		{
		  "orderId": 15,
		  "orderTakenTime": 1457458734733,
		  "tableId": "TABLE1",
		  "waiterId": "WAITER1",
		  "orderReadyTime": null,
		  "orderBilledTime": null,
		  "orderStatus": "NEW",
		  "totalActualPrice": 100,
		  "discount": 0,
		  "totalFinalPrice": 100,
		  "remarks": null,
		  "orderItems": [
		    {
		      "orderItemId": 16,
		      "itemName": "Chicken Biriyani",
		      "discount": 0,
		      "itemTotalPrice": 100,
		      "remarks": null,
		      "foodItemId": 1,
		      "itemPrice": 50,
		      "itemQuantity": 2
		    }
		  ]
		}
		

3. Cancel Order

	Url: http://localhost:8080/roms/api/order/{orderId}/_cancel
	Verb: POST
	Headers
		userId : "user1"

	Body:
	
		"Invalid Order"	

4. Fullfill Order

	Url: http://localhost:8080/roms/api/order/{orderId}/_fullfill
	Verb: POST
	Headers
		userId : "user1"

5. Bill Order

	Url: http://localhost:8080/roms/api/order/{orderId}/_bill
	Verb: POST
	Headers
		userId : "user1"	

6. View Orders

	Url: http://localhost:8080/roms/api/order
	Verb: GET
	Headers
		userId : "user1"
		deviceType : "WAITER_POS" or "KITCHEN_POS"		

			