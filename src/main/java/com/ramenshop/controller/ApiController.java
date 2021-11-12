package com.ramenshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramenshop.data.Admin;
import com.ramenshop.data.Menu;
import com.ramenshop.data.Result;
import com.ramenshop.repository.AdminRepository;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	AdminRepository adminRepository;
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	
	@PostMapping("/admin/members")
	public Result regMember(@RequestBody Admin admin ) {
		System.out.println("컨트롤러안");
		Optional<Admin> a = adminRepository.findById(admin.getAdminId());
		if(a.isEmpty()==true){
			admin.setPassword(passwordEncode().encode(admin.getPassword()));
			adminRepository.save(admin);
			return new Result("ok");			
		}
		return new Result("no");

	}
	@DeleteMapping("/admin/members")
	public Result delMember(@RequestBody Admin admin ) {
		return new Result("ok");
	}
	
	@PostMapping("/admin/edit")
	public Result regMenu(@RequestBody Menu menu ) {
		return new Result("ok");
	}
	
	@PutMapping("/admin/edit")
	public Result modMenu(@RequestBody Menu menu) {
		return new Result("ok");
	}
	
	@DeleteMapping("/admin/edit")
	public Result delMenu(@RequestBody String id) {
		return new Result("ok");
	}

	
}
