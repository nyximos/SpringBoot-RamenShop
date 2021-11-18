package com.ramenshop.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;
import com.ramenshop.service.MenuService;

@RestController("/api")
public class MenuApiController {
	
	@Autowired
	MenuService menuService;

    @PostMapping("/admin/edit")
    public String write(
    		@RequestParam(name="name") String name,
    		@RequestParam(name="price") int price,
    		@RequestParam(name="discription") String discription,
    		@RequestParam(name="imgFile") MultipartFile imgFile,
    		@RequestParam(name="menuGroupId") String menuGroupId,
    		HttpServletRequest request,
    		HttpServletResponse response
    		) {
    	
    	//String name, int price, String discription, String imgUrl, Menugroup menuGroupId
    	
    	
        try { // 이미지 경로를 DB에 셋팅
            String baseDir = request.getSession().getServletContext().getRealPath("\\"); //webapp까지 경로
            System.out.println(baseDir);
            String filePath = baseDir + "\\imgs\\" + imgFile.getOriginalFilename();
            imgFile.transferTo(new File(filePath)); // 해당 경로에 이미지 파일 저장
            
            MenuGroup menuGroup = new MenuGroup();
            menuGroup.setId(Long.parseLong(menuGroupId));
            String imgName = imgFile.getOriginalFilename();
            
            Menu menu = new Menu(name, price,discription, menuGroup, imgName);
            
            
            menu.setImgUrl(filePath);
            menuService.saveMenu(menu);
            response.sendRedirect("/admin/list");
            
            return "등록성공";
         } catch(Exception e) {
            e.printStackTrace();
            return "등록에러";
         }
    	
    }
    
    
    @DeleteMapping("/admin/edit")
    public String write(@RequestParam(name="id") Long id, HttpServletResponse response) {
    	try {
    	menuService.deleteMenu(id);
    	response.sendRedirect("/admin/list");
    	return "삭제성공";
    	} catch(Exception e) {
    		e.printStackTrace();
    		return "삭제실패";
    	}
    }
}
