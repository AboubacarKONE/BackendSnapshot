package com.example.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend.enumeration.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backend.model.Responsable;
import java.util.Optional;
import java.util.List;

@CrossOrigin("*")
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    //Afficher responsables dont l'etat est active
    @Query(value = "SELECT respact FROM Responsable respact WHERE respact.id= :id  AND etat='active' ")
    public Responsable findAllResponsableByidandEtat( @Param("id") Long id);

//Afficher les responsables par etat
    @Query(value = "SELECT respetat FROM Responsable respetat WHERE respetat.etat= :etat")
    public List<Responsable> getResponsableByEtat(@Param("etat") Etat etat);

    //Responsable en Etat inactive
    @Transactional
    @Modifying
    @Query(value = "UPDATE  Responsable SET etat= 'inactive'  WHERE id= :id  ")
    public void SupRespByEtatInactive (@Param("id") Long id);

    //Responsable en Etat active
    @Transactional
    @Modifying
    @Query(value = "UPDATE  Responsable SET etat= 'active'  WHERE id= :id  ")
    public void RestaureRespByEtatactive (@Param("id") Long id);

    //Liste globale des responsables dont l'état est active
    @Query(value = "SELECT Resp FROM Responsable Resp WHERE Resp.etat= 'active'")
    public List<Responsable> getallresponsableactive();

    //Liste globale des responsables dont l'état est inactive
    @Query(value = "SELECT Respinactive FROM Responsable Respinactive WHERE Respinactive.etat= 'inactive'")
    public List<Responsable> getallresponsableinactive();

    @Query("SELECT p FROM Responsable p WHERE p.email = :email")
    Optional<Responsable> findResponsable(@Param("email") String email);

}
