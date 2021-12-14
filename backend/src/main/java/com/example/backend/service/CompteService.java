package com.example.backend.service;

import java.util.List;

import com.example.backend.enumeration.Profile;
import com.example.backend.model.Administrateur;

public interface CompteService {
	List<Administrateur> AddAdminToRole(Profile profile,String roleName);
	void deleteRoleFromAdmin(Profile profile, String roleName);
}
