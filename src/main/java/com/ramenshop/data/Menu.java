package com.ramenshop.data;

import java.util.ArrayList;
import java.util.List;

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

import com.ramenshop.data.enums.BooleanType;



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


	public Menu(){}
	
	Menu(Long id, String name, int price, String imgUrl, String discription, Long menuGroupId){
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgUrl = imgUrl;
		this.discription = discription;
		this.menuGroup.setId(menuGroupId);

	}

	/*
	Menu(MenuBuilder builder){
		this.id = builder.id;
		this.name = builder.name(name)
		
	}
	*/
}

/*
class MenuBuilder{
	private Long id;
	private String name;
	private int price;
	private String imgUrl;
	private String discription;
	private Long menuGroupId;
	
	public MenuBuilder(Long id) {
		this.id = id;
	}
	
	public MenuBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public MenuBuilder setPrice(int price) {
		this.price = price;
		return this;
	}
	
	public MenuBuilder setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
		return this;
	}
	
	public MenuBuilder setDiscription(String discription) {
		this.discription = discription;
		return this;
	}
	
	public MenuBuilder setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
		return this;
	}
	
	public Menu build() {
		return new Menu(this);
	}
	}
	*/
