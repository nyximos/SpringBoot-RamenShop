package com.ramenshop.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;




@Entity
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
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
	
	@Column(name = "img_name", nullable = false)
	private String imgName;
	
	@Column(nullable = false)
	private String discription;
	
	
	@Column(nullable = false)
	private boolean isSale = true;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity=MenuGroup.class)
	@JoinColumn(name="menu_group_id")
	private MenuGroup menuGroup;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="menu", cascade=CascadeType.ALL)
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

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
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
	
	

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}



	public boolean getIsSale() {
		return isSale;
	}

	public void setIsSale(boolean isSale) {
		this.isSale = isSale;
	}

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
		menuGroup.getMenus().add(this);
	}
	
	public String getMenuGroupId() {
		String menuGroupId = Long.toString(this.menuGroup.getId());
		return menuGroupId;
	}

	public List<OrderMenu> getOrderMenus() {
		return orderMenus;
	}

	public void setOrderMenus(List<OrderMenu> orderMenus) {
		this.orderMenus = orderMenus;
	}



	public Menu(){}
	
	public Menu(String name, int price, String discription, MenuGroup menuGroupId, String imgName, boolean isSsale){
		this.name = name;
		this.price = price;
		this.discription = discription;
		this.menuGroup = menuGroupId;
		this.imgName = imgName;
		this.isSale = isSale;
	}



}
