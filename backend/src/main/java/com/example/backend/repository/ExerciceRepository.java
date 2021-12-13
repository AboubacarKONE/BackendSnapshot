package com.example.backend.repository;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backend.model.Exercice;
@RepositoryRestResource
@CrossOrigin("*")
public interface ExerciceRepository extends JpaRepository<Exercice, Long>{
	@Query(value = "select u from Exercice u where u.annee = :annee")
	Optional<Exercice> findByAnnee(@Param("annee") String annee);

	public List<Exercice> getExerciceByAnnee(String annee);

}
