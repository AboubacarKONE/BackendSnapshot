package com.example.backend.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.backend.model.Administrateur;



public class AdministrateurValidator {
	public static List<String>validator(Administrateur admin){
		List<String> errors = new ArrayList<String>();
		if(admin == null) {
			errors.add("Veuillez renseigner le nom de l'administrateur");
			errors.add("Veuillez renseigner le prenom de l'administrateur");
			errors.add("Veuillez renseigner le login de l'aministrateur");
			errors.add("Veuillez renseigner le password de l'utilisateur");
			errors.add("Veuillez renseigner l'état de l'administrateur");
			errors.add("Veuillez renseigner le role de l'administrateur");
			errors.add("Veuillez renseigner le profile de l'adminstrateur");
		}
		if(!StringUtils.hasLength(admin.getNom())) {
			errors.add("Veuillez renseigner le nom de l'administrateur");
		}
		if(!StringUtils.hasLength(admin.getPrenom())) {
			errors.add("Veuillez renseigner le prenom de l'administrateur");
		}
		if(!StringUtils.hasLength(admin.getEmail())) {
			errors.add("Veuillez renseigner l'email de l'utilisateur");
		}
		if(!StringUtils.hasLength(admin.getPassword())) {
			errors.add("Veuillez renseigner le password de l'utilisateur");
		}
		if(admin.getEtat()==null) {
			errors.add("Veuillez renseigner l'état de l'administrateur");
		}
	   if(admin.getProfile()==null) {
		   errors.add("Veuillez renseigner le profile de l'administrateur");
	   }
	   if(admin.getTelephone() == null) {
		   errors.add("Veuillez renseigner le numero de telephone de l'administrateur");
	   }
		return errors;
	}

}
