package com.ramenshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ramenshop.data.Menu;

@Controller
public class MenuWebController {
	@GetMapping("/menus")
	public String menusList(Model model) {
		
		List<Menu> menus = menuService.findMenus();
		model.addAttribute("menus", menus);
		return "menus";
	}
}
