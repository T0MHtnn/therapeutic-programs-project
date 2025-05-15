package com.AppProgrammeSport.MoveYou.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.AppProgrammeSport.MoveYou.entity.CustomUserDetails;
import com.AppProgrammeSport.MoveYou.entity.Utilisateur;

public interface UtilisateurService { 
	public Utilisateur saveUtilisateur(Utilisateur user);
	
	public List<Utilisateur> saveMultipleUtilisateurs(List<Utilisateur> utilisateurs);

	public List<Utilisateur> fetchUtilisateurList();

	public Utilisateur fetchUtilisateurById(Long utilisateurId);

	public void deleteUtilisateurById(Long utilisateurId);

	public Utilisateur updateUtilisateur(Long utilisateurId, @AuthenticationPrincipal CustomUserDetails userDetails);

	public Utilisateur fetchUtilisateurByEmail(String utilisateurEmail);
	
	public String fetchMdpByEmail(String utilisateurEmail);
}
