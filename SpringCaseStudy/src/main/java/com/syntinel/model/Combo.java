package com.syntinel.model;

public class Combo {
	
	private int comboId;
	private String name;
	private double price;
	private String description;
	
	public Combo() {
		
	}

	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
