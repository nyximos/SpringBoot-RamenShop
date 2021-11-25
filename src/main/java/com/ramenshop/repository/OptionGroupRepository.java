package com.ramenshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramenshop.data.MenuGroup;
import com.ramenshop.data.OptionGroup;

public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long>{

	List<OptionGroup> findOptionGroupByMenuGroup(MenuGroup id);
}
