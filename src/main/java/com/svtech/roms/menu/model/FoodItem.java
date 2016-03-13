package com.svtech.roms.menu.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "roms_menu_fooditem")
public class FoodItem implements Serializable {

	@Id
	/*@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequenceName", value = "tao_menu_id_seq") })
	@GeneratedValue(generator = "sequence", strategy=GenerationType.SEQUENCE)*/
	@Column(name = "item_id")
	private long id;

	@Column(name = "item_code")
	private String itemCode;

	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "category_name")
	private String category;

	@Column(name = "price")
	private double price;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private FoodItemStatus foodItemStatus;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public FoodItemStatus getFoodItemStatus() {
		return foodItemStatus;
	}

	public void setFoodItemStatus(FoodItemStatus foodItemStatus) {
		this.foodItemStatus = foodItemStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((foodItemStatus == null) ? 0 : foodItemStatus.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((itemCode == null) ? 0 : itemCode.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodItem other = (FoodItem) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (foodItemStatus != other.foodItemStatus)
			return false;
		if (id != other.id)
			return false;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", itemCode=" + itemCode + ", itemName=" + itemName + ", category=" + category
				+ ", price=" + price + ", foodItemStatus=" + foodItemStatus + "]";
	}
	
	

}
