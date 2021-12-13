package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
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
	@PostAuthorize("hasAuthority('AJOUTER')")
    public void savelog(@RequestBody LogActivites logactivites){
		logactivitesService.addLogActivites(logactivites);
       
    }
	//lister historique
	  @GetMapping("/listehistorique")
	  @PostAuthorize("hasAuthority('LISTER')")
	  public List<LogActivites> listLogActivites(){
	        return logactivitesService.listLogActivites();
	    }
	//histoire activite par  identifiant
	    @GetMapping("/histoireById/{id}")
	    @PostAuthorize("hasAuthority('LISTER')")
	    public LogActivites listLogActivitebyid(@PathVariable("id") Long id){
	    	return logactivitesService.listLogActivitebyid(id);
	    }
	    //supprimer une histoirique
	    @DeleteMapping("/deletehistoire/{id}")
	    @PostAuthorize("hasAuthority('SUPPRIMER')")
	    public void suppressionhistoire(@PathVariable Long id){
	    	logactivitesService.deleteLogActivitesByid(id);
	        
	    }
	    @GetMapping("/logActivite/{IdActivite}")
	    @PostAuthorize("hasAuthority('LISTER')")
		public List<LogActivites>listByActivite(@PathVariable("IdActivite") Long IdActivite){
			return logactivitesService.listByActivite(IdActivite);
		}
}
