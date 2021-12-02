package com.ramenshop.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ramenshop.data.CartMenu;
import com.ramenshop.data.Menu;
import com.ramenshop.data.Option;
import com.ramenshop.repository.OptionRepository;
import com.ramenshop.service.MenuService;

@RestController
@RequestMapping("/api")
public class MenuApiController {

	@Autowired
	MenuService menuService;
	@Autowired
	OptionRepository optionRepository;
	
	
	// 장바구니 등록
		@PostMapping("/menus/{id}")
		public String putMenu(
				@PathVariable(name = "id") Long id,
				@RequestParam(name = "spicy") String spicy,
				@RequestParam(name = "count") int count,
				HttpServletRequest request,
				HttpServletResponse response
				) {
			try {
				
				System.out.println(id);
				System.out.println(spicy);
				
				Menu menu = menuService.findMenu(id).get();
				
				String[] topings = request.getParameterValues("double");
				List<Option> ol = new ArrayList();

				int price=menu.getPrice();
				Option o;
				for(int i=0; i<topings.length; i++) {
					ol.add(o = optionRepository.findById(Long.parseLong(topings[i])).get());
					price+=o.getPrice();
				}
				price = price*count;
				
				
				CartMenu C = new CartMenu(menu,ol,price,count);

				HttpSession session = request.getSession();
				
				System.out.println("넣기전"+session.getAttribute("cart"));

				if(session.getAttribute("cart")!=null) {
					List<CartMenu> m1 = (List<CartMenu>) session.getAttribute("cart");
					m1.add(C);
					session.setAttribute("cart", m1);
					System.out.println("생성후");

				}else {
					List<CartMenu> m = new ArrayList<>();
					m.add(C);
					session.setAttribute("cart", m);
					System.out.println("생성전");

				}		
				
				
				System.out.println("넣기후"+session.getAttribute("cart"));
				
				response.sendRedirect("/menus");

				return "등록성공";
			} catch (Exception e) {
				e.printStackTrace();
				return "등록에러";
			}

		}

		//장바구니 메뉴 삭제
	    @PostMapping("/menus/delete/{id}")
	    public String delSessionList(
	    		@PathVariable Long id,
	    		Model model,
	    		HttpServletRequest request
	    		) {	    	
	    	System.out.println("in");
	    	List<CartMenu> m1 = (List<CartMenu>)request.getSession().getAttribute("cart");
	    	m1.remove(id);
			model.addAttribute("cart",m1);

	    	
	        return "cart";
	    }

	
}
