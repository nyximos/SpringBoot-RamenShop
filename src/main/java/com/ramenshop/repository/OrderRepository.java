package com.ramenshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramenshop.data.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
