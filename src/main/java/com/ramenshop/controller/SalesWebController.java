package com.ramenshop.controller;

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
	
	//전체기간 통계 조회
	@GetMapping("/admin/sales")
	public String sales(
			Model model
			) {
		String fromdate = "20000101";
		String todate = "20300101";
		model.addAttribute("menus", menuService.findMenus());
		model.addAttribute("sales", orderMenuRepository.selectAllSQL(fromdate, todate));
		
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
	
	//기간,메뉴 선택하여 통계 조회
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
		
		//정확한 날짜계산을 위해 todate에 하루를 더해서 검색
		String newtodate = todate.replace("-","");
		int newinttodate = Integer.parseInt(newtodate);
		newinttodate+=1;
		todate = Integer.toString(newinttodate);
		
		model.addAttribute("menus", menuService.findMenus());
		if(menu.equals("전체")) {
			model.addAttribute("sales", orderMenuRepository.selectAllSQL(fromdate,todate));
			
		}else {
			model.addAttribute("sales", orderMenuRepository.selectAllSQL(fromdate,todate,menu));
			
		}
		String length = "1";
		model.addAttribute("daySales", orderMenuRepository.selectAllSQL(length));
		length = "7";
		model.addAttribute("weekSales", orderMenuRepository.selectAllSQL(length));
		length = "30";
		model.addAttribute("monthSales", orderMenuRepository.selectAllSQL(length));
		length = "365";
		model.addAttribute("yearSales", orderMenuRepository.selectAllSQL(length));

		model.addAttribute("result", "성공하였습니다.");

		
		
		return "sales";
	}
	
}
