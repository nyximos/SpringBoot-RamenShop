package com.ramenshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ramenshop.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.ramenshop.data.Admin> dbuser = adminRepository.findById(username);
		if(dbuser.isEmpty()) {
			throw new UsernameNotFoundException("Invalid username");
		}
		
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("QUERY"));
		auths.add(new SimpleGrantedAuthority("WRITE"));

		UserDetails ud = User
			.withUsername(dbuser.get().getAdminId())
			.password(dbuser.get().getPassword())
			.authorities(auths)
			.roles("admin")
			.build();
		return ud;
		}
}
