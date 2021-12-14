package com.example.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.TypeResponsable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Responsable implements Serializable{
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    private String nom;
    private String prenom;
    private Long telephone;
    private String domaine;
    private String email;
    private String photoUrl;
    @Enumerated(EnumType.STRING)
    private TypeResponsable type;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @ManyToOne()
    private Administrateur administrateur;

    
    

    
    
    
}
