package com.syntinel.dto;

import java.util.ArrayList;

public class MenuOrder {
	public ArrayList<String> itemCounts;
	
	public MenuOrder() {
		super();
		this.itemCounts = new ArrayList<String>(100);
	}

	public ArrayList<String> getItemCounts() {
		return itemCounts;
	}

	public void setItemCounts(ArrayList<String> itemCounts) {
		this.itemCounts = itemCounts;
	}

	@Override
	public String toString() {
		return "MenuOder [itemCounts=" + itemCounts + "]";
	}
	
}
