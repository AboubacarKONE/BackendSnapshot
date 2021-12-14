package com.example.backend.service;

import java.util.List;
import java.util.Optional;


import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.validator.ExerciceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidOperationException;
import com.example.backend.model.Exercice;
import com.example.backend.repository.ExerciceRepository;

@Service
public class ExerciceServiceImpl implements ExerciceService{
	@Autowired
	ExerciceRepository exerciceRepository;

	@Override
	public void ajoutExercice(Exercice exercice) {
		Optional<Exercice> exo = this.exerciceRepository.getExerciceByAnnee(exercice.getAnnee());
		if(exo.isPresent()){
		 throw new InvalidOperationException("cet exercice existe deja", ErrorCodes.EXERCICE_ALREADY_EXISTE);
		}
		exerciceRepository.save(exercice);
		Optional<Exercice> optionalExercice = exerciceRepository.findByAnnee(exercice.getAnnee());
		if (!optionalExercice.isPresent()){
			exerciceRepository.save(exercice);
		}else{
			throw new InvalidEntityException("l' exercice: " + exercice.getAnnee()+ " existe déjà");
		}
	}

	@Override
	public List<Exercice> listExercice() {
		
		return exerciceRepository.getAllExercice();
	}

	@Override
	public Exercice ExerciceById(Long id) {
		return exerciceRepository.findParId(id);
	}

	@Override
	@Transactional
	public void updateExcercice(Long id, Exercice exercice) {
		List<String> errors= ExerciceValidator.validator(exercice);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("l' exercice  " + exercice.getAnnee() +" n'est pas valide");
		}
		Exercice exercice1 = exerciceRepository.findById(id).get();
        exercice1.setAnnee(exercice.getAnnee());
        exercice1.setDate_debut(exercice.getDate_debut());
        exercice1.setDate_fin(exercice.getDate_fin());
        exercice1.setStatut(exercice.getStatut());
        exercice1.setEtat(exercice.getEtat());
	}


	@Override
	public Exercice getExerciceByAnnee(String annee) {
		return exerciceRepository.getExerciceByAnnee(annee).orElseThrow(()->{
			throw new EntityNotFoundException("aucun exercice avec cet annee "+ annee + " n'existe dans la BDD", ErrorCodes.EXERCICE_NOT_FOUND);
		});
	}

	@Override
	public void deleteExercice(Long id) {
		exerciceRepository.updateExerciceByEtat(id);
	}

}
