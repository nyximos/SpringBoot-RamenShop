package com.ramenshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramenshop.data.Menu;
import com.ramenshop.dto.MenuDto;
import com.ramenshop.service.MenuService;

@Controller
public class MenuWebController {
	
	@Autowired
	MenuService menuService;
	
	public MenuWebController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping("/menus")
	public String menusList(Model model) {
		
		List<MenuDto> menus = menuService.getMenuList();
		model.addAttribute("menus", menus);
		return "menus";
	}
	
    @GetMapping("/list")
    public String list(Model model) {
		List<Menu> menus = menuService.findMenus();
		model.addAttribute("menus", menus);
        return "list";
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }
    
    @PostMapping("/post")
    public String write(MenuDto menuDto) {
    	menuService.saveMenu(menuDto);
    	return "redirect:/list";
    }
	
}
