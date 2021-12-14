package com.example.backend.service;

import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.enumeration.Etat;
import com.example.backend.model.Participation;
import com.example.backend.repository.ParticipationRepo;
import com.example.backend.validator.ParticipantValidator;
import com.example.backend.validator.ParticipationValidator;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
public class PartcipationServiceImplement implements ParticipationService {

	@Autowired
	ParticipationRepo participationRepo;

	@Override
	public Participation ajouterParticipation(Participation p) {
		List<String>errors = ParticipationValidator.validator(p);
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("la participation n'est pas valide", ErrorCodes.PARTICIPATION_INVALID, errors);
		}
		return participationRepo.save(p);
	}

	@Override
	public void deleteParticipation(Long id) {
		participationRepo.deleteParticipationEtat(id);
	}

	@Override
	@Transactional
	public void updateParticipation(Long id, Participation p) {
		List<String>errors = ParticipationValidator.validator(p);
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("la participation n'est pas valide", ErrorCodes.PARTICIPATION_INVALID, errors);
		}
		Participation APP = participationRepo.findById(id).get();
		System.out.println(id);
		APP.setHeure_arriver(p.getHeure_arriver());
		APP.setActivite(p.getActivite());
		APP.setParticipant(p.getParticipant());
		APP.setAdministrateur(p.getAdministrateur());
		APP.setPresence(p.isPresence());

	}

	@Override
	public List<Participation> getAllParticipation() {
		return participationRepo.getAllParticipation();
	}

	@Override
	public List<Participation> getAllParticipationInactive() {
		return participationRepo.getAllParticipationInactive();
	}

	@Override
	public Participation getParticipationById(Long id) {
		return participationRepo.getPartById(id);
	}

	@Override
	public List<Participation> participantByActivite(Long IdActivite) {
		return participationRepo.getParticipationByActiviteAndEtat(IdActivite);
	}

	@Override
	public List<Participation> participationByEtat(Etat etat) {
		return participationRepo.getAllParticipationByEtat(etat);
	}

	@Override
	public void restaurer(Long id) {
		 participationRepo.restaurerParticipationEtat(id);
	}
}
