package com.example.backend.service;

import java.util.List;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.Responsable;

public interface ResponsableService {
	public Responsable ajouter_responsable(Responsable responsable);
	public Responsable modifier_responsable(Long id, Responsable responsable);
	public List<Responsable> list_responsable();
	public  List<Responsable> list_responsableByEtat(Etat etat);
	public Responsable afficher_responsable_by_id(Long id);
	public void supprimer_responsable(Long id);
	public void resetresponsable(Long id);
	public  List<Responsable> list_responsable_active();
	public List<Responsable> list_responsable_inactive();



}
