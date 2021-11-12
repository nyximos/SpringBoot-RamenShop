package com.ramenshop.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
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
	
	@ManyToOne(targetEntity=Order.class)
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne(targetEntity=Menu.class)
	@JoinColumn(name="menu_id")
	private Menu menu;
	
	@OneToMany(mappedBy="orderMenu")
	private List<OrderMenuOption> orderMenuOptions = new ArrayList<>();
	
}
