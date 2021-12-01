package com.example.backend.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.backend.model.Role;

public class RoleValidator {
	public static List<String>validator(Role role){
		List<String>errors=new ArrayList<String>();
		if(role == null) {
			errors.add("Veuillez renseigner la description du role");
			errors.add("Veuillez renseigner le libelle du role");
		}
		if(!StringUtils.hasLength(role.getDescription())) {
			errors.add("Veuillez renseigner la description du role");
		}
		if(!StringUtils.hasLength(role.getLibelle())) {
			errors.add("Veuillez renseigner le libelle du role");
		}
		return errors;
	}

}
