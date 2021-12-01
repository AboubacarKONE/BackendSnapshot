package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiResponse;

import com.example.backend.model.Responsable;
import com.example.backend.service.ResponsableService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class ResponsableController {
	@Autowired
	ResponsableService responsableService;
	
	//Affichage de la liste
    @GetMapping("/responsables")
    @ApiOperation(value = "renvoi la liste des responsables", notes = "cette methode permet de chercher et renvoyer la liste des responsables qui existent"
			+ "dans la BDD", responseContainer = "list<Responsable>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des responsables / une liste vide") })
    public List<Responsable> getResponsableList(){
    return  responsableService.list_responsable();
    }
    
    //Affichage par id
    @GetMapping(path = "/responsable/{id}")
    @ApiOperation(value = "rechercher un responsable", notes = "cette methode permet de rechercher un responsable par son id", response = Responsable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "le responsable trouvé dans la BDD"),
			@ApiResponse(code = 404, message = "aucun responsable avec cet id n'existe dans la BDD") })
    public Responsable getOneresponsable(@PathVariable(value= "id") Long id){
        return responsableService.afficher_responsable_by_id(id);
    }
    
    //Ajout d'un responsable
    @PostMapping(path="/saveResponsable")
    @ApiOperation(value = "Enregistrer un responsable", notes = "cette methode permet d'ajouter un responsable", response = Responsable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet responsable cree"),
			@ApiResponse(code = 400, message = "l'objet responsable n'est pas valide") })
    public Responsable save(@RequestBody Responsable responsable) {
        return responsableService.ajouter_responsable(responsable);
    }

    //Modification d'un responsable par son id
    @PutMapping("/updateResponsable/{id}")
    @ApiOperation(value = "Modifier un reponsable", notes = "cette methode permet de modifier un reponsable", response = Responsable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet reponsable modifié"),
			@ApiResponse(code = 400, message = "l'objet reponsable n'est pas valide") })
    public Responsable updateResponsable(@PathVariable Long id, @RequestBody Responsable responsable){
        return responsableService.modifier_responsable(id, responsable);
    }
    @DeleteMapping("/deleteResponsable/{id}")
    @ApiOperation(value = "supprimer un reponsable", notes = "cette methode permet de supprimer un reponsable par son id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "le reponsable a été supprimé"),
			@ApiResponse(code = 404, message = "aucun reponsable avec cet id n'existe dans la BDD") })
    public void deleteresponsable(@PathVariable Long id) {
        responsableService.supprimer_responsable(id);
    }
}