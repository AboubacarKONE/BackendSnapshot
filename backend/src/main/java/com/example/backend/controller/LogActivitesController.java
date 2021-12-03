package com.example.backend.controller;

import java.util.List;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.LogActivitesRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backend.model.LogActivites;
import com.example.backend.service.LogActivitesService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class LogActivitesController {
	@Autowired
	LogActivitesService logactivitesService;
	@Autowired
	LogActivitesRepository repos;

	 //ajouter d'une historique
	@PostMapping("/addlog")
	@ApiOperation(value = "Enregistrer un logActivites", notes = "cette methode permet d'ajouter un log Activites", response = LogActivites.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet LogActivites cree"),
			@ApiResponse(code = 400, message = "Log Activites n'est pas valide") })
    public void savelog(@RequestBody LogActivites logactivites){
		logactivitesService.addLogActivites(logactivites);
       
    }


    

	//lister seul les activites qui sont active
	  @GetMapping("/listehistorique")
	  @ApiOperation(value = "lister actives active", notes = "cette methode permet d'afficher un log Activitess", response = LogActivites.class)
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Affichage l'objet LogActivites "),
			  @ApiResponse(code = 400, message = "Log Activites abscent") })
	  public List<LogActivites> listLogActivites(){
	        return logactivitesService.listLogActivites();
	    }
	//histoire activite par  identifiant
	    @GetMapping("/histoireById/{id}")
		@ApiOperation(value = "affichage un logActivites par Id", notes = "cette methode permet d'afficher un log Activites par Id", response = LogActivites.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "affichage log activites"),
				@ApiResponse(code = 400, message = "Log Activites n'est pas valide") })
	    public LogActivites listLogActivitebyid(@PathVariable("id") Long id){
	    	return logactivitesService.activiteByidAndEtat(id);
	    }

	//Update LogActivite en inactive
	@PutMapping("/histoireUpdate/{id}")
	public  void update( @PathVariable Long id ){
		repos.LogActivite(id);
	}

	    //supprimer une histoirique
            
         
            
	    @DeleteMapping("/deletehistoire/{id}")
	    public void suppressionhistoire(@PathVariable Long id){
	    	logactivitesService.deleteLogActivitesByid(id);
	        
	    }
	    @GetMapping("/logActivite/{IdActivite}")
		public List<LogActivites>listByActivite(@PathVariable("IdActivite") Long IdActivite){
			return logactivitesService.listLogActivites();
		}
}
