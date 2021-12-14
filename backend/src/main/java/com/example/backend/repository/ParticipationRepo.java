package com.example.backend.repository;

import com.example.backend.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("*")
public interface ParticipationRepo extends JpaRepository<Participation, Long> {
}
