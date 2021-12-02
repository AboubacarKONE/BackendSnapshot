package com.example.backend.service;

import com.example.backend.enumeration.Etat;
import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;
import java.util.List;
public interface ParticipantService {
    public String  addParticipant(Participant participant);
    public Participant updateParticipant(Long id, Participant participant);
   // public List<Participant> listParticipant();
    void deleteParticipant( Long id);
    //Participant ParticipantById(Long id);
    int findByparticipantGenreAndEtat(ParticipantGenre genre);
    public List<Participant> addManyParticipant(List<Participant> participants);
    public Participant findByIdAndEtat(Long id_participant,Etat etat);
    public List<Participant> findByEtat(Etat etat);
    //modifier etat active par etat inactive
    void updateParticipant(Long id);
    //modifier etat d'un participant par active
    void updateInactiveparActive(Long id);
}
