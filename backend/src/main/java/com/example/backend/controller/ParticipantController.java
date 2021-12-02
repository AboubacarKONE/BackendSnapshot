package com.example.backend.controller;
import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;
import com.example.backend.repository.ParticipantRepository;
import com.example.backend.service.ParticipantService;
import com.example.backend.service.ParticipantServiceImplement;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class ParticipantController {
    @Autowired
    ParticipantService participantService;
    
    @Autowired
    ParticipantRepository participantRepository;

    @PostMapping(value="/participant")
    @ApiOperation(value = "Enregistrer un participant", notes = "cette methode permet d'ajouter un participant", response = Participant.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet participant cree"),
			@ApiResponse(code = 400, message = "l'objet participant n'est pas valide") })
    public void save(@RequestBody Participant participant){
        participantService.addParticipant(participant);
        
    }

    @PutMapping(path = "/participant/{id}")
    @ApiOperation(value = "Modifier un participant", notes = "cette methode permet de modifier un participant", response = Participant.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet participant modifié"),
			@ApiResponse(code = 400, message = "l'objet participant n'est pas valide") })
    public void update(@RequestBody Participant participant, @PathVariable Long id){
       participantService.updateParticipant(id,participant);
    }

    //pour afficher la liste
    @GetMapping("/participants")
    @ApiOperation(value = "renvoi la liste des particiapnt", notes = "cette methode permet de chercher et renvoyer la liste des participant qui existent"
			+ "dans la BDD", responseContainer = "list<particiapnt>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des particiapnt / une liste vide") })
    @ResponseBody
    public List<Participant> listParticipant(){
        return participantService.listParticipant();
    }
    
    //aficher participant par son id
    @GetMapping("/participantById/{id}")
    @ApiOperation(value = "renvoi d'un participant a travers son ID", notes = "cette methode permet de chercher et renvoyer un participant par son identifiant"
            + "dans la BDD", response = Participant.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "participant recuperer") })
    public Participant ParticipantById(@PathVariable("id") Long id) {
		return participantService.ParticipantById(id);
	}


    //Cette méthode permet récupérer par genre dont l'etat est inactive
    @GetMapping("/participantGenre/{genre}")
    public int findByparticipantGenre(@PathVariable("genre") ParticipantGenre genre){
    	return participantService.findByparticipantGenre(genre);
    }

    //Cette méthode permet d'importer le fichier Excel
    @PostMapping("/uploadExcel")
    public List <Participant> save(@RequestBody List<Participant> participants){
        return participantService.addParticipant(participants);
    }

    @PutMapping("/supprimer/{id_participant}")
    public void supprimer(@PathVariable("id_participant") Long id_participant){
        participantService.supprimer(id_participant);
    }

    @PutMapping("/recupere/{id}")
    public void recupere(@PathVariable("id") Long id_participant){
         participantService.recupere(id_participant);
    }

}
