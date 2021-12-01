package com.example.backend.service;

import com.example.backend.model.Activite;
import com.example.backend.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActiviteImp implements ActiviteService {
	@Autowired
	ActiviteRepository activiteRepository;

	@Override
	public String ajouterActivite(Activite activite) {
		this.activiteRepository.save(activite);
		return "Ajout Ok" + activite.getType();
	}

	@Override
	@Transactional
	public void modifierActivite(Long Id, Activite activite) {
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
