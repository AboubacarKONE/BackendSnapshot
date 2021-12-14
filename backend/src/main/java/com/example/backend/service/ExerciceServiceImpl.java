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
		Exercice exo = this.exerciceRepository.getExerciceByAnnee(exercice.getAnnee());
		if(exo == null){
		 exerciceRepository.save(exercice);
		}
	}

	@Override
	public List<Exercice> listExercice() {
		
		return exerciceRepository.getAllExercice();
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
	public Exercice getExerciceByAnnee(String annee) {
		return exerciceRepository.getExerciceByAnnee(annee);
	}

	@Override
	public void changeExerciceEtat(Long id) {
		exerciceRepository.updateExerciceByEtat(id);
	}



}
