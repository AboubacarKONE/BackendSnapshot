/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend.service;


import com.example.backend.model.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author hady.fofana
 */
@Service
public interface RoleService {
    
   public Role ajouter_role (Role role);
   public Role modifier_role (Long Id, Role role);
   void suprimer_role (Long Id);
   public List <Role> listeRole();
   public Role getRoleById (Long id);
   public Role verifie_role (String libelle);
    

}
