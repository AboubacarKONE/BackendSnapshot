package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Administrateur> list() {
		return adminRepository.list();
	}

	@Override
	public String saveAdmin(Administrateur admin) {
		Optional<Administrateur> optionalAdministrateur= this.adminRepository.findByEmail(admin.getEmail());
		//admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		if(!optionalAdministrateur.isPresent()){
			adminRepository.save(admin);
			return "ajouter avec success...!!";
		}else {
			return "Cette email "+ admin.getEmail() +" existe déjà";
		}
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

	@Override
	public Optional<Administrateur> LoginUser(String login, String password) {
		return adminRepository.getUserByLoginAndPassword(login, password);
	}
}
