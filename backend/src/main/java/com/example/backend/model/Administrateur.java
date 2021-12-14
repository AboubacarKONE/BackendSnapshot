package com.example.backend.model;

import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
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
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String photoUrl;
	@Email
	private String email;
	@Enumerated(EnumType.STRING)
	private Etat etat = Etat.active;
	@Enumerated(EnumType.STRING)
	private Profile profile;
	private Long telephone;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Roles> roles = new ArrayList<>();



}
