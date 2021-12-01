package com.ramenshop.controller;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;
import com.ramenshop.repository.MenuRepository;
import com.ramenshop.service.MenuService;

@RestController
@RequestMapping("/api")
public class AdminMenuApiController {

	@Autowired
	MenuService menuService;

	
	// 메뉴 등록
	@PostMapping("/admin/menus/post")
	public String postMenu(@RequestParam(name = "name") String name, @RequestParam(name = "price") int price,
			@RequestParam(name = "discription") String discription,
			@RequestParam(name = "imgFile") MultipartFile imgFile,
			@RequestParam(name = "menuGroupId") String menuGroupId,
			HttpServletRequest request,
			HttpServletResponse response) {

		// String name, int price, String discription, String imgUrl, Menugroup
		// menuGroupId

		try { // 이미지 경로를 DB에 셋팅
			String baseDir = request.getSession().getServletContext().getRealPath("\\"); // webapp까지 경로
			System.out.println(baseDir);
			String filePath = baseDir + "\\imgs\\" + imgFile.getOriginalFilename();
			imgFile.transferTo(new File(filePath)); // 해당 경로에 이미지 파일 저장


			
			MenuGroup menuGroup = new MenuGroup();
			menuGroup.setId(Long.parseLong(menuGroupId));
			String imgName = imgFile.getOriginalFilename();

			Menu menu = new Menu(name, price, discription, menuGroup, imgName, true);

			menu.setImgUrl(filePath);
			menuService.saveMenu(menu);
			response.sendRedirect("/admin/menus");

			return "등록성공";
		} catch (Exception e) {
			e.printStackTrace();
			return "등록에러";
		}

	}
	
	// 메뉴 수정
	@PostMapping("/admin/menus/{id}")
	public String putMenu(
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "name") String name, @RequestParam(name = "price") int price,
			@RequestParam(name = "discription") String discription,
			@Nullable @RequestParam(name = "imgFile") MultipartFile imgFile,
			@RequestParam(name = "menuGroupId") String menuGroupId,
			@RequestParam(name = "isSale") String isSale,
			HttpServletRequest request,
			HttpServletResponse response
			) {
		try {
			
			Boolean booleanIsSale = Boolean.parseBoolean(isSale);
			
			Menu menu = menuService.findMenu(id).get();
			menu.setName(name);
			menu.setPrice(price);
			menu.setDiscription(discription);
			menu.setIsSale(booleanIsSale);

			System.out.println(imgFile.getSize());
			if(imgFile.getSize() > 0) {
				String baseDir = request.getSession().getServletContext().getRealPath("\\"); // webapp까지 경로
				System.out.println(baseDir);
				String filePath = baseDir + "\\imgs\\" + imgFile.getOriginalFilename();
				imgFile.transferTo(new File(filePath)); // 해당 경로에 이미지 파일 저장
				
				
				
				String imgName = imgFile.getOriginalFilename();
				menu.setImgName(imgName);
				menu.setImgUrl(filePath);
			}
			
			menu.setMenuGroup(menuService.findMenuGroup(Long.parseLong(menuGroupId)).get());

			menuService.saveMenu(menu);

			
			response.sendRedirect("/admin/menus");

			return "등록성공";
		} catch (Exception e) {
			e.printStackTrace();
			return "등록에러";
		}

	}



}
