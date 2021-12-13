package com.example.backend.service;

import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.model.Activite;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.ActiviteRepository;
import com.example.backend.validator.ActiviteValidator;
import com.example.backend.validator.AdministrateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ActiviteImp implements ActiviteService {
	@Autowired
	ActiviteRepository activiteRepository;

	@Override
	public String ajouterActivite(Activite activite) {
		Optional<Activite> optionalActivite = activiteRepository.findByLibelle(activite.getLibelle());
		if (!optionalActivite.isPresent()){
			activiteRepository.save(activite);
			return "Ajout Ok" + activite.getType();
		}else{
			throw new InvalidEntityException("l' activité: " + activite.getLibelle() + " existe déjà");
		}
	}

	@Override
	@Transactional
	public void modifierActivite(Long Id, Activite activite) {
		List<String> errors= ActiviteValidator.validator(activite);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("l' activité  " + activite.getIdActivite() +" n'est pas valide");
		}
		Activite activiteAncien = activiteRepository.findById(Id).get();
		activiteAncien.setLibelle(activite.getLibelle());
		activiteAncien.setType(activite.getType());
		activiteAncien.setDateDebut(activite.getDateDebut());
		activiteAncien.setDateFin(activite.getDateFin());
		activiteAncien.setEtat(activite.getEtat());
	}

	@Override
	public String supprimerActiviteById(Long Id_activite) {
		this.activiteRepository.deleteById(Id_activite);
		return "Suppression de l'activité";
	}

	@Override
	public Activite listeById(Long Id_activite) {
		return activiteRepository.findById(Id_activite).get();
	}

	@Override
	public List<Activite> getAllActivite() {
		return activiteRepository.findAll();
	}

	@Override
	public List<Activite> findActiviteByAnnee(String annee) {
		return activiteRepository.findActiviteByAnnee(annee);
	}

	@Override
	public List<Activite> getActiviteByMonth(int year, int month) {
		LocalDate initial = LocalDate.of(year, month, 1);
		LocalDate start = initial.withDayOfMonth(1);
		LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
		return activiteRepository.getActiviteByDateDebutGreaterThanEqualAndDateDebutLessThanEqual(start, end);
	}

}
