package com.example.backend.repository;

import com.example.backend.model.Administrateur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;



@RepositoryRestResource
@CrossOrigin("*")
public interface AdminRepository extends JpaRepository<Administrateur, Long> {
	//List<Administrateur>findAllByRoleId(Long id);
	@Query(value="select u from Administrateur u where u.email = :email")
    Optional<Administrateur>findByEmail(@Param("email") String email);
}
