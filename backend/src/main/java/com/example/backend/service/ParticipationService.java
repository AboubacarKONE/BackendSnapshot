package com.example.backend.service;

import com.example.backend.model.Participation;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface ParticipationService {

    public Participation ajouterParticipation(Participation p);
    void deleteParticipation(Long id);
    public void updateParticipation(Long id, Participation p);
    public List<Participation> getAllParticipation();
    public Participation getParticipationById(Long id);

}
