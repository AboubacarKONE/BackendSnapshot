package com.example.backend.service;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Activite;
import com.example.backend.repository.ActiviteRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Service
public class ActiviteImp implements ActiviteService {
	@Autowired
	ActiviteRepository activiteRepository;

	@Override
	public void ajouterActivite(Activite activite) {
		if (activite.getDateDebut().isBefore(activite.getDateFin())){
			this.activiteRepository.save(activite);
		}else {
			System.out.println("Non enregistrer");
		}


	}

	@Override
	@Transactional
	public void modifierActivite(Long Id, Activite activite) {
		Activite activiteAncien = activiteRepository.findById(Id).get();
		activiteAncien.setLibelle(activite.getLibelle());
		activiteAncien.setDateDebut(activite.getDateDebut());
		activiteAncien.setDateFin(activite.getDateFin());
		activiteAncien.setEtat(activite.getEtat());
	}
	//Activite by Etat active
	@Override
	public Activite listeById(Long idActivite, Etat etat) {
		return activiteRepository.getActiviteByIdAndEtat(idActivite, etat );
	}
	//Activite by Etat inactive
	@Override
	public Activite listeByIdInactive(Long id, Etat etat) {
		return activiteRepository.getActiviteByIdAndEtat(id,etat);
	}

	//All Activite
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
	public String disableActivite(Long id) {
		this.activiteRepository.desableActivite(id);
		return "Suppression de l'activité";
	}
	// active Activite
	@Override
	public String enableActivite(Long id) {
		this.activiteRepository.enableActivite(id);
		return "Activer avec succes";
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
