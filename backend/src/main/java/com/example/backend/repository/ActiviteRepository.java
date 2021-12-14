package com.example.backend.repository;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Activite;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.backend.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
	@Query(value = "select u from Activite u where u.libelle = :libelle")
	Optional<Activite> findByLibelle(@Param("libelle") String libelle);

	@Query(value="SELECT p FROM Activite p WHERE p.exercice.annee=:annee")
	List<Activite>findActiviteByAnnee(@Param("annee") String annee);
	List<Activite> getActiviteByDateDebutGreaterThanEqualAndDateDebutLessThanEqual(LocalDate start, LocalDate end);

	@Query(value = "SELECT a FROM Activite a WHERE a.etat = 'active' ")
	List<Activite> getAllActivite();

	@Query(value = "SELECT a FROM Activite a WHERE a.etat = 'inactive' ")
	List<Activite> getAllActiviteInactive();

	@Query(value = "SELECT ac FROM Activite ac WHERE ac.id = :id AND ac.etat = :etat")
	Activite getActiviteByIdAndEtat(Long id, Etat etat);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Activite SET etat='inactive' WHERE id=:id")
	void desableActivite(Long id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Activite SET etat='active' WHERE id=:id")
	void enableActivite(Long id);

}
