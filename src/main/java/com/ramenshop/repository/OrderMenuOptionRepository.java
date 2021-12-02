package com.ramenshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramenshop.data.OrderMenuOption;

@Repository
public interface OrderMenuOptionRepository extends JpaRepository<OrderMenuOption, Long> {

}
