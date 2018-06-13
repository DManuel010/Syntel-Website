package com.syntinel.model;

public class Food {

	private int foodId;
	private String name;
	private String foodGroup;
	private double price;
	private String description;
	private int stock;
	private String image;
	
	

	public Food() {
		
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFoodGroup() {
		return foodGroup;
	}

	public void setFoodGroup(String foodGroup) {
		this.foodGroup = foodGroup;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", name=" + name + ", foodGroup=" + foodGroup + ", price=" + price
				+ ", description=" + description + ", stock=" + stock + ", image=" + image + "]";
	}
	
}
