package com.ramenshop.data;

import java.util.List;

public class CartMenu {
	Menu menu;
	List<Option> options;
	int price;
	int count;
	
	CartMenu(){}

	
	public CartMenu(Menu menu, List<Option> options, int price, int count) {
		super();
		this.menu = menu;
		this.options = options;
		this.price = price;
		this.count = count;
	}


	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
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


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}

	
	


	
}