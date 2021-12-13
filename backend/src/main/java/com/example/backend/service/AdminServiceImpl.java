package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.enumeration.Profile;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Administrateur> list() {
		return adminRepository.findAll();
	}

	@Override
	public Administrateur saveAdmin(Administrateur admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		List<Administrateur> administrateur = adminRepository.findByProfile(Profile.ADMIN);
		administrateur.get(0).getRoles().forEach(ad->{
			admin.setRoles(Arrays.asList(roleRepository.findByLibelle(ad.getLibelle())));
			System.out.println(admin);
		});
	//dmin.setRoles(Arrays.asList(roleRepository.findByLibelle(administrateur.get(0).getRoles().forEach(ad->ad.getLibelle()))));
		return adminRepository.save(admin);
	}

	@Override
	public Administrateur updateAdmin(Long id, Administrateur admin) {
		return adminRepository.save(admin);
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);

	}

	@Override
	public Administrateur AdminById(Long id) {
		return adminRepository.findById(id).get();
	}

	@Override
	public Administrateur findByEmail(String Email) {
		return adminRepository.findByEmail(Email)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun administrateur avec l'email= " + Email + "n'a été trouvé dans la BDD",
						ErrorCodes.ADMININSTRATEUR_NOT_FOUND));
	}

	}
