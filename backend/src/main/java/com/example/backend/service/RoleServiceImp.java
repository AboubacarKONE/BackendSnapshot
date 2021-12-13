/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.Exception.InvalidOperationException;
import com.example.backend.model.Administrateur;
import com.example.backend.model.Roles;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.validator.RolesValidator;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hady.fofana
 */

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	RoleRepository rolerepository;
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Roles ajouter_role(Roles role) {
		List<String> errors = RolesValidator.validator(role);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le role n'est pas valide", ErrorCodes.ROLE_INVALID, errors);
		}
		return rolerepository.save(role);
	}

	@Override
	public Roles modifier_role(Long id, Roles role) {
		List<String> errors = RolesValidator.validator(role);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le role a mettre à jour n'est pas valide", ErrorCodes.ROLE_INVALID,
					errors);
		}
		Roles roleR = rolerepository.getById(id);
		roleR.setLibelle(role.getLibelle());
		return rolerepository.save(roleR);
	}

	@Override
	public void suprimer_role(Long Id) {
//		List<Administrateur> admin = adminRepository.findAllByRoleId(Id);
//		if (!admin.isEmpty()) {
//			throw new InvalidOperationException("vous ne pouvez pas supprimer un role assigné a un administrateur",
//					ErrorCodes.ROLE_ALREADY_EXISTE);
//		}
		rolerepository.deleteById(Id);
	}

	@Override
	public List<Roles> listeRole() {
		return rolerepository.findAll();
	}

	@Override
	public Roles getRoleById(Long id) {
		return rolerepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
				"Aucun role avec l'id = " + id + " n'a ete trouvé dans la BDD", ErrorCodes.ROLE_NOT_FOUND)
				);
	}

	@Override
	public Roles verifie_role(String libelle) {
		return rolerepository.findByLibelle(libelle);
	}

}
