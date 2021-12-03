package com.example.backend.repository;

import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ParticipantRepository  extends JpaRepository<Participant,Long> {
	 @Query("SELECT p FROM Participant p WHERE p.email = :email")
	    Optional<Participant> findParticipant(@Param("email") String email);
	@Query("SELECT count(*) FROM Participant p WHERE p.participantGenre =:participantGenre and p.etat ='active'")
	int findByparticipantGenreAndEtat(@Param("participantGenre") ParticipantGenre participantGenre);
	//modifier etat active par etat inative
	@Transactional
	@Modifying
	@Query("UPDATE Participant  SET etat='inactive' WHERE id=:id")
	void updateParticipant(@Param(value = "id") Long id );
	//change etat inative par etat active d'un participant
	@Transactional
	@Modifying
	@Query("UPDATE Participant SET etat='active' WHERE id =:id")
	void  updateInactiveparActive(@Param(value = "id")Long id);
// list de Participant par etat active
	List<Participant> findByEtat(Etat etat);
	// afficher un participant par son id
	Participant findByIdAndEtat(Long id,Etat etat);
}
