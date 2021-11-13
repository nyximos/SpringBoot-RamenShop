package com.ramenshop.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramenshop.data.Admin;
import com.ramenshop.data.Menu;
import com.ramenshop.data.Result;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@PostMapping("/admin/members")
	public Result regMember(@RequestBody Admin admin ) {
		return new Result("ok");
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
