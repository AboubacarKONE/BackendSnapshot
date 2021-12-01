package com.example.backend.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.model.Exercice;
import com.example.backend.repository.ExerciceRepository;

@Service
public class ExerciceServiceImpl implements ExerciceService{
	@Autowired
	ExerciceRepository exerciceRepository;

	@Override
	public void ajoutExercice(Exercice exercice) {
		 exerciceRepository.save(exercice);		
	}

	@Override
	public List<Exercice> listExercice() {
		
		return exerciceRepository.findAll();
	}

	@Override
	public Exercice ExerciceById(Long id) {
		return exerciceRepository.findById(id).get();
	}

	@Override
	@Transactional
	public void updateExcercice(Long id, Exercice exercice) {
		Exercice exercice1 = exerciceRepository.findById(id).get();
        exercice1.setAnnee(exercice.getAnnee());
        exercice1.setDate_debut(exercice.getDate_debut());
        exercice1.setDate_fin(exercice.getDate_fin());
        exercice1.setStatut(exercice.getStatut());
        exercice1.setEtat(exercice.getEtat());
	}

	@Override
	public void deleteExercice(Long id) {
		exerciceRepository.deleteById(id);
		
	}

	@Override
	public List<Exercice> getExerciceByAnnee(String annee) {
		return exerciceRepository.getExerciceByAnnee(annee);
	}

	

}
