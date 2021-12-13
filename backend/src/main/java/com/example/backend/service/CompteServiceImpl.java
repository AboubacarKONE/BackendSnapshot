package com.example.backend.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.enumeration.Profile;
import com.example.backend.model.Administrateur;
import com.example.backend.model.Roles;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.RoleRepository;
@Service
@Transactional
public class CompteServiceImpl implements CompteService {
	
	private AdminRepository adminRepository;	
	private RoleRepository roleRepository;
	@Autowired
	public CompteServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository) {
		this.adminRepository = adminRepository;
		this.roleRepository = roleRepository;
	}
	@Override
	public List<Administrateur> AddAdminToRole(Profile profile, String roleName) {
	 List<Administrateur> admin = adminRepository.findByProfile(profile);
	 Roles rol = roleRepository.findByLibelle(roleName);
	 admin.stream().forEach(ad->{
		 if(!ad.getRoles().contains(rol)) {
			 ad.getRoles().add(rol);
		 }
	 });
	 return admin;
		
	}
	



}
