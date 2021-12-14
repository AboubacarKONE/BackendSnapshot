package com.example.backend.service;

import com.example.backend.enumeration.Etat;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.Exception.InvalidOperationException;
import com.example.backend.model.Activite;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.ActiviteRepository;
import net.bytebuddy.asm.Advice;
import com.example.backend.validator.ActiviteValidator;
import com.example.backend.validator.AdministrateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ActiviteImp implements ActiviteService {
	@Autowired
	ActiviteRepository activiteRepository;

	@Override
	public void ajouterActivite(Activite activite) {
		List<String>errors = ActiviteValidator.validator(activite);
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("l'activite n'est pas valide",ErrorCodes.ACTIVITE_INVALID, errors);
		}
		if (activite.getDateDebut().isBefore(activite.getDateFin())) {
			Optional<Activite> optionalActivite = activiteRepository.findByLibelle(activite.getLibelle());
			if (!optionalActivite.isPresent()) {
				activiteRepository.save(activite);
			} else {
				throw new InvalidEntityException("l' activité: " + activite.getLibelle() + " existe déjà");
			}
		} else {
			throw new InvalidOperationException("la date de debut doit etre superieur a la date de fin");
		}

	}

	@Override
	@Transactional
	public void modifierActivite(Long Id, Activite activite) {
		List<String> errors = ActiviteValidator.validator(activite);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("l' activité n'est pas valide", ErrorCodes.ACTIVITE_INVALID, errors);
		}
		Activite activiteAncien = activiteRepository.findById(Id).get();
		activiteAncien.setLibelle(activite.getLibelle());
		activiteAncien.setDateDebut(activite.getDateDebut());
		activiteAncien.setDateFin(activite.getDateFin());
		activiteAncien.setEtat(activite.getEtat());
	}

	// Activite by Etat active
	@Override
	public Activite listeById(Long idActivite, Etat etat) {
		return activiteRepository.getActiviteByIdAndEtat(idActivite, etat);
	}

	// Activite by Etat inactive
	@Override
	public Activite listeByIdInactive(Long id, Etat etat) {
		return activiteRepository.getActiviteByIdAndEtat(id, etat);
	}

	// All Activite
	@Override
	public List<Activite> getAllActivite() {
		return activiteRepository.getAllActivite();
	}

	@Override
	public List<Activite> getAllActiviteInactive() {
		return activiteRepository.getAllActiviteInactive();
	}

	// desactive activite
	@Override
	public void disableActivite(Long id) {
		this.activiteRepository.desableActivite(id);
	}

	// active Activite
	@Override
	public void enableActivite(Long id) {
		this.activiteRepository.enableActivite(id);
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
