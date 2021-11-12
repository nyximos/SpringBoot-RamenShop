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
@Table(name="option_group")
public class OptionGroup {

	@Id
	@Column(name="option_group_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=2)
	private String name;
	
	@ManyToOne(targetEntity=MenuGroup.class)
	@JoinColumn(name="menu_group_id")
	private MenuGroup menuGroup;
	
	@OneToMany(mappedBy="optionGroup")
	private List<Option> options = new ArrayList<>();
	
}
