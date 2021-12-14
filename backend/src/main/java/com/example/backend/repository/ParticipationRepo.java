package com.example.backend.repository;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@CrossOrigin("*")
public interface ParticipationRepo extends JpaRepository<Participation, Long> {

    @Query(value = "SELECT p FROM Participation p WHERE p.activite.id = :id")
    List<Participation> getParticipationByActiviteAndEtat(@Param("id") Long IdActivite);

    @Query(value = "SELECT a FROM Participation a WHERE a.etat = 'active' ")
    List<Participation> getAllParticipation();

    @Query(value = "SELECT a FROM Participation a WHERE a.etat = 'inactive' ")
    List<Participation> getAllParticipationInactive();

    @Query(value = "SELECT par FROM Participation par WHERE par.id = :id AND par.etat = 'active' ")
    Participation getPartById(@Param("id")Long id_participation);

    @Query(value = "SELECT c FROM Participation c WHERE c.etat = :etat")
    List<Participation> getAllParticipationByEtat(@Param("etat") Etat etat);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Participation SET etat = 'inactive' WHERE id = :id ")
    void deleteParticipationEtat(@Param("id") Long id_participation);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Participation SET etat = 'active' WHERE id = :id ")
    void restaurerParticipationEtat(@Param("id") Long id_participation);

}
