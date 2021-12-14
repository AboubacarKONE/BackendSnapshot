package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backend.model.Exercice;
@RepositoryRestResource
@CrossOrigin("*")
public interface ExerciceRepository extends JpaRepository<Exercice, Long>{
	Exercice getExerciceByAnnee(String annee);

	@Query(value = "SELECT Ex FROM Exercice Ex WHERE Ex.etat = 'active' ")
	List<Exercice> getAllExercice();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Exercice Ex SET Ex.etat = 'inactive'")
	public void updateExerciceByEtat(Long id);


}
