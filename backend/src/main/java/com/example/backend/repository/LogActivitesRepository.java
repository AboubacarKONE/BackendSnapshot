package com.example.backend.repository;

import java.util.List;

import com.example.backend.enumeration.Etat;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backend.model.LogActivites;

@RepositoryRestResource
@CrossOrigin("*")
public interface LogActivitesRepository extends JpaRepositoryImplementation<LogActivites, Long> {
	 @Query(value = "SELECT lo FROM LogActivites lo WHERE lo.activite.IdActivite = :IdActivite AND lo.etat = 'active' ")
	 LogActivites getLogActivitesByIdAndEtat(@Param("IdActivite") Long id);

	 @Query(value = "SELECT lo FROM LogActivites lo WHERE lo.etat = 'active' ")
	 List<LogActivites> getAllLogActivite();

@Transactional
@Modifying
	@Query("UPDATE LogActivites  SET etat = 'inactive' WHERE id =:id")
	void LogActivite (@Param(value = "id") Long id);

}

