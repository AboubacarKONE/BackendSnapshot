package com.example.backend.validator;

import com.example.backend.model.Responsable;

import java.util.ArrayList;
import java.util.List;

public class ResponsableValidator {

    public static List<String> validator(Responsable responsable){
        List<String> error= new ArrayList<>();

        if(responsable==null){
            error.add("Merci de renseigner tous les champs");
        }
        if(responsable.getNom()==null){
            error.add("Merci de renseigner le nom");
        }
        if(responsable.getPrenom()==null){
            error.add("Merci de renseigner le prenom");
        }
        if(responsable.getNom()==null){
            error.add("Merci de renseigner le nom");
        }
        return error;
    }

}
