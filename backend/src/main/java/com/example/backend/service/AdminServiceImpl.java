package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.enumeration.Profile;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.validator.AdministrateurValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
		List<String> errors = AdministrateurValidator.validator(admin);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("Adminstrateur n'est pas valide", ErrorCodes.ADMINISTRATEUR_INVALID,
					errors);
		}
		Optional<Administrateur> adminEmail = adminRepository.findByEmail(admin.getEmail());
		if (adminEmail.isPresent()) {
			throw new InvalidEntityException("Un autre administrateur avec cet email existe deja",
					ErrorCodes.ADMINISTRATEUR_ALREADY_EXISTE,
					Collections.singletonList("Un autre administrateur avec le meme email existe dans la BDD"));
		}
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		List<Administrateur> administrateur = adminRepository.findByProfile(admin.getProfile());
		if (!administrateur.isEmpty()) {
			administrateur.get(0).getRoles().forEach(ad -> {
				admin.getRoles().add(roleRepository.findByLibelle(ad.getLibelle()));
			});
		}
		return adminRepository.save(admin);
	}

	@Override
	public Administrateur updateAdmin(Long id, Administrateur admin) {
		List<String>errors=AdministrateurValidator.validator(admin);
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("Adminstrateur n'est pas valide", ErrorCodes.ADMINISTRATEUR_INVALID, errors);
		}
		Administrateur administrateur = adminRepository.findById(id).get();
		administrateur.setNom(admin.getNom());
		administrateur.setPrenom(admin.getPrenom());
		administrateur.setEmail(admin.getEmail());
		administrateur.setEtat(admin.getEtat());
		administrateur.setPassword(admin.getPassword());
		administrateur.setPhotoUrl(admin.getPhotoUrl());
		administrateur.setProfile(admin.getProfile());
		administrateur.setTelephone(admin.getTelephone());
		return adminRepository.save(administrateur);
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);

	}

	@Override
	public Administrateur AdminById(Long id) {
		return adminRepository.findById(id).get();	}

	@Override
	public Administrateur findByEmail(String Email) {
		return adminRepository.findByEmail(Email)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun administrateur avec l'email= " + Email + "n'a été trouvé dans la BDD",
						ErrorCodes.ADMININSTRATEUR_NOT_FOUND));
	}

}
