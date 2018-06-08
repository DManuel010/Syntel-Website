package com.syntinel.model;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class FoodOrder {

	private int foodOrderId;
	private int orderId;
	private int foodId;
	private String name;
	private double cost;
	private String orderdate;
	private String expecteddate;
	private String status;
	
	@Size(min=0)
	private int quantity;

	public FoodOrder() {
		
	}

	
	public double getCost() {
		return cost;
	}



	public void setCost(double cost) {
		this.cost = cost;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getExpecteddate() {
		return expecteddate;
	}

	public void setExpecteddate(String expecteddate) {
		this.expecteddate = expecteddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
