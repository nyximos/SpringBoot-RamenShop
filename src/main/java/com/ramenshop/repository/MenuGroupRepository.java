package com.ramenshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramenshop.data.MenuGroup;

@Repository
public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {

}
