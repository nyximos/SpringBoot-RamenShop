package com.ramenshop.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="menu_group")
public class MenuGroup {
	
	@Id
	@Column(name="menu_group_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //int. autoincrement
	private Long id;
	
	@Column(nullable=false,length=3)
	private String name;
	
	@OneToMany(mappedBy="menuGroup") // Menu클래스의 menuGroup필드에 매핑을 해준다는 뜻
	private List<Menu> menus = new ArrayList<>();
	
	@OneToMany(mappedBy="menuGroup")
	private List<OptionGroup> optionGroups = new ArrayList<>();
	
	public MenuGroup() {}
	

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

	public List<Menu> getMenus() {
		return menus;
	}

//	public void setMenus(List<Menu> menus) {
//		this.menus = menus;
//	}
	
	public void addMenus(Menu menu) {
		this.menus.add(menu);
		menu.setMenuGroup(this);
	}

	public List<OptionGroup> getOptionGroups() {
		return optionGroups;
	}

//	public void setOptionGroups(List<OptionGroup> optionGroups) {
//		this.optionGroups = optionGroups;
//	}
	
	public void addOptionGroup(OptionGroup optionGroup) {
		this.optionGroups.add(optionGroup);
		optionGroup.setMenuGroup(this);
	}
	
	
}
