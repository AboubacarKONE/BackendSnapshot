package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.LogActivites;
import com.example.backend.repository.LogActivitesRepository;

@Service
public class LogActivitesServiceImp implements LogActivitesService {
	@Autowired
	LogActivitesRepository logActivitesRepository;

	@Override
	public void addLogActivites(LogActivites act) {
		logActivitesRepository.save(act);

	}

	@Override
	public List<LogActivites> listLogActivites() {
		return logActivitesRepository.findAll();
	}

	@Override
	public LogActivites listLogActivitebyid(Long id_LogActivites) {
		return logActivitesRepository.findById(id_LogActivites).get();

	}

	@Override
	public void deleteLogActivitesByid(Long id_LogActivites) {
		logActivitesRepository.deleteById(id_LogActivites);

	}

	@Override
	public List<LogActivites> listByActivite(Long IdActivite) {
		return logActivitesRepository.findAllByActiviteId(IdActivite);
	}

}
