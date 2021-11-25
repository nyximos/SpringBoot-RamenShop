package com.ramenshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramenshop.data.Menu;
import com.ramenshop.data.MenuGroup;
import com.ramenshop.data.Option;
import com.ramenshop.data.OptionGroup;
import com.ramenshop.repository.MenuGroupRepository;
import com.ramenshop.repository.MenuRepository;
import com.ramenshop.repository.OptionGroupRepository;
import com.ramenshop.repository.OptionRepository;

@Service
public class MenuService {

	@Autowired
	public MenuRepository menuRepository;

	@Autowired
	public MenuGroupRepository menuGroupRepository;
	
	@Autowired
	public OptionGroupRepository optionGroupRepository;
	
	@Autowired
	public OptionRepository optionRepository;

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

	public List<Menu> findAllByIsSale(boolean b) {
		List<Menu> menus = menuRepository.findAllByIsSale(true);
			return menus;
	}
	


	public List<Option> findOptions(Long id) {
		Optional<Menu> menu = menuRepository.findById(id);
		MenuGroup menuGroup = menuGroupRepository.findMenuGroupById(menu.get().getMenuGroup().getId());
		List<OptionGroup> optionGroups = optionGroupRepository.findOptionGroupByMenuGroup(menuGroup);
		List<Option> allOptions = optionRepository.findAll();
		
		List<Option> options = new ArrayList<>();
		
		for (int i = 0; i < optionGroups.size(); i++) {
			for (int j = 0; j < allOptions.size(); j++) {
				if(optionGroups.get(i).getId().equals(allOptions.get(i).getOptionGroup().getId())) {
					options.add(allOptions.get(j));
				}
			}
			
		}
		
		return options;
	}

}