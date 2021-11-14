package com.ramenshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/denied")
	public String denied() {
	 return "denied";
	}

//	@GetMapping("/menus")
//	public String menus(Model model) {
//		return "menus";
//	}
	
	@GetMapping("/menus/{menuId}")
	public String menu(@PathVariable int menuId, Model model) {
		return "menu";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		return "cart";
	}
	
	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/admin/members")
	public String members(Model model) {
		return "members";
	}
	
	
	@GetMapping("/admin/sales")
	public String sales(Model model) {
		return "sales";
	}
	
}
