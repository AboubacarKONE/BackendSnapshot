package com.example.backend.controller;


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
    @ApiOperation(value = "supprimer une participation", notes = "cette methode permet de supprimer une participation par son id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la participation a été supprimé"),
			@ApiResponse(code = 404, message = "aucune participation avec cet id n'existe dans la BDD") })
    public void deleteParticipation(@PathVariable("id") Long id) {
        participationService.deleteParticipation(id);
    }

    @PutMapping("/updateParticipation/{id}")
    @ApiOperation(value = "Modifier une participation", notes = "cette methode permet de modifier une participation", response = Participation.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet participation modifié"),
			@ApiResponse(code = 400, message = "l'objet participation n'est pas valide") })
    public void updateParticipation(@PathVariable("id") Long id ,@RequestBody Participation p) {
       
    }

    @GetMapping("/listeParticipation")
    @ApiOperation(value = "renvoi la liste des participations", notes = "cette methode permet de chercher et renvoyer la liste des participations qui existent"
			+ "dans la BDD", responseContainer = "liste<Participation>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des participants / une liste vide") })
    public List<Participation> getAllParticipation() {
        return participationService.getAllParticipation();
    }

    @GetMapping("/getActivite/{id}")
    @ApiOperation(value = "rechercher un participation", notes = "cette methode permet de rechercher un participant par son id", response = Participation.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'apprenant trouvé dans la BDD"),
			@ApiResponse(code = 404, message = "aucun participant avec cet id n'existe dans la BDD") })
    public Participation getParticipationById(@PathVariable("id") Long id) {
        return participationService.getParticipationById(id);
    }
}
