package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.validator.AdministrateurValidator;
import com.example.backend.enumeration.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	public List<Administrateur> getAllAdministrateur() {
		return adminRepository.getAllAdministrateur();
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
		List<String> errors= AdministrateurValidator.validator(admin);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("l' admin à modifier n'est pas valide", ErrorCodes.ADMINISTRATEUR_INVALID, errors);
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
	public Administrateur getAdById(Long id) {
		return adminRepository.getAdById(id);
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);

	}

	public List<Administrateur> getAdministrateurByEtat(Etat etat) {
		return adminRepository.getAllAdministrateurByEtat(etat) ;
	}


	@Override
	public Administrateur findByEmail(String Email) {
		return adminRepository.findByEmail(Email)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun administrateur avec l'email= " + Email + "n'a été trouvé dans la BDD",
						ErrorCodes.ADMININSTRATEUR_NOT_FOUND));
	}

}
