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

@Entity
@Table(name="option_group")
public class OptionGroup {

	@Id
	@Column(name="option_group_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=6)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity=MenuGroup.class)
	@JoinColumn(name="menu_group_id")
	private MenuGroup menuGroup;
	
	@OneToMany(mappedBy="optionGroup")
	private List<Option> options = new ArrayList<>();

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

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
		menuGroup.getOptionGroups().add(this);
	}

	public List<Option> getOptions() {
		return options;
	}

//	public void setOptions(List<Option> options) {
//		this.options = options;
//	}
	
	public void addOption(Option option) {
		this.options.add(option);
		option.setOptionGroup(this);
	}
	
	
	
}
