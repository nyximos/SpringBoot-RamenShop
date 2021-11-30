package com.ramenshop.controller;

import java.io.File;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String index(HttpServletResponse response) {
		
		//쿠키 객체 생성, "cookieName"이라는 이름으로 쿠키를 생성하고, 그 값은 "cookieValue"로 설정
		Cookie rememberCookie = new Cookie("cookieName", "cookieVlaue"); 
		
		// 쿠키 경로 설정, "/"는 모든 경로에서 사용하겠다는 뜻
		rememberCookie.setPath("/");
		
		// 쿠키를 유지할 시간 설정(단위 : 초)
		rememberCookie.setMaxAge(60*5);
		
		response.addCookie(rememberCookie);

	
		return "index";
	}
	
	
	@PostMapping("/upload")
	public String uploadSingle(@RequestParam("files") MultipartFile file) throws Exception {
	    String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
	    String basePath = rootPath + "/" + "single";
	    String filePath = basePath + "/" + file.getOriginalFilename();
	    File dest = new File(filePath);
	    file.transferTo(dest); // 파일 업로드 작업 수행
	    return "uploaded";
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
	
	/*
	@GetMapping("/admin/sales")
	public String sales(Model model) {
		String root = request.getContextPath();
		return "sales";
	}
	*/
	
}
