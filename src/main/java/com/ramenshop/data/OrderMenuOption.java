package com.ramenshop.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="order_menu_option")
public class OrderMenuOption {
	
	@Id
	@Column(name="order_menu_option_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private int count=1;
	
	@ManyToOne(targetEntity=Option.class)
	@JoinColumn(name="option_id")
	private Option option;

	@ManyToOne(targetEntity=OrderMenu.class)
	@JoinColumn(name="order_menu_id")
	private OrderMenu orderMenu;
	
}