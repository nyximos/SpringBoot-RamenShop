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

	Option(){}
	
	public Option(Long id) {
		super();
		this.id = id;
	}

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

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public OptionGroup getOptionGroup() {
		return optionGroup;
	}

	public void setOptionGroup(OptionGroup optionGroup) {
		this.optionGroup = optionGroup;
		optionGroup.getOptions().add(this);
	}

	public List<OrderMenuOption> getOrderMenuOptions() {
		return orderMenuOptions;
	}

//	public void setOrderMenuOptions(List<OrderMenuOption> orderMenuOptions) {
//		this.orderMenuOptions = orderMenuOptions;
//	}
	
	public void addOrderMenuOption(OrderMenuOption orderMenuOption) {
		this.orderMenuOptions.add(orderMenuOption);
		orderMenuOption.setOption(this);
	}
	

}
