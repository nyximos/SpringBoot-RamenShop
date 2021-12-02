package com.ramenshop.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ramenshop.data.CartMenu;
import com.ramenshop.data.Menu;
import com.ramenshop.data.Option;
import com.ramenshop.data.Order;
import com.ramenshop.data.OrderMenu;
import com.ramenshop.data.OrderMenuOption;
import com.ramenshop.repository.OptionRepository;
import com.ramenshop.repository.OrderMenuOptionRepository;
import com.ramenshop.repository.OrderMenuRepository;
import com.ramenshop.repository.OrderRepository;
import com.ramenshop.service.MenuService;

@Controller
public class OrderWebController {

	@Autowired
	MenuService menuService;
	@Autowired
	OptionRepository optionRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderMenuRepository orderMenuRepository;
	@Autowired
	OrderMenuOptionRepository orderMenuOptionRepository;

	// 주문 등록
	@PostMapping("/menus/cart")
	public String putMenu(
				HttpServletRequest request, 
				HttpServletResponse response
				) {
	
				
				HttpSession session = request.getSession();
		
				if (session.getAttribute("cart") == null) {
					System.out.println("cart null");
				} 
				
				List<CartMenu> m = (List<CartMenu>)session.getAttribute("cart");
				
				int price=0;
				
				for (int i = 0; i < m.size(); i++) {
					
					price+=m.get(i).getPrice();
				}

				
				Order o = new Order();
				o.setAmount(price);
				o.setOrdertime(LocalDateTime.now());
				orderRepository.save(o);

				try {
					for (int i = 0; i < m.size(); i++) {
						
						OrderMenu om = new OrderMenu();
						System.out.println("1");
						om.setCount(m.get(i).getCount());
						System.out.println("2");
						om.setOrderMenuPrice(m.get(i).getPrice());
						System.out.println(m.get(i).getMenu());
//						try {
//							om.setMenu(menuService.findMenu((long)6).get());
//						} catch (Exception e) {
//							System.out.println("3");
//						}
						System.out.println(m.get(i).getMenu().getPrice());
						Menu menu = m.get(i).getMenu();
						om.setMenu(menu);
						System.out.println("4");
						om.setOrder(o);
						System.out.println("5");
						orderMenuRepository.save(om);
						System.out.println("6");
						for(int j =0; j < m.get(i).getOptions().size(); j++) {
							System.out.println("7");
							OrderMenuOption omo = new OrderMenuOption();
							System.out.println("8");
							omo.setOrderMenu(om);
							System.out.println("9");
							Option option = m.get(i).getOptions().get(j);
							omo.setOption(option);
							System.out.println("10");
							orderMenuOptionRepository.save(omo);
						}
					}

				}
				catch (Exception e){
					System.out.print("에러");
				}
				
				return "/cart";

	

	}

}
