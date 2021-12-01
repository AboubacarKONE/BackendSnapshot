package com.example.backend.service;

import com.example.backend.model.Participation;
import com.example.backend.repository.ParticipationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartcipationServiceImplement implements ParticipationService {

	@Autowired
	ParticipationRepo participationRepo;

	@Override
	public Participation ajouterParticipation(Participation p) {
		return participationRepo.save(p);
	}

	@Override
	public void deleteParticipation(Long id) {
		participationRepo.deleteById(id);
	}

	@Override
	@Transactional
	public void updateParticipation(Long id, Participation p) {
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
		return participationRepo.findAll();
	}

	@Override
	public Participation getParticipationById(Long id) {
		return participationRepo.findById(id).get();
	}
}
