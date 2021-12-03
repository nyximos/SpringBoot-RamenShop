package com.ramenshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalesWebController {
	
	@GetMapping("/admin/sales")
	public String sales(Model model) {
//		String root = request.getContextPath();
		
		
		return "sales";
	}
	
}
