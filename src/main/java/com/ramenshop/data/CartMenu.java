package com.ramenshop.data;

import java.util.List;

public class CartMenu {
	String menuId;
	List<Option> options;
	int price;
	
	CartMenu(){}

	public CartMenu(String menuId, List<Option> options, int price) {
		super();
		this.menuId = menuId;
		this.options = options;
		this.price = price;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}



	
}
