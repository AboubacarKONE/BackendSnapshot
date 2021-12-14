package com.example.backend.validator;

import com.example.backend.model.Exercice;

import java.util.ArrayList;
import java.util.List;

public class ExerciceValidator {

    public static List<String > validator(Exercice exercice){
        List <String> error= new ArrayList<>();

        if(exercice==null){
            error.add("Merci de renseigner tous les champs");
        }
        if(exercice.getDate_debut()==null){
            error.add("Veuillez renseigner la date de debut");
        }
        if(exercice.getDate_fin()==null){
            error.add("Veuillez renseigner la date de fin");
        }
        if(exercice.getEtat()==null){
            error.add("Veuillez renseigner l'etat");
        }
        if(exercice.getStatut()==null){
            error.add("Veuillez renseigner le statut ");
        }
        if(exercice.getStatut()==null){
            error.add("Veuillez renseigner l'annee ");
        }

        return error;
    }

}
