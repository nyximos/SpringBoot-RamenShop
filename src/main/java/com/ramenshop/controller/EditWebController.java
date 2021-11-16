package com.ramenshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;
import com.ramenshop.repository.MenuGroupRepository;
import com.ramenshop.repository.MenuRepository;

@Controller
public class EditWebController {
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	MenuGroupRepository menuGroupRepository;
	
	@GetMapping("/admin/edit")
	public String menusList(Model model) {
		
		List<Menu> menus = menuRepository.findAll();
		List<MenuGroup> menugroups = menuGroupRepository.findAll();
		
		model.addAttribute("menus", menus);
		model.addAttribute("menugroups", menugroups);
		return "edit";
	}
}
