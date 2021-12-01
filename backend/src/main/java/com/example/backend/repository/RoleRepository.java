/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend.repository;


import com.example.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;



/**
 *
 * @author hady.fofana
 */
@RepositoryRestResource
@CrossOrigin("*")
public interface RoleRepository extends JpaRepository<Role,Long>{
	Role findByLibelle (String libelle);
    
}
