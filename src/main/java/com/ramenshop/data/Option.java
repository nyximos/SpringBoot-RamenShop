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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="options")
public class Option {
	
	@Id
	@Column(name="option_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=15)
	private String name;
	
	@Column(nullable=false, columnDefinition = "integer default 0")
	private int price;
	
	@Column(nullable=false, columnDefinition = "integer default 0")
	private int max;
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity=OptionGroup.class)
	@JoinColumn(name="option_group_id")
	private OptionGroup optionGroup;
	
	@OneToMany(mappedBy="option")
	private List<OrderMenuOption> orderMenuOptions = new ArrayList<>();
}
