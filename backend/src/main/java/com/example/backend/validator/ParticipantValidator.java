package com.example.backend.validator;

import com.example.backend.model.Participant;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ParticipantValidator {

    public static List<String> validator(Participant participant){
        List<String> error= new ArrayList<>();
        if(participant==null){
            error.add("Veuillez renseigner tous les champs ");
        }
        if(!StringUtils.hasLength(participant.getEmail())){
            error.add("Veuillez renseigner le mail ");
        }
        if(!StringUtils.hasLength(participant.getNom_complet())){
            error.add("Veuillez renseigner le nom complet ");
        }

        if(!StringUtils.hasLength(participant.getStructure())){
            error.add("Veuillez renseigner la structure ");
        }
        if(participant.getTelephone()==null){
            error.add("Veuillez renseigner le numero de telephone ");
        }
        return error;
    }

}
