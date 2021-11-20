package com.ramenshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;
import com.ramenshop.repository.MenuGroupRepository;
import com.ramenshop.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	public MenuRepository menuRepository;

	@Autowired
	public MenuGroupRepository menuGroupRepository;

	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public void saveMenu(Menu menu) {
		menuRepository.save(menu);
	}

	public void deleteMenu(Long id) {
		menuRepository.deleteById(id);
	}

	public List<Menu> findMenus() {
		return menuRepository.findAll();
	}
	
	public List<Menu> findMenusByGroup(Long menuGroupId) {
		MenuGroup mg = new MenuGroup();
		mg.setId(menuGroupId);
		return menuRepository.findByMenuGroup(mg);
	}

	public Optional<Menu> findMenu(Long menuId) throws Exception {
		Optional<Menu> menu = menuRepository.findById(menuId);
		if (menu.isPresent()) {
			return menu;
		} else {
			throw new Exception();
		}
	}

	public Optional<MenuGroup> findMenuGroup(Long menuGroupId) throws Exception {

		Optional<MenuGroup> menuGroup = menuGroupRepository.findById(menuGroupId);

		if (menuGroup.isPresent()) {
			return menuGroup;
		} else {
			throw new Exception();
		}
	}

}