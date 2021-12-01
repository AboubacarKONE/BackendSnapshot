package com.example.backend.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.TypeResponsable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Responsable {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_responsable;   
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
    @ManyToOne
    private Administrateur administrateur;
    
    

    
    
    
}
