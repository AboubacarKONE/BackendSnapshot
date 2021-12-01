package com.example.backend.model;

import com.example.backend.enumeration.Etat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_participation;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Activite activite;
    @ManyToOne
    private Participant participant;
    private Time heure_arriver;
    private boolean presence;
    @ManyToOne
    private Administrateur administrateur;
    @Enumerated(EnumType.STRING)
    private Etat etat = Etat.active;
}
