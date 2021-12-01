package com.example.backend.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.model.Administrateur;
import com.example.backend.service.AdminService;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	@Autowired
	private AdminService adminService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Administrateur admin = adminService.findByEmail(username);
		List<SimpleGrantedAuthority>authorities = new ArrayList<>();
		admin.getRoles().forEach(role->
		authorities.add(new SimpleGrantedAuthority(role.getLibelle())));
		return new User(admin.getEmail(),admin.getPassword(),authorities);
	}

}
