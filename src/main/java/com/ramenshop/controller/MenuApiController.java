package com.ramenshop.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ramenshop.data.Menu;
import com.ramenshop.service.MenuService;

@RestController
@RequestMapping("/api")
public class MenuApiController {

	@Autowired
	MenuService menuService;

	
	// 장바구니 등록
		@PostMapping("/menus/{id}")
		public String putMenu(
				@PathVariable(name = "id") Long id,
				@RequestParam(name = "spicy") String spicy,
//				@nullable @RequestParam(name = "price") int price,
				HttpServletRequest request,
				HttpServletResponse response
				) {
			try {
				
				System.out.println(id);
				System.out.println(spicy);
				
				String[] topings = request.getParameterValues("double");
				for(int i=0; i<topings.length; i++) {
					System.out.println(topings[i]); 
				}
				
				
//				Boolean booleanIsSale = Boolean.parseBoolean(isSale);	
//				Menu menu = menuService.findMenu(id).get();
//				menu.setName(name);
//				menu.setPrice(price);
//				menu.setDiscription(discription);
//				menu.setIsSale(booleanIsSale);
//
//				System.out.println(imgFile.getSize());
//				if(imgFile.getSize() > 0) {
//					String baseDir = request.getSession().getServletContext().getRealPath("\\"); // webapp까지 경로
//					System.out.println(baseDir);
//					String filePath = baseDir + "\\imgs\\" + imgFile.getOriginalFilename();
//					imgFile.transferTo(new File(filePath)); // 해당 경로에 이미지 파일 저장
//					
//					
//					
//					String imgName = imgFile.getOriginalFilename();
//					menu.setImgName(imgName);
//					menu.setImgUrl(filePath);
//				}
//				
//				menu.setMenuGroup(menuService.findMenuGroup(Long.parseLong(menuGroupId)).get());
//
//				menuService.saveMenu(menu);
//
//				
//				response.sendRedirect("/admin/menus");

				return "등록성공";
			} catch (Exception e) {
				e.printStackTrace();
				return "등록에러";
			}

		}

	
}
