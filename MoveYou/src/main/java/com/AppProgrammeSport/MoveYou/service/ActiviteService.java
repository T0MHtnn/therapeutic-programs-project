package com.AppProgrammeSport.MoveYou.service;

import java.util.List;

import com.AppProgrammeSport.MoveYou.entity.Activite;
import com.AppProgrammeSport.MoveYou.entity.Utilisateur;

public interface ActiviteService {
	public Activite saveActivite(Activite activi);
	
	public List<Activite> saveMultipleActivites(List<Activite> Activites);

	public List<Activite> fetchActiviteList();

	public Activite fetchActiviteById(Long ActiviteId);

	public void deleteActiviteById(Long ActiviteId);
	
	public void deleteRelationActiviteByProgrammeTherapeutique(Long activiteId, Long therapeuticId);

	public Activite updateActivite(Long ActiviteId, Activite Activite);
	
	public Activite fetchActiviteByName(String ActiviteName);

	//public Activite fetchActiviteByNameIgnoreCase(String ActiviteName);
	
	public Activite fetchActiviteByPathologies(String ActivitePathologies);
	
	public List<Activite> chercherActivite(String motCle);

	public List<Activite> chercherActiviteRecommande(Utilisateur utilisateur);
		
	public List<Activite> fetchActiviteByProgrammeTherapeutiqueId(Long therapeuticId);

}
