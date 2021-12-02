package com.example.backend.controller;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Participation;
import com.example.backend.service.ParticipationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class ParticipationController {

    @Autowired
    ParticipationService participationService;

    @PostMapping("/ajouteParticipation")
    @ApiOperation(value = "Enregistrer une participation", notes = "cette methode permet d'ajouter une participation", response = Participation.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet participant cree"),
            @ApiResponse(code = 400, message = "l'objet participant n'est pas valide") })
    public Participation ajouterParticipation(@RequestBody Participation p) {
        return participationService.ajouterParticipation(p);
    }

    @DeleteMapping("/deletep/{id}")
    @ApiOperation(value = "supprimer une participation", notes = "cette methode permet de supprimer une participation par son id en changeant l'etat de active a inactive")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "la participation a été supprimé"),
            @ApiResponse(code = 404, message = "aucune participation avec cet id n'existe dans la BDD") })
    public void deleteParticipation(@PathVariable("id") Long id) {
        participationService.deleteParticipation(id);
    }

    @PutMapping("/updateParticipation/{id}")
    @ApiOperation(value = "Modifier une participation", notes = "cette methode permet de modifier une participation", response = Participation.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet participation modifié"),
            @ApiResponse(code = 400, message = "l'objet participation n'est pas valide") })
    public String updateParticipation(@PathVariable("id") Long id ,@RequestBody Participation p) {
        return "mis ajour effectué";
    }

    @GetMapping("/listeParticipation")
    @ApiOperation(value = "renvoi la liste des participations dont l'etat est active", notes = "cette methode permet de chercher et renvoyer la liste des participations dont l'etat est active qui existent"
            + "dans la BDD", responseContainer = "liste<Participation>")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des participants / une liste vide") })
    public List<Participation> getAllParticipation() {
        return participationService.getAllParticipation();
    }

    @GetMapping("/getParticipation/{id}")
    @ApiOperation(value = "rechercher un participation dont l'etat est active", notes = "cette methode permet de rechercher un participant dont l'etat est active par son id", response = Participation.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'apprenant trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "aucun participant avec cet id n'existe dans la BDD") })
    public Participation getParticipationById(@PathVariable("id") Long id) {
        return participationService.getParticipationById(id);
    }

    @GetMapping("/getParticipantActivite/{id}")
    @ApiOperation(value = "rechercher la liste des participants dont l'etat est active par activite ", notes = "cette methode permet de rechercher tous les participants dont l'etat est active dans une activité donné", response = Participation.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Liste des participants trouvé"),
            @ApiResponse(code = 404, message = "aucune liste trouvé") })
    public List<Participation> getParticipantByActivite(@PathVariable ("id") Long Id_activite){
        return participationService.participantByActivite(Id_activite);
    }


    @ApiOperation(value = "rechercher la liste des participants par etat", notes = "cette methode permet de rechercher tous les participants par etat", response = Participation.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Liste des participants trouvé"),
            @ApiResponse(code = 404, message = "aucune liste trouvé") })
    @GetMapping("/getParticipationEtat/{etat}")
    public List<Participation> participationByEtat(@PathVariable("etat") Etat etat) {
        return participationService.participationByEtat(etat);
    }

    @ApiOperation(value = "restaurer une participation", notes = "cette methode permet de restaurer une participation", response = Participation.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Restaurer avec succès"),
            @ApiResponse(code = 404, message = "Erreur de restauration , verifier votre requette") })
    @GetMapping("/restaure/{id}")
    public void restaurer(@PathVariable("id") Long id) {
        participationService.restaurer(id);
    }
}
