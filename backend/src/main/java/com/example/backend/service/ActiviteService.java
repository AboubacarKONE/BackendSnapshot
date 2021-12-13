package com.example.backend.service;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Activite;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
public interface ActiviteService {
     void ajouterActivite (Activite activite);
     void modifierActivite (Long Id_activite, Activite activite);
     Activite listeById (Long IdActivite, Etat etat);//activite by etat active
     Activite listeByIdInactive (Long id, Etat etat);//activite by etat inactive
     List<Activite> getAllActivite();//all activite by etat active
     List<Activite> getAllActiviteInactive();//all activite by etat inactive
     String disableActivite (Long id);//desactive activite
     String enableActivite(Long id);//active Activite
     List<Activite>findActiviteByAnnee(String annee);
     List<Activite> getActiviteByMonth(int year, int month);

}
