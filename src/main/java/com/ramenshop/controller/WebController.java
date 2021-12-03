package com.ramenshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ramenshop.data.CartMenu;
import com.ramenshop.data.Menu;
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
