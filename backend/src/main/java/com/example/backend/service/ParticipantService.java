package com.example.backend.service;

import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;
import java.util.List;
public interface ParticipantService {
    public String  addParticipant(Participant participant);
    public Participant updateParticipant(Long id, Participant participant);
    Participant ParticipantById(Long id_participant);
    int findByparticipantGenre(ParticipantGenre genre);
    public List<Participant> addParticipant(List<Participant> participants);
    public List<Participant> listParticipant();
    void supprimer(Long id_participant);
    void recupere(Long id_participant);

}
