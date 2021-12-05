package com.ramenshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramenshop.data.CartMenu;
import com.ramenshop.data.Menu;
import com.ramenshop.repository.MenuRepository;
import com.ramenshop.service.MenuService;

@Controller
public class MenuWebController {
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	MenuRepository menuRepository;
	
	//**고객 메뉴 페이지**
	@GetMapping("/menus")
	public String getMenus(Model model,HttpServletRequest request) {
		List<Menu> menus = menuService.findAllByIsSale(true);
		model.addAttribute("menus", menus);
		
		model.addAttribute("cart",request.getSession().getAttribute("cart"));
        return "menus";
	}
	
	//카테고리별 메뉴 페이지
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
	
	//메뉴 옵션,개수 선택 페이지
	@GetMapping("/menus/{id}")
	public String getMenu(Model model, @PathVariable Long id) {
    	try {
			menuService.findMenu(id).ifPresent(o -> model.addAttribute("menu", o));
			Long menuGroupId = menuService.findMenuGroupId(id);
			model.addAttribute("menuGroupId", menuGroupId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "menu";
	}
	
	//장바구니 메뉴 삭제
    @PostMapping("/menus/delete/{id}")
    public String delSessionList(
    		@PathVariable int id,
    		Model model,
    		HttpServletRequest request,
    		HttpServletResponse response
    		) {	    	
			System.out.println("in");
			List<CartMenu> m1 = (List<CartMenu>)request.getSession().getAttribute("cart");
			System.out.println(m1.size());
			m1.remove(id);
			model.addAttribute("cart",m1);
    	System.out.println("out");
        return "cart";
    }

}
