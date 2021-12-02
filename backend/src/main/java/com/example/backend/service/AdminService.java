package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Administrateur;
import com.example.backend.model.Participation;

public interface AdminService {
	 public List<Administrateur> getAllAdministrateur();
	 Administrateur saveAdmin(Administrateur admin);
	 Administrateur updateAdmin(Long id, Administrateur admin);
	 public Administrateur getAdById(Long id);
	 void deleteAdmin(Long id);
	public List<Administrateur> getAdministrateurByEtat(Etat etat);
	Administrateur AdminById(Long id);
	public void UpdateAdmin(Long id, Administrateur administrateur);

	Administrateur findByEmail(String Email);
	 Optional<Administrateur> LoginUser(String login, String password);

}
