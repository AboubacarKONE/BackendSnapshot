package com.example.backend.controller;

import java.util.List;

import com.example.backend.model.LogActivites;
import com.example.backend.model.Participation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

import com.example.backend.model.Exercice;
import com.example.backend.service.ExerciceService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class ExerciceController {
	@Autowired
	ExerciceService exerciceService;
	 //ajouter un exercice
	@PostMapping("/ajoutExercice")
	@ApiOperation(value = "Enregistrer un exercice", notes = "cette methode permet d'ajouter un exercice", response = Participation.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet exercice cree"),
			@ApiResponse(code = 400, message = "l'objet exercice n'est pas valide") })
    public void ajouExercice(@RequestBody Exercice exercice){
        exerciceService.ajoutExercice(exercice);
        
    }
	 //lister les exercice
	  @GetMapping("/listeExercice")
	  @ApiOperation(value = "renvoi la liste des exercices", notes = "cette methode permet de chercher et renvoyer la liste des exercices qui existent"
			  + "dans la BDD", response=Exercice.class)
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des exercices") })
	    public List<Exercice> listExercice(){
	        return exerciceService.listExercice();
	    }


	//un exercice par son identifiant
	    @GetMapping("/ExerciceById/{id}")
		@ApiOperation(value = "renvoi un exercice", notes = "cette methode permet de chercher et renvoyer un exercice par son identifiant"
				+ "dans la BDD", response= Exercice.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "affiche l'exercice cherché") })
	    public Exercice unExercice(@PathVariable("id") Long id){
	        return exerciceService.ExerciceById(id);
	    }


	  //mise à jour exercice
	    @PutMapping("/updateExercice/{id}")
		@ApiOperation(value = "Modifier un exercice", notes = "cette methode permet de modifier un exercice", response =Exercice.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet exercice modifié"),
				@ApiResponse(code = 400, message = "l'objet exercice n'est pas valide") })
	    public void reExercice(@PathVariable Long id, @RequestBody Exercice exercice){
	        exerciceService.updateExcercice(id, exercice);
	       
	    }
	  //supprimer un exercice
	    @DeleteMapping("/supprimerExercice/{id}")
		@ApiOperation(value = "suppression d'une exercice", notes = "cette methode permet de supprimer l' exercice"
				+ "dans la BDD", response=Exercice.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "supprimée avec succès") })
	    public void supExercice(@PathVariable Long id){
	        exerciceService.deleteExercice(id);
	       
	    }
	    @GetMapping("/ExerciceByYear={annee}")
		@ApiOperation(value = "renvoi une activité par année", notes = "cette methode permet de chercher et renvoyer l'exercice par son année"
				+ "dans la BDD", response=Exercice.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "exercice selon l'année") })
		public List<Exercice> recherExerciceAnnee(@PathVariable("annee") String annee){
	        return this.exerciceService.getExerciceByAnnee(annee);
	    }

}
