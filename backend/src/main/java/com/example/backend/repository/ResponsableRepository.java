package com.example.backend.repository;

import com.example.backend.model.Participant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backend.model.Responsable;

import java.util.Optional;

@CrossOrigin("*")
public interface ResponsableRepository extends JpaRepositoryImplementation<Responsable, Long> {

    @Query("SELECT p FROM Responsable p WHERE p.email = :email")
    Optional<Responsable> findResponsable(@Param("email") String email);

}
