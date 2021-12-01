/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend.controller;

import com.example.backend.model.Role;
import com.example.backend.service.RoleServiceImp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Optional;
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

/**
 *
 * @author hady.fofana
 */
@RestController
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class RoleControler {

   @Autowired
   RoleServiceImp roleserviceimp;
   
   @PostMapping("/role/add")
   @ApiOperation(value = "Enregistrer un role", notes = "cette methode permet d'ajouter un role", response = Role.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet role cree"),
			@ApiResponse(code = 400, message = "l'objet role n'est pas valide") })
    public Role save (@RequestBody Role role){
        return roleserviceimp.ajouter_role(role);
    }
    @GetMapping("/role/all")
    @ApiOperation(value = "renvoi la liste des roles", notes = "cette methode permet de chercher et renvoyer la liste des roles qui existent"
			+ "dans la BDD", responseContainer = "list<Role>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des roles / une liste vide") })
   public List <Role> listeRole(){
       return roleserviceimp.listeRole();
   }
   
   @GetMapping("/role/detail/{id}")
   @ApiOperation(value = "rechercher un role", notes = "cette methode permet de rechercher un role par son id", response = Role.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "le role trouvé dans la BDD"),
			@ApiResponse(code = 404, message = "aucun role avec cet id n'existe dans la BDD") })
   public Role getRoleById(@PathVariable Long id){
       return roleserviceimp.getRoleById(id);
   }
   
    @DeleteMapping ("/role/delete/{id}")
    @ApiOperation(value = "supprimer un role", notes = "cette methode permet de supprimer un role par son id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "le role a été supprimé"),
			@ApiResponse(code = 404, message = "aucun role avec cet id n'existe dans la BDD") })
    public void supprimer(@PathVariable Long id){
    	roleserviceimp.suprimer_role(id);
        
    }
    
    @PutMapping ("/role/update/{id}")
    @ApiOperation(value = "Modifier un role", notes = "cette methode permet de modifier un role", response = Role.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet role modifié"),
			@ApiResponse(code = 400, message = "l'objet role n'est pas valide") })
    public Role update(@RequestBody Role role, @PathVariable Long id){
        return roleserviceimp.modifier_role(id, role);
    }
    @GetMapping("/role/verification={libelle}")
    @ApiOperation(value = "rechercher un role par libelle", notes = "cette methode permet de rechercher un role par son libelle", response = Role.class)
 	@ApiResponses(value = { @ApiResponse(code = 200, message = "le libelle trouvé dans la BDD"),
 			@ApiResponse(code = 404, message = "aucun role avec cet libelle n'existe dans la BDD") })
    public Role getRoleByLibelle(@PathVariable String libelle){
        return roleserviceimp.verifie_role(libelle);
    }
    
 
}
