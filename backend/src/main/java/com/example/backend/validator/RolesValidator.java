package com.example.backend.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import com.example.backend.model.Roles;

public class RolesValidator {
	public static List<String>validator(Roles role){
		List<String>errors=new ArrayList<String>();
		if(role == null) {
			errors.add("Veuillez renseigner le libelle du role");
		}
		if(!StringUtils.hasLength(role.getLibelle())) {
			errors.add("Veuillez renseigner le libelle du role");
		}
		return errors;
	}

}
