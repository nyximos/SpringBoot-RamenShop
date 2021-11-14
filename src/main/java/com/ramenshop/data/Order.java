package com.ramenshop.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int amount;
	
	@Column(name="order_time", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asiz/Seoul")
	@CreationTimestamp
	private LocalDateTime ordertime;
	
	@OneToMany(mappedBy="order") // OrderMenu의 order에 매핑을 해준다는 뜻
	private List<OrderMenu> orderMenus = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(LocalDateTime ordertime) {
		this.ordertime = ordertime;
	}

	public List<OrderMenu> getOrderMenus() {
		return orderMenus;
	}

	public void setOrderMenus(List<OrderMenu> orderMenus) {
		this.orderMenus = orderMenus;
	}
	
	
	
}
