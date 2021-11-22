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
public class AdminMenuWebController {
	
	@Autowired
	MenuService menuService;

	//**어드민 메뉴 페이지**
	@GetMapping("/admin/menus")
    public String list(Model model) {
		List<Menu> menus = menuService.findMenus();
		model.addAttribute("menus", menus);
        return "admin-menus";
    }
	
	//카테고리별 메뉴 보기
	@GetMapping("/admin/menus/group/{id}")
    public String groupList(Model model,@PathVariable Long id) {
		List<Menu> menus = menuService.findMenusByGroup(id);
		
		model.addAttribute("menus", menus);
        return "admin-menus";
    }
    
	//메뉴 등록 페이지
	@GetMapping("/admin/menus/post")
    public String post() {
        return "post";
    }
	
	//메뉴 수정 페이지
	@GetMapping("/admin/menus/{id}")
    public String post(Model model, @PathVariable Long id) {
    	try {
			menuService.findMenu(id).ifPresent(o -> model.addAttribute("menu", o));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return "admin-menu";
    }
	
	//메뉴 삭제
    @PostMapping("/admin/menus/delete/{id}")
    public String delList(Model model,@PathVariable Long id) {
      menuService.deleteMenu(id);
      List<Menu> menus = menuService.findMenus();
      model.addAttribute("menus", menus);
        return "admin-menus";
    }

    
    

	
}
