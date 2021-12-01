package com.example.backend.service;

import java.util.List;

import com.example.backend.model.Responsable;

public interface ResponsableService {
	public Responsable ajouter_responsable(Responsable responsable);
	public Responsable modifier_responsable(Long id, Responsable responsable);
	public List<Responsable> list_responsable();
	public Responsable afficher_responsable_by_id(Long id);
	public void supprimer_responsable(Long id);
}
