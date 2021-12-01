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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;


import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.TypeActivite;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Data
@Entity
public class Activite {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long IdActivite;
    private  String libelle;
    @Enumerated(EnumType.STRING)
    private  TypeActivite type;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @ManyToOne
    private Administrateur administrateur;
    @ManyToOne
    private Exercice exercice;
    @OneToMany(mappedBy = "activite", cascade = CascadeType.ALL)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Participation> participations; 


}
