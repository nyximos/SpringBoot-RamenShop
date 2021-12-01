package com.ramenshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ramenshop.data.Menu;
import com.ramenshop.data.Option;
import com.ramenshop.repository.MenuRepository;
import com.ramenshop.service.MenuService;

@Controller
public class MenuWebController {
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	MenuRepository menuRepository;
	
	@GetMapping("/menus")
	public String getMenus(Model model) {
		List<Menu> menus = menuService.findAllByIsSale(true);
		model.addAttribute("menus", menus);
        return "menus";
	}
	
	@GetMapping("/menus/group/{id}")
    public String groupList(Model model,@PathVariable Long id) {
		List<Menu> groupMenus = menuService.findMenusByGroup(id);
		List<Menu> saleMenus = menuService.findAllByIsSale(true);
		
		List<Menu> menus = new ArrayList<>();

		for (int i = 0; i < saleMenus.size(); i++ ) {
		  for (int j = 0; j < groupMenus.size(); j++) {
		    if (groupMenus.get(j).getId().equals(saleMenus.get(i).getId())) {
		      menus.add(groupMenus.get(j));
		    }
		  }
		}
		
		model.addAttribute("menus", menus);

		
        return "menus";
    }
	
	@GetMapping("/menus/{id}")
	public String getMenu(Model model, @PathVariable Long id) {
    	try {
			menuService.findMenu(id).ifPresent(o -> model.addAttribute("menu", o));
			// 메뉴 그룹 아이디 보내기
			Long menuGroupId = menuService.findMenuGroupId(id);
			model.addAttribute("menuGroupId", menuGroupId);
			
			
			List<Option> options = menuService.findOptions(id);
			model.addAttribute("options", options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "menu";
	}

}
