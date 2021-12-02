package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.enumeration.Etat;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
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
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepository.save(admin);
	}

	@Override
	public Administrateur updateAdmin(Long id, Administrateur admin) {
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
	public Administrateur AdminById(Long id) {
		return adminRepository.getAdById(id);
	}

	@Override
	public void UpdateAdmin(Long id, Administrateur administrateur) {
		adminRepository.deleteAdminEtat(id);
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
