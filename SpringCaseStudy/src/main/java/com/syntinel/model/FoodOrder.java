package com.syntinel.model;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class FoodOrder {

	private int foodOrderId;
	private int orderId;
	private int foodId;
	
	@Size(min=0)
	private int quantity;

	public FoodOrder() {
		
	}

	public int getFoodOrderId() {
		return foodOrderId;
	}

	public void setFoodOrderId(int foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
