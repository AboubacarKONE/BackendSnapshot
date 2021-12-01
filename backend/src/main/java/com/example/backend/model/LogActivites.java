package com.example.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private Long id_LogActivites;
	
	@ManyToOne
	private Responsable responsable;
	@ManyToOne
	private Activite activite;

}
