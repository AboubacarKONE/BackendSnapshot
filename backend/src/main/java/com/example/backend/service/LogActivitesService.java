package com.example.backend.service;

import java.util.List;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.LogActivites;

public interface LogActivitesService {

     //Ajouter une log d'activité
	 void addLogActivites(LogActivites act);

     //Lister les logs d'activité
     List<LogActivites> listLogActivites();

     //Recuperation par id en tenant compte de son etat
     LogActivites activiteByidAndEtat(Long id);

    //Effacer un log d'activité par son id
    void deleteLogActivitesByid(Long id);
}
