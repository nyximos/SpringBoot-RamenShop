package com.ramenshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;
import com.ramenshop.repository.MenuRepository;
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
		
//		List<MenuDto> menus = menuService.getMenuList();
//		model.addAttribute("menus", menus);
		return "menus";
	}
	
	@GetMapping("/admin/list")
    public String list(Model model) {
		List<Menu> menus = menuService.findMenus();
		model.addAttribute("menus", menus);
        return "list";
    }
	@GetMapping("/admin/list/group/{id}")
    public String groupList(Model model,@PathVariable Long id) {
		List<Menu> menus = menuService.findMenusByGroup(id);
		
		model.addAttribute("menus", menus);
        return "list";
    }
    
    @PostMapping("/admin/list/{id}")
    public String delList(Model model,@PathVariable Long id) {
      System.out.println("컨트롤러안");
      menuService.deleteMenu(id);
      List<Menu> menus = menuService.findMenus();
      model.addAttribute("menus", menus);
        return "list";
    }

    @GetMapping("/admin/list/{id}")
    public String post(Model model, @PathVariable Long id) {
    	try {
//			Optional<Menu> menu = menuService.findMenu(id);
			menuService.findMenu(id).ifPresent(o -> model.addAttribute("menu", o));
//			model.addAttribute("menu", menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return "menu";
    }
    
    @GetMapping("/admin/list/post")
    public String post() {
        return "post";
    }
    

	
}
