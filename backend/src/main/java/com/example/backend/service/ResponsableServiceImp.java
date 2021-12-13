package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.validator.ResponsableValidator;
import com.example.backend.enumeration.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.Responsable;
import com.example.backend.repository.ResponsableRepository;

@Service
public class ResponsableServiceImp implements ResponsableService {

	@Autowired
	ResponsableRepository responsableRepository;
	
	@Override
	public Responsable ajouter_responsable(Responsable responsable) {
		Optional<Responsable> optionalResponsable = this.responsableRepository.findResponsable(responsable.getEmail());
		//vérifier si email existe dans la base
		if(!optionalResponsable.isPresent())
		{
			responsableRepository.save(responsable);
		}else{
			System.out.println("Email : " + responsable.getEmail() + " existe déjà...");
		}
		return null;
	}

	@Override
	public Responsable modifier_responsable(Long id, Responsable responsable) {
		List<String> errors= ResponsableValidator.validator(responsable);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("la modification a echoué, veuillez verifiez vos champs", ErrorCodes.ADMINISTRATEUR_INVALID, errors);
		}
		Responsable responsables = responsableRepository.findById(id).get();
		responsables.setNom(responsable.getNom());
		responsables.setPrenom(responsable.getPrenom());
		responsables.setTelephone(responsable.getTelephone());
		responsables.setDomaine(responsable.getDomaine());
		responsables.setType(responsable.getType());
		responsables.setEtat(responsable.getEtat());
		responsables.setEmail(responsable.getEmail());
		return responsableRepository.save(responsables);
	}

	@Override 
	public List<Responsable> list_responsable() {
		return responsableRepository.findAll();
	}

	@Override
	public Responsable afficher_responsable_by_id(Long id) {
		return responsableRepository.findAllResponsableByidandEtat(id);
	}

	@Override
	public void supprimer_responsable(Long id) {
		responsableRepository.SupRespByEtatInactive(id);
		
	}

	@Override
	public void resetresponsable(Long id) {
		responsableRepository.RestaureRespByEtatactive(id);
	}

	@Override
	public List<Responsable> list_responsableByEtat(Etat etat) {
		return responsableRepository.getResponsableByEtat(etat);
	}

}
