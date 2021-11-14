package com.ramenshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramenshop.data.Admin;
import com.ramenshop.data.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
