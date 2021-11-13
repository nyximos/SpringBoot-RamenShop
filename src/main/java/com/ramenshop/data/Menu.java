package com.ramenshop.data;

import java.util.ArrayList;
import java.util.List;

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

import com.ramenshop.data.enums.BooleanType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="menu")
public class Menu {

	@Id
	@Column(name="menu_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 15)
	private String name;
	
	@Column(nullable = false)
	private int price;
	
	@Column(name = "img_url",nullable = false)
	private String imgUrl;
	
	@Column(nullable = false)
	private String dscription;
	
	@Column(name = "is_sale",nullable = false)
	private BooleanType isSale=BooleanType.Y;
	
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity=MenuGroup.class)
	@JoinColumn(name="menu_group_id")
	private MenuGroup menuGroup;
	
	@OneToMany(mappedBy="menu")
	private List<OrderMenu> orderMenus = new ArrayList<>();
	
	Menu(){}

}
