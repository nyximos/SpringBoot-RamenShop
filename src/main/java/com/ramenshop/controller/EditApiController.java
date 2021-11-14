package com.ramenshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramenshop.data.Menu;
import com.ramenshop.data.Result;
import com.ramenshop.repository.MenuRepository;

@RestController
@RequestMapping("/api")
public class EditApiController {
	@Autowired
	MenuRepository menuRepository;
	
	
	@PostMapping("/admin/edit")
	public Result addMenu(@RequestBody Menu menu) {
		System.out.println("컨트롤러들어옴");
		menuRepository.save(menu);
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
