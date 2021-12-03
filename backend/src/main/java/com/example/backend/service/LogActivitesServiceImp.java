package com.example.backend.service;

import java.util.List;

import com.example.backend.enumeration.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.LogActivites;
import com.example.backend.repository.LogActivitesRepository;

@Service
public class LogActivitesServiceImp implements LogActivitesService {
	@Autowired
	LogActivitesRepository logActivitesRepository;

	//Ajouter un log d'activité
	@Override
	public void addLogActivites(LogActivites act) {
		LogActivites respond = this.logActivitesRepository.getLogActivitesByResponsableAndActivite(
				act.getResponsable(), act.getActivite()

		);
		if(respond == null){
			logActivitesRepository.save(act);
		}

	}

	//Lister les activitées
	@Override
	public List<LogActivites> listLogActivites() {
		return logActivitesRepository.getAllLogActivite();
	}

	//Effacé un elemant par son id
	@Override
	public void deleteLogActivitesByid(Long id) {
	}

	//Recuperation par id
	@Override
	public LogActivites activiteByidAndEtat(Long id) {
		return logActivitesRepository.getLogActivitesByIdAndEtat(id);
	}

}
