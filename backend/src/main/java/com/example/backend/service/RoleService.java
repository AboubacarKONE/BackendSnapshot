/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend.service;



import com.example.backend.model.Roles;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author hady.fofana
 */
@Service
public interface RoleService {
    
   public Roles ajouter_role (Roles role);
   public Roles modifier_role (Long Id, Roles role);
   void suprimer_role (Long Id);
   public List <Roles> listeRole();
   public Roles getRoleById (Long id);
   public Roles verifie_role (String libelle);
    

}
