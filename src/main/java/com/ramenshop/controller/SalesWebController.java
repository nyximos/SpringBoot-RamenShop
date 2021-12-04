package com.ramenshop.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ramenshop.repository.OrderMenuRepository;
import com.ramenshop.service.MenuService;

@Controller
public class SalesWebController {
	
	@Autowired
	OrderMenuRepository orderMenuRepository;
	@Autowired
	MenuService menuService;
	
	@GetMapping("/admin/sales")
	public String sales(
			Model model
//			@RequestParam String menu
			) {
		
		String fromdate = "211201";
		String todate = "211231";
		String menu= "콜라";
		
		int a = 211201;
		int b = 211205;
		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = LocalDate.now();
		LocalDateTime d = LocalDateTime.parse("2021-12-31 23:59:59", 
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		model.addAttribute("menus", menuService.findMenus());
		model.addAttribute("sales", orderMenuRepository.selectAllSQL());
		
		String length = "1";
		model.addAttribute("daySales", orderMenuRepository.selectAllSQL(length));
		length = "7";
		model.addAttribute("weekSales", orderMenuRepository.selectAllSQL(length));
		length = "30";
		model.addAttribute("monthSales", orderMenuRepository.selectAllSQL(length));
		length = "365";
		model.addAttribute("yearSales", orderMenuRepository.selectAllSQL(length));
		
		
		return "sales";
	}
	
	@PostMapping("/admin/sales")
	public String selectedSales(
			Model model,
			@RequestParam String menu,
			@RequestParam String fromdate,
			@RequestParam String todate
			) {
		System.out.println(menu);
		System.out.println(fromdate);
		System.out.println(todate);
		
		model.addAttribute("menus", menuService.findMenus());
		model.addAttribute("sales", orderMenuRepository.selectAllSQL(fromdate,todate,menu));
		
		return "sales";
	}
	
}
