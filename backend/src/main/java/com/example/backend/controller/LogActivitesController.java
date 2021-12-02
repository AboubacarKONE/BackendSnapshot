package com.example.backend.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	 //ajouter d'une historique
	@PostMapping("/addlog")
    public void savelog(@RequestBody LogActivites logactivites){
		logactivitesService.addLogActivites(logactivites);
       
    }
	//lister historique
	  @GetMapping("/listehistorique")
	  @ApiOperation(value = "renvoi la liste des historiques", notes = "cette methode permet de chercher et renvoyer les historiques des activités "
			  + "dans la BDD", response=LogActivites.class)
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "l'historique des activités") })
	  public List<LogActivites> listLogActivites(){
	        return logactivitesService.listLogActivites();
	    }

	//histoire activite par  identifiant
	    @GetMapping("/histoireById/{id}")
		@ApiOperation(value = "renvoi historique d'une activité", notes = "cette methode permet de chercher et renvoyer l'historique d'une activité"
				+ "dans la BDD", response=LogActivites.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "historique de l'activité cherchée") })
	    public LogActivites listLogActivitebyid(@PathVariable("id") Long id){
	    	return logactivitesService.listLogActivitebyid(id);
	    }
	    //supprimer une histoirique
	    @DeleteMapping("/deletehistoire/{id}")
		@ApiOperation(value = "suppression d'une activité", notes = "cette methode permet de supprimer l' activité"
				+ "dans la BDD", response=LogActivites.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "supprimée avec succès") })
	    public void suppressionhistoire(@PathVariable Long id){
	    	logactivitesService.deleteLogActivitesByid(id);
	        
	    }
	    @GetMapping("/logActivite/{IdActivite}")
		@ApiOperation(value = "renvoi historique d'une activité", notes = "cette methode permet de chercher et renvoyer l'historique d'une activité"
				+ "dans la BDD", response=LogActivites.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "historique de l'activité cherchée") })
		public List<LogActivites>listByActivite(@PathVariable("IdActivite") Long IdActivite){
			return logactivitesService.listByActivite(IdActivite);
		}
}
