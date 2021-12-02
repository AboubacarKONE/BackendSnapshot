package com.example.backend.repository;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Administrateur;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;


@RepositoryRestResource
@CrossOrigin("*")
public interface AdminRepository extends JpaRepository<Administrateur, Long> {
	//List<Administrateur>findAllByRoleId(Long id);
	@Query(value = "select u from Administrateur u where u.email = :email")
	Optional<Administrateur> findByEmail(@Param("email") String email);

	Optional<Administrateur> getUserByLoginAndPassword(String login, String password);

	@Query(value = "SELECT ad FROM Administrateur ad WHERE ad.etat = 'active' ")
	List<Administrateur>getAllAdministrateur();

	@Query(value = "SELECT ad FROM Administrateur ad  WHERE ad.id = :id AND ad.etat = 'active' ")
	Administrateur getAdById(@Param("id")Long id);

	@Query(value = "SELECT Ad FROM Administrateur Ad WHERE Ad.etat = :etat")
	List<Administrateur> getAllAdministrateurByEtat(@Param("etat") Etat etat);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Administrateur SET etat = 'inactive' WHERE id = :id ")
	void deleteAdminEtat(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Administrateur SET etat = 'active' WHERE id = :id ")
	void resetAdminEtat(@Param("id") Long id);
}
