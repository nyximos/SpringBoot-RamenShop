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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="menu_group")
public class MenuGroup {
	
	@Id
	@Column(name="menu_group_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //int. autoincrement
	private Long id;
	
	@Column(nullable=false,length=3)
	private String name;
	
	@OneToMany(mappedBy="menuGroup") // Menu클래스??의 menu필드??에 매핑을 해준다는 뜻
	private List<Menu> menus = new ArrayList<>();
	
	@OneToMany(mappedBy="menuGroup")
	private List<OptionGroup> optionGroups = new ArrayList<>();
}
