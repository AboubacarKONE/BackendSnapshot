package com.example.backend.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.backend.enumeration.Etat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogActivites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
        
	@Enumerated(EnumType.STRING)
	private Etat etat = Etat.active;
	
	@ManyToOne
	private Responsable responsable;
	@ManyToOne
	private Activite activite;

}
