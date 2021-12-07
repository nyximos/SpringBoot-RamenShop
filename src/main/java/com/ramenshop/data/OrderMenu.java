package com.ramenshop.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="order_menu")
public class OrderMenu {
	
	@Id
	@Column(name="order_menu_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="order_menu_price",nullable = false)
	private int orderMenuPrice; // 주문가격
	
	@Column(nullable = false)
	private int count=1;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity=Order.class)
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity=Menu.class)
	@JoinColumn(name="menu_id")
	private Menu menu;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="orderMenu", cascade=CascadeType.ALL)
	private List<OrderMenuOption> orderMenuOptions = new ArrayList<>();

	public OrderMenu(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOrderMenuPrice() {
		return orderMenuPrice;
	}

	public void setOrderMenuPrice(int orderMenuPrice) {
		this.orderMenuPrice = orderMenuPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
		order.getOrderMenus().add(this);
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
		menu.getOrderMenus().add(this);
	}

	public List<OrderMenuOption> getOrderMenuOptions() {
		return orderMenuOptions;
	}

//	public void setOrderMenuOptions(List<OrderMenuOption> orderMenuOptions) {
//		this.orderMenuOptions = orderMenuOptions;
//	}
	
	public void addOrderMenuOption(OrderMenuOption orderMenuOption) {
		this.orderMenuOptions.add(orderMenuOption);
		orderMenuOption.setOrderMenu(this);
	}
	
	

}
