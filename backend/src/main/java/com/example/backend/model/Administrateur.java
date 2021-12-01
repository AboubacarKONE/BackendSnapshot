package com.example.backend.model;

import com.example.backend.enumeration.Etat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Administrateur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String password;
	private String photoUrl;
	@Email
	private String email;
	@Enumerated(EnumType.STRING)
	private Etat etat;
	private Long telephone;

	// @NotNull(message = "Veuillez renseigner le rôle...")
	@OneToMany(mappedBy = "administrateur", fetch = FetchType.EAGER)
	@JsonProperty(access =Access.WRITE_ONLY)
	private List<Role> roles;

}
