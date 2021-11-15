package com.ramenshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramenshop.data.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

}
