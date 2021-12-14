package com.example.backend.validator;

import com.example.backend.model.Activite;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ActiviteValidator {

    public static List<String> validator(Activite activite){
        List<String> errors=new ArrayList<>();

        if (activite==null){
            errors.add("Merci de renseigner les champs requis");
        }
        if(!StringUtils.hasLength(activite.getLibelle())){
            errors.add("Veuillez renseigner le champs libelle");
        }
        if(activite.getDateDebut()==null){
            errors.add("Veuillez renseigner la date de debut");
        }
        if(activite.getDateFin()==null){
            errors.add("Veuillez renseigner la date de fin");

        }
        if(activite.getType()==null){
            errors.add("Veuillez renseigner le type de l'activité");
        }

        if(activite.getEtat()==null){
            errors.add("Veuillez renseigner l'etat");
        }
        if(activite.getExercice()==null || activite.getExercice().getId()==null){
            errors.add("Veuillez renseigner l'exercice ");
        }
        return  errors;
    }

}
