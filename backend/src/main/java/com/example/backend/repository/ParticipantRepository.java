package com.example.backend.repository;

import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface ParticipantRepository  extends JpaRepository<Participant,Long> {
	 @Query("SELECT p FROM Participant p WHERE p.email = :email")
	    Optional<Participant> findParticipant(@Param("email") String email);
	@Query("SELECT count(*) FROM Participant p WHERE p.participantGenre =:participantGenre and p.etat='active'")
	int findByparticipantGenre(@Param("participantGenre") ParticipantGenre participantGenre);

	@Query("SELECT p FROM Participant p WHERE p.id_participant=:id_participant and p.etat='active'")
	Participant ParticipantById(Long id_participant);

	@Query("SELECT p FROM Participant p WHERE p.etat='active'")
	 List<Participant> listParticipant();


	@Transactional
	@Modifying
	@Query("UPDATE Participant SET etat='inactive' WHERE id_participant=:id_participant")
	 void supprimer(Long id_participant);


	@Transactional
	@Modifying
	@Query("UPDATE Participant SET etat='actvie' WHERE id_participant=:id_participant")
	void recupere(Long id_participant);
}
