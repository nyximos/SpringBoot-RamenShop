package com.ramenshop.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_menu_option")
public class OrderMenuOption {
	
	@Id
	@Column(name="order_menu_option_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private int count=1;
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity=Option.class)
	@JoinColumn(name="option_id")
	private Option option;

	@ManyToOne(fetch = FetchType.LAZY,targetEntity=OrderMenu.class)
	@JoinColumn(name="order_menu_id")
	private OrderMenu orderMenu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
		option.getOrderMenuOptions().add(this);
	}

	public OrderMenu getOrderMenu() {
		return orderMenu;
	}

	public void setOrderMenu(OrderMenu orderMenu) {
		this.orderMenu = orderMenu;
		orderMenu.getOrderMenuOptions().add(this);
	}
	
	
	
}
