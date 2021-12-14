package com.example.backend.service;

import java.util.List;
import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.model.LogActivites;
import com.example.backend.repository.LogActivitesRepository;

@Service
public class LogActivitesServiceImp implements LogActivitesService {
	@Autowired
	LogActivitesRepository logActivitesRepository;

	// Ajouter un log d'activité
	@Override
	public void addLogActivites(LogActivites act) {
		LogActivites respond = this.logActivitesRepository.getLogActivitesByResponsableAndActivite(act.getResponsable(),
				act.getActivite()

		);
		if (respond != null) {
			throw new InvalidOperationException("ce responsable  est deja assigné a cette activite  ",
					ErrorCodes.LOGACTIVITE_ALREADY_EXISTE);
		}
		logActivitesRepository.save(act);

	}

	// Lister les activitées
	@Override
	public List<LogActivites> listLogActivites() {
		return logActivitesRepository.getAllLogActivite();
	}

	// Effacé un elemant par son id
	@Override
	public void deleteLogActivitesByid(Long id) {
	}

	// Recuperation par id
	@Override
	public LogActivites activiteByidAndEtat(Long id) {
		return logActivitesRepository.getLogActivitesByIdAndEtat(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"aucun activites avec cet id " + id + " n'est associe a un responsable",
						ErrorCodes.LOGACTIVITE_NOT_FOUND));
	}

	@Override
	public void deleteLogActivite(Long id) {
		logActivitesRepository.deleteLogActivite(id);
		
	}

}
