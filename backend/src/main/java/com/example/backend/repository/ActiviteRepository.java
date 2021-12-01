package com.example.backend.repository;

import com.example.backend.model.Activite;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
	@Query(value="SELECT p FROM Activite p WHERE p.exercice.annee=:annee")
	List<Activite>findActiviteByAnnee(@Param("annee") String annee);
	List<Activite> getActiviteByDateDebutGreaterThanEqualAndDateDebutLessThanEqual(LocalDate start, LocalDate end);
}
