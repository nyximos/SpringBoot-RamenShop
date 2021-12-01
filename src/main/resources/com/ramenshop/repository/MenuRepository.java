package com.ramenshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	public List<Menu> findByMenuGroup(MenuGroup id);
	public List<Menu> findAllByIsSale(boolean bool);	
}
