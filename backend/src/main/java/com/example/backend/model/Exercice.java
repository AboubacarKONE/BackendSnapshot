package com.example.backend.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.Statut;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exercice implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String annee;
	private Date date_debut;
	private Date date_fin;
	@Enumerated(EnumType.STRING)	
	private Statut statut;
	@Enumerated(EnumType.STRING)
	private Etat etat;
	@ManyToOne
	private Administrateur admin;
	

}
