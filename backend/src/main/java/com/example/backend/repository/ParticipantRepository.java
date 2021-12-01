package com.example.backend.repository;

import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ParticipantRepository  extends JpaRepository<Participant,Long> {
	 @Query("SELECT p FROM Participant p WHERE p.email = :email")
	    Optional<Participant> findParticipant(@Param("email") String email);
	@Query("SELECT count(*) FROM Participant p WHERE p.participantGenre =:participantGenre")
	int findByparticipantGenre(@Param("participantGenre") ParticipantGenre participantGenre);
}
