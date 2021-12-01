package com.example.backend.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backend.model.Responsable;
@CrossOrigin("*")
public interface ResponsableRepository extends JpaRepositoryImplementation<Responsable, Long> {

}
