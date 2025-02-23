package com.example.backend.controller;

import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.enumeration.Etat;
import com.example.backend.model.Administrateur;
import com.example.backend.service.AdminService;
import com.example.backend.service.AdminServiceImpl;
import com.example.backend.validator.AdministrateurValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.transaction.Transactional;

@RestController
@CrossOrigin("*")
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
public class AdminController {

    @Autowired
    AdminService adminService;

    //la liste globale
    @GetMapping("/listAdmin")
    @PostAuthorize("hasAuthority('LISTER')")
    @ApiOperation(value = "renvoi la liste des administrateurs", notes = "cette methode permet de chercher et renvoyer la liste des administrateurs qui existent"
			+ "dans la BDD", responseContainer = "list<Administrateur>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des administrateurs / une liste vide") })
    public ResponseEntity<?> getAllAdministrateur(){
    return new ResponseEntity<>(adminService.getAllAdministrateur(), HttpStatus.OK);
}

    //l'insertion
    @PostMapping("/saveAdmin")
    @PostAuthorize("hasAuthority('AJOUTER')")
    @ApiOperation(value = "Enregistrer un administrateur", notes = "cette methode permet d'ajouter un administrateur", response = Administrateur.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet administrateur cree"),
			@ApiResponse(code = 400, message = "l'objet administrateur n'est pas valide") })
    public @ResponseBody ResponseEntity<?> save(@RequestBody Administrateur admin){
    List<String>errors=AdministrateurValidator.validator(admin);
    if(!errors.isEmpty()) {
    	throw new InvalidEntityException("l'administrateur n'est pas valide",ErrorCodes.ADMINISTRATEUR_INVALID,errors);
    }
    return new ResponseEntity<>(adminService.saveAdmin(admin), HttpStatus.OK);
    }

    //la modification
    @PutMapping("/updateAdmin/{id}")
    @PostAuthorize("hasAuthority('MODIFIER')")
    @ApiOperation(value = "Modifier un administrateur", notes = "cette methode permet de modifier un administrateur", response = Administrateur.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet administrateur modifié"),
			@ApiResponse(code = 400, message = "l'objet administrateur n'est pas valide") })
    public @ResponseBody ResponseEntity<?> update(@RequestBody Administrateur admin,@PathVariable(name = "id") Long id){

        return new ResponseEntity<>	(adminService.updateAdmin(id, admin), HttpStatus.OK);
    }
    //listeByID
    @GetMapping("/adminById/{id}")
    @PostAuthorize("hasAuthority('LISTER')")
    public Administrateur AdminById(@PathVariable Long id) {
		return adminService.getAdById(id);
	}
    //Suppression
    @DeleteMapping("/deleteAdmin/{id}")
    @PostAuthorize("hasAuthority('SUPPRIMER')")
    @ApiOperation(value = "supprimer un administrateur", notes = "cette methode permet de supprimer un administrateur par son id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'administrateur a été supprimé"),
			@ApiResponse(code = 404, message = "aucun administrateur avec cet id n'existe dans la BDD") })
    public void delete(@PathVariable(name = "id") Long id){
    	adminService.deleteAdmin(id);
    }
    //liste admin par état
    @GetMapping("/adminParEtat/{etat}")
    public List<Administrateur> getAdministrateurByEtat(@PathVariable("etat") Etat etat) {
        return adminService.getAdministrateurByEtat(etat);
    }

    //archiver admin
    @PutMapping("/archiverAdmin/{id}")
    public String UpdateAdministrateur (@PathVariable("id") Long id, @RequestBody  Administrateur administrateur){
        return "mis a jour effectuée";
    }

}

