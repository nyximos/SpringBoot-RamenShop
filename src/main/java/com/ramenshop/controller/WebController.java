package com.ramenshop.controller;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String index() {
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

//	@GetMapping("/menus")
//	public String menus(Model model) {
//		return "menus";
//	}
//	
//	@GetMapping("/menus/{menuId}")
//	public String menu(@PathVariable int menuId, Model model) {
//		return "menu";
//	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		return "cart";
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
