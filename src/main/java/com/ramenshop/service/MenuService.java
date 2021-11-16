package com.ramenshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramenshop.data.Menu;
import com.ramenshop.repository.MenuRepository;

@Service
public class MenuService {
	
	@Autowired
	public MenuRepository menuRepository;
	
	public void saveMenu(Menu menu) {
		menuRepository.save(menu);
	}
	
	public List<Menu> findMenus(){
		return menuRepository.findAll();
	}
	
	public Optional<Menu> findOne(Long menuId) throws Exception {
		Optional<Menu> menu = menuRepository.findById(menuId);
		if(menu.isPresent()) {
			return menu;
		} else {
			throw new Exception();
		}
	}
	

}