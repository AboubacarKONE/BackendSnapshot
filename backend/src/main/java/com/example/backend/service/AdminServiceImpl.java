package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.enumeration.Etat;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.AdminRepository;
import com.example.backend.validator.AdministrateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Administrateur> getAllAdministrateur() {
		return adminRepository.getAllAdministrateur();
	}

	@Override
	public Administrateur saveAdmin(Administrateur admin) {
		Optional<Administrateur> optionalAdministrateur = adminRepository.findByEmail(admin.getEmail());
		if (!optionalAdministrateur.isPresent()){
			return adminRepository.save(admin);

		}else{
			throw new InvalidEntityException("l' admin existe déjà", ErrorCodes.ADMINISTRATEUR_INVALID);
		}

	}

	@Override
	public Administrateur updateAdmin(Long id, Administrateur admin) {
		List<String> errors= AdministrateurValidator.validator(admin);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("l' admin à modifier n'est pas valide", ErrorCodes.ADMINISTRATEUR_INVALID, errors);
		}
		Administrateur administrateur = adminRepository.getById(id);
		administrateur.setNom(admin.getNom());
		administrateur.setPrenom(admin.getPrenom());
		administrateur.setEmail(admin.getEmail());
		administrateur.setEtat(admin.getEtat());
		administrateur.setRoles(admin.getRoles());
		administrateur.setLogin(admin.getLogin());
		administrateur.setPassword(admin.getPassword());
		administrateur.setTelephone(admin.getTelephone());
		return adminRepository.save(admin);
	}

	@Override
	public Administrateur getAdById(Long id) {
		return adminRepository.getAdById(id);
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);

	}

	@Override
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

	@Override
	public Optional<Administrateur> LoginUser(String login, String password) {
		return adminRepository.getUserByLoginAndPassword(login, password);
	}
}
