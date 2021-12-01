package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backend.model.LogActivites;
@RepositoryRestResource
@CrossOrigin("*")
public interface LogActivitesRepository extends JpaRepositoryImplementation<LogActivites, Long> {
	 @Query(value = "SELECT lo FROM LogActivites lo WHERE lo.activite.IdActivite = :IdActivite")
	 List<LogActivites> findAllByActiviteId(@Param("IdActivite") Long IdActivite);

}
