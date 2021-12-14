package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
import com.example.backend.enumeration.TypeActivite;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Data
@Entity
public class Activite implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private  String libelle;
    @Enumerated(EnumType.STRING)
    private  TypeActivite type;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Enumerated(EnumType.STRING)
    private Etat etat = Etat.active;
    @ManyToOne
    private Administrateur administrateur;
    @ManyToOne
    private Exercice exercice;
    @OneToMany(mappedBy = "activite", cascade = CascadeType.ALL)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Participation> participations;



}
