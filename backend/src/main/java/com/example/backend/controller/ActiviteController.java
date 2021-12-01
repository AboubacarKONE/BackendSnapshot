package com.example.backend.controller;


import com.example.backend.model.Activite;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.ActiviteRepository;
import com.example.backend.service.ActiviteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class ActiviteController {
   @Autowired
   ActiviteService activiteService;

    //lister toutes les activités
    @GetMapping("/activites")
    @ApiOperation(value = "renvoi la liste des activités", notes = "cette methode permet de chercher et renvoyer la liste des activités qui existent"
			+ "dans la BDD", responseContainer = "liste<Activité>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des activités / une liste vide") })
    public List<Activite> getAllActivite(){
    return activiteService.getAllActivite();
    }

    //AJOUTER UNE ACTIVITE
    @PostMapping("/saveActivite")
    @ApiOperation(value = "Enregistrer une activité", notes = "cette methode permet d'ajouter une activité", response = Activite.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet activité cree"),
			@ApiResponse(code = 400, message = "l'objet activité n'est pas valide") })
    public void ajouterActivite(@RequestBody Activite activite){
        this.activiteService.ajouterActivite(activite);
         
    }

    //MODIFIER UNE ACTIVITE
    @PutMapping ("updateActivite/{Id_activite}")
    @ApiOperation(value = "Modifier une activité", notes = "cette methode permet de modifier une activité", response = Activite.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet activité modifié"),
			@ApiResponse(code = 400, message = "l'objet activité n'est pas valide") })
    public void modifierActivite(@RequestBody Activite activite, @PathVariable Long Id_activite) {
    this.activiteService.modifierActivite(Id_activite, activite);
    
     }

    //AVOIR UNE ACTIVITE PAR ID²
    @GetMapping("/ActiviteById/{Id_activite}")
    @ApiOperation(value = "rechercher une activité", notes = "cette methode permet de rechercher une activité par son id", response = Activite.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'activité trouvé dans la BDD"),
			@ApiResponse(code = 404, message = "aucune activité avec cet id n'existe dans la BDD") })
    public Activite AvoirUneActivite(@PathVariable("Id_activite") Long Id_activite){
        return activiteService.listeById(Id_activite);
    }

    //SUPPRIMER ACTIVITE
     @DeleteMapping("/supprimerActivite/{Id_activite}")
     @ApiOperation(value = "supprimer une activité", notes = "cette methode permet de supprimer une activité par son id")
 	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'apprenant a été supprimé"),
 	@ApiResponse(code = 404, message = "aucune activité avec cet id n'existe dans la BDD") })
    public String supprimerActiviteById(@PathVariable ("Id_activite") long Id_activite){
    return this.activiteService.supprimerActiviteById(Id_activite);
     }
     //activite par anne
     @GetMapping("/activiteByAnnee={annee}")
     List<Activite>findActiviteByAnnee(@PathVariable String annee){
    	 return activiteService.findActiviteByAnnee(annee);
     }
     @GetMapping("/actviteByMonth={year}-{month}")
     public List<Activite> listByMonth(@PathVariable("year") int year, @PathVariable("month") int month ){
         return activiteService.getActiviteByMonth(year,month);
     }

}
