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
import com.example.backend.model.Role;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.validator.RoleValidator;

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
	public Role ajouter_role(Role role) {
		List<String> errors = RoleValidator.validator(role);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le role n'est pas valide", ErrorCodes.ROLE_INVALID, errors);
		}
		return rolerepository.save(role);
	}

	@Override
	public Role modifier_role(Long id, Role role) {
		List<String> errors = RoleValidator.validator(role);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le role a mettre à jour n'est pas valide", ErrorCodes.ROLE_INVALID,
					errors);
		}
		Role roleR = rolerepository.getById(id);
		roleR.setDescription(role.getDescription());
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
	public List<Role> listeRole() {
		return rolerepository.findAll();
	}

	@Override
	public Role getRoleById(Long id) {
		return rolerepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
				"Aucun role avec l'id = " + id + " n'a ete trouvé dans la BDD", ErrorCodes.ROLE_NOT_FOUND)
				);
	}

	@Override
	public Role verifie_role(String libelle) {
		return rolerepository.findByLibelle(libelle);
	}

}
