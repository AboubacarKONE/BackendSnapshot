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
    public void ajouExercice(@RequestBody Exercice exercice){
        exerciceService.ajoutExercice(exercice);
        
    }
	 //lister les exercice
	  @GetMapping("/listeExercice")
	    public List<Exercice> listExercice(){
	        return exerciceService.listExercice();
	    }
	//un exercice par son identifiant
	    @GetMapping("/ExerciceById/{id}")
	    public Exercice unExercice(@PathVariable("id") Long id){
	        return exerciceService.ExerciceById(id);
	    }
	  //mise à jour exercice
	    @PutMapping("/updateExercice/{id}")
	    public void reExercice(@PathVariable Long id, @RequestBody Exercice exercice){
	        exerciceService.updateExcercice(id, exercice);
	       
	    }
	  //supprimer un exercice
	    @DeleteMapping("/supprimerExercice/{id}")
	    public void supExercice(@PathVariable Long id){
	        exerciceService.deleteExercice(id);
	       
	    }
	    @GetMapping("/ExerciceByYear={annee}")
	    public List<Exercice> recherExerciceAnnee(@PathVariable("annee") String annee){
	        return this.exerciceService.getExerciceByAnnee(annee);
	    }

}
