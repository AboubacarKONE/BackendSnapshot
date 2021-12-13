package com.example.backend.repository;

import com.example.backend.enumeration.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backend.model.Responsable;

import java.util.List;

@CrossOrigin("*")
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    //Afficher responsables dont l'etat est active
    @Query(value = "SELECT respact FROM Responsable respact WHERE respact.id_responsable= :id_responsable AND etat='active' ")
    public Responsable findAllResponsableByidandEtat( @Param("id_responsable") Long id_responsable);

//Afficher les responsables par etat
    @Query(value = "SELECT respetat FROM Responsable respetat WHERE respetat.etat= :etat")
    public List<Responsable> getResponsableByEtat(@Param("etat") Etat etat);

    //Responsable en Etat inactive
    @Transactional
    @Modifying
    @Query(value = "UPDATE  Responsable SET etat= 'inactive'  WHERE id_responsable= :id_responsable  ")
    public void SupRespByEtatInactive (@Param("id_responsable") Long id_responsable);

    //Responsable en Etat active
    @Transactional
    @Modifying
    @Query(value = "UPDATE  Responsable SET etat= 'active'  WHERE id_responsable= :id_responsable  ")
    public void RestaureRespByEtatactive (@Param("id_responsable") Long id_responsable);

    //Liste globale des responsables dont l'état est active
    @Query(value = "SELECT Resp FROM Responsable Resp WHERE Resp.etat= 'active'")
    public List<Responsable> getallresponsableactive();

    //Liste globale des responsables dont l'état est inactive
    @Query(value = "SELECT Respinactive FROM Responsable Respinactive WHERE Respinactive.etat= 'inactive'")
    public List<Responsable> getallresponsableinactive();

}
