package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import com.example.backend.enumeration.Etat;

import java.io.Serializable;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Participation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time heure_arriver;
    private boolean presence;
    @Enumerated(EnumType.STRING)
    private Etat etat = Etat.active;
    @ManyToOne()
    private Activite activite;
    @ManyToOne
    private Participant participant;    
    @ManyToOne
    private Administrateur administrateur;
   
}
