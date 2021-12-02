package com.example.backend.controller;


import com.example.backend.enumeration.Etat;
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

    //Get all activite of Etat = active
    @GetMapping("/activites")
    @ApiOperation(value = "renvoi la liste des activités", notes = "cette methode permet de chercher et renvoyer la liste des activités qui existent"
			+ "dans la BDD", responseContainer = "liste<Activité>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des activités / une liste vide") })
    public List<Activite> getAllActivite(){

        return activiteService.getAllActivite();
    }
    //Get all activite of Etat = Inactive
    @GetMapping("/activitesInactive")
    @ApiOperation(value = "renvoi la liste des activités", notes = "cette methode permet de chercher et renvoyer la liste des activités qui existent"
            + "dans la BDD", responseContainer = "liste<Activité>")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des activités / une liste vide") })
    public List<Activite> getAllActiviteInactive(){

        return activiteService.getAllActiviteInactive();
    }
    //Add activite
    @PostMapping("/saveActivite")
    @ApiOperation(value = "Enregistrer une activité", notes = "cette methode permet d'ajouter une activité", response = Activite.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet activité cree"),
			@ApiResponse(code = 400, message = "l'objet activité n'est pas valide") })
    public void ajouterActivite(@RequestBody Activite activite){
        this.activiteService.ajouterActivite(activite);
         
    }

    //Update activite
    @PutMapping ("updateActivite/{Id_activite}")
    @ApiOperation(value = "Modifier une activité", notes = "cette methode permet de modifier une activité", response = Activite.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet activité modifié"),
			@ApiResponse(code = 400, message = "l'objet activité n'est pas valide") })
    public void modifierActivite(@RequestBody Activite activite, @PathVariable Long Id_activite) {
    this.activiteService.modifierActivite(Id_activite, activite);
    
     }
     //Desable activite
     @PutMapping ("desableActivite/{id}")
     @ApiOperation(value = "Modifier l'Etat d'une activité en inactive", notes = "cette methode permet de modifier l'Etat d'une activité en inactive", response = Activite.class)
     @ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet activité supprimer"),
             @ApiResponse(code = 400, message = "l'objet activité n'est pas valide") })
     public void desableActivite(@PathVariable("id") Long id) {
      this.activiteService.disableActivite(id);

     }
     //Enable activite
     @PutMapping ("enableActivite/{id}")
     @ApiOperation(value = "Modifier l'Etat d'une activité en active", notes = "cette methode permet de modifier l'Etat d'une activité en active", response = Activite.class)
     @ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet activité activé"),
             @ApiResponse(code = 400, message = "l'objet activité n'est pas valide") })
     public void enableActivite(@PathVariable("id") Long id) {
      this.activiteService.enableActivite(id);
     }

  //Activite by Etat active
    @GetMapping("/ActiviteById/{id}")
    @ApiOperation(value = "rechercher une activité active ", notes = "cette methode permet de rechercher une activité active par son id", response = Activite.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'activité trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "aucune activité active avec cet id n'existe dans la BDD") })
    public Activite AvoirUneActivite(@PathVariable("id") Long id) {
        return activiteService.listeById(id, Etat.active);
    }
    //Activite by Etat inactive
    @GetMapping("/ActiviteByIdInac/{id}")
    @ApiOperation(value = "rechercher une activité inactive", notes = "cette methode permet de rechercher une activité inactive par son id", response = Activite.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'activité trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "aucune activité avec cet id n'existe dans la BDD") })
    public Activite AvoirUneActiviteInactive(@PathVariable("id") Long id){
     return activiteService.listeById(id,Etat.inactive);
    }

     //activite by year
     @GetMapping("/activiteByAnnee={annee}")
     @ApiOperation(value = "rechercher une activité par annee", notes = "cette methode permet de rechercher une activité annee", response = Activite.class)
     @ApiResponses(value = { @ApiResponse(code = 200, message = "l'activité trouvé dans la BDD"),
             @ApiResponse(code = 404, message = "aucune activité n'existe dans la BDD") })
     List<Activite>findActiviteByAnnee(@PathVariable String annee){
    	 return activiteService.findActiviteByAnnee(annee);
     }
    //activite by month
     @GetMapping("/actviteByMonth={year}-{month}")
     @ApiOperation(value = "rechercher une activité mois", notes = "cette methode permet de rechercher une activité mois", response = Activite.class)
     @ApiResponses(value = { @ApiResponse(code = 200, message = "l'activité trouvé dans la BDD"),
             @ApiResponse(code = 404, message = "aucune activité n'existe dans la BDD") })
     public List<Activite> listByMonth(@PathVariable("year") int year, @PathVariable("month") int month ){
         return activiteService.getActiviteByMonth(year,month);
     }

}
