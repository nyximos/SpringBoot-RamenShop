package com.ramenshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ramenshop.service.MenuService;

@Controller
public class WebController {
	
	@Autowired
	MenuService menuService;

	
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
	
	
	
	@GetMapping("/cart")
	public String getMenus(Model model,HttpServletRequest request) {
		
		model.addAttribute("cart",request.getSession().getAttribute("cart"));
        return "cart";
	}

	
	
}
