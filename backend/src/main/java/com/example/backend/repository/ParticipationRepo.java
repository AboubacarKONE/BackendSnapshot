package com.example.backend.repository;

import com.example.backend.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
public interface ParticipationRepo extends JpaRepository<Participation, Long> {

    @Query(value = "SELECT p FROM Participation p WHERE p.activite.IdActivite = :IdActivite")
    List<Participation> getParticipationByActiviteAndEtat(@Param("IdActivite") Long IdActivite);

    @Query(value = "SELECT a FROM Participation a WHERE a.etat = 'active' ")
    List<Participation> getAllParticipation();

    @Query(value = "SELECT par FROM Participation par WHERE par.id_participation = :id_participation AND par.etat = 'active' ")
    Participation getPartById(@Param("id_participation")Long id_participation);

    @Query(value = "SELECT c FROM Participation c WHERE c.etat = 'inactive' ")
    List<Participation> getAllParticipationInactive();

}
