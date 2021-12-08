package com.ramenshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramenshop.data.Admin;
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

	//관리자 등록
	@PostMapping("/admin/members")
	public Result regMember(@RequestBody Admin admin ) {
		Optional<Admin> a = adminRepository.findById(admin.getAdminId());
		if(a.isEmpty()){
			admin.setPassword(passwordEncode().encode(admin.getPassword()));
			adminRepository.save(admin);
			return new Result("ok");	
		}
		return new Result("ng");

	}
	
	//관리자 삭제
	@DeleteMapping("/admin/members")
	public Result delMember(@RequestBody Admin admin ) {
		Optional<Admin> a = adminRepository.findById(admin.getAdminId());
		if(a.isPresent()){
			if(passwordEncode().matches(admin.getPassword(), a.get().getPassword())) {
				adminRepository.deleteById(admin.getAdminId());
				return new Result("ok");				
			}
		}
		return new Result("ng");
	}
	

	
	
}
