package com.ramenshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;
import com.ramenshop.repository.MenuRepository;

@Service
public class MenuService {
	
	@Autowired
	public MenuRepository menuRepository;
	
	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
	
	
	public void saveMenu(Menu menu) {
		menuRepository.save(menu);
	}
	
	public void deleteMenu(Long id) {
		menuRepository.deleteById(id);
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
