package com.ramenshop.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;
import com.ramenshop.service.MenuService;

@RestController
public class MenuApiController {
	
	@Autowired
	MenuService menuService;

    @PostMapping("/post")
    public String write(
    		@RequestParam(name="name") String name,
    		@RequestParam(name="price") int price,
    		@RequestParam(name="discription") String discription,
    		@RequestParam(name="imgFile") MultipartFile imgFile,
    		@RequestParam(name="menuGroupId") String menuGroupId
    		) {
    	
    	//String name, int price, String discription, String imgUrl, Long menuGroupId
    	
    	
        try { // 이미지 경로를 DB에 셋팅
            String baseDir = "C:\\Users\\mycom\\Documents\\GitHub\\SpringBoot-RamenShop\\src\\main\\resources\\static\\imgs\\";
            String filePath = baseDir + imgFile.getOriginalFilename();
            imgFile.transferTo(new File(filePath)); // 해당 경로에 이미지 파일 저장
            
            MenuGroup menuGroup = new MenuGroup();
            menuGroup.setId(Long.parseLong(menuGroupId));
            String imgName = imgFile.getOriginalFilename();
            
            Menu menu = new Menu(name, price,discription, menuGroup, imgName);
            menu.setImgUrl(filePath);
            menuService.saveMenu(menu);
            return "등록성공";
         } catch(Exception e) {
            e.printStackTrace();
            return "등록에러";
         }
    	
    }
}
