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
	
	public ArrayList<String> getNonZeroItemCounts() {
		
		ArrayList<String> nonZeroItemCounts = new ArrayList<String>();
		for (String count : itemCounts) {
			if (!count.equals("0") ) {
				nonZeroItemCounts.add(count);
			}
			
		}
		return nonZeroItemCounts;
	}

	@Override
	public String toString() {
		return "MenuOder [itemCounts=" + itemCounts + "]";
	}
	
}
