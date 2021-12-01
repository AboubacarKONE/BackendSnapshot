package com.example.backend.service;

import java.util.List;

import com.example.backend.model.Administrateur;

public interface AdminService {
	 List<Administrateur> list();
	 Administrateur saveAdmin(Administrateur admin);
	 Administrateur updateAdmin(Long id, Administrateur admin);
	 Administrateur AdminById(Long id);
	 void deleteAdmin(Long id);
	 Administrateur findByEmail(String Email);

}
