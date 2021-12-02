package com.example.backend.service;

import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;
import com.example.backend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceImplement implements ParticipantService {
	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public String addParticipant(Participant participant) {
		Optional<Participant> optionalParticipant = this.participantRepository.findParticipant(participant.getEmail());

		//vérifier si email existe dans la base
		if(!optionalParticipant.isPresent())
		{
			participantRepository.save(participant);
			return "Participant ajouté avec succèss...";
		}else{
			System.out.println("Email : " + participant.getEmail() + " existe déjà...");
			return "Email : " + participant.getEmail() + " existe déjà...";
		}

	}

	@Override
	// mettre a jour de participant
	public Participant updateParticipant(Long id, Participant participant) {
		// return participantRepository.save(participant);
		Participant mod = participantRepository.getById(id);
		mod.setNom_complet(participant.getNom_complet());
		mod.setEmail(participant.getEmail());
		mod.setDomaine(participant.getDomaine());
		mod.setStructure(participant.getStructure());
		mod.setTelephone(participant.getTelephone());
		return participantRepository.save(mod);

	}
	@Override
	public void deleteParticipant(Long id) {
		participantRepository.deleteById(id);
	}

	//@Override
	//public Participant ParticipantById(Long id) {
		//return participantRepository.findById(id).get();
	//}

	@Override
	public int findByparticipantGenreAndEtat(ParticipantGenre genre) {
		return participantRepository.findByparticipantGenreAndEtat(genre);
	}

	@Override
	public List<Participant> addManyParticipant(List<Participant> participants) {
		List<Participant> list = new ArrayList<>();
		for(int i=0; i<participants.size(); i++){
			System.out.println(participants.get(i));
			Participant part = new Participant();
			part.setNom_complet(participants.get(i).getNom_complet());
			part.setStructure(participants.get(i).getStructure());
			part.setEmail(participants.get(i).getEmail());
			part.setDomaine(participants.get(i).getDomaine());
			part.setTelephone(participants.get(i).getTelephone());
			Participant p = participantRepository.saveAndFlush(part);
			list.add(p);
		}
		return list;
	}

	@Override
	public Participant findByIdAndEtat(Long id_participant, Etat etat) {
		return participantRepository.findByIdAndEtat(id_participant,etat);
	}
//methode afficher un participant par son Id
	@Override
	public List<Participant> findByEtat(Etat etat) {
		return  participantRepository.findByEtat(etat);
	}

	@Override
	public void updateParticipant(Long id) {
		participantRepository.updateParticipant(id);
	}
	// modifier etat d'un participant par active
	@Override
	public void updateInactiveparActive(Long id) {
		participantRepository.updateInactiveparActive(id);
	}



}
