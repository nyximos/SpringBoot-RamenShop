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



@Entity
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
	private String discription;
	
	@Column(name = "is_sale",nullable = false)
	private BooleanType isSale=BooleanType.Y;
	
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity=MenuGroup.class)
	@JoinColumn(name="menu_group_id")
	private MenuGroup menuGroup;
	
	@OneToMany(mappedBy="menu")
	private List<OrderMenu> orderMenus = new ArrayList<>();
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public BooleanType getIsSale() {
		return isSale;
	}

	public void setIsSale(BooleanType isSale) {
		this.isSale = isSale;
	}

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
		menuGroup.getMenus().add(this);
	}

	public List<OrderMenu> getOrderMenus() {
		return orderMenus;
	}

//	public void setOrderMenus(List<OrderMenu> orderMenus) {
//		this.orderMenus = orderMenus;
//	}

	public void addOrderMenu(OrderMenu orderMenu) {
		this.orderMenus.add(orderMenu);
		orderMenu.setMenu(this);
	}


	Menu(){}

}
