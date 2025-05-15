/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AppProgrammeSport.MoveYou.entity.CustomUserDetails;
import com.AppProgrammeSport.MoveYou.entity.Utilisateur;
import com.AppProgrammeSport.MoveYou.service.UtilisateurService;

import jakarta.validation.Valid;

/**
 * Contrôleur REST pour gérer les opérations liées à l'entité Utilisateur.
 */
@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);

    /**
     * Crée un utilisateur à partir d'un fichier JSON.
     *
     * @param utilisateur l'utilisateur à créer
     * @return l'utilisateur créé
     */
    @PostMapping("/utilisateurs")
    public Utilisateur saveUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        LOGGER.info("Inside saveUtilisateur");
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    /**
     * Crée plusieurs utilisateurs à partir d'un fichier JSON.
     *
     * @param utilisateurs la liste d'utilisateurs à créer
     * @return la liste des utilisateurs créés
     */
    @PostMapping("/utilisateurs/multipleUtilisateurs")
    public List<Utilisateur> saveMultipleUtilisateurs(@RequestBody List<Utilisateur> utilisateurs) {
        return utilisateurService.saveMultipleUtilisateurs(utilisateurs);
    }

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return la liste des utilisateurs
     */
    @GetMapping("/utilisateurs")
    public List<Utilisateur> fetchUtilisateurList() {
        LOGGER.info("Inside fetchUtilisateurList of UtilisateurController");
        return utilisateurService.fetchUtilisateurList();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param utilisateurId l'identifiant de l'utilisateur
     * @return l'utilisateur trouvé
     */
    @GetMapping("/utilisateurs/{id}")
    public Utilisateur fetchUtilisateurById(@PathVariable("id") Long utilisateurId) {
        return utilisateurService.fetchUtilisateurById(utilisateurId);
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param utilisateurId l'identifiant de l'utilisateur à supprimer
     * @return un message confirmant la suppression de l'utilisateur
     */
    @DeleteMapping("/utilisateurs/delete/{id}")
    public String deleteUtilisateurById(@PathVariable("id") Long utilisateurId) {
        utilisateurService.deleteUtilisateurById(utilisateurId);
        return "Utilisateur " + utilisateurId + " supprimé";
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param utilisateurId l'identifiant de l'utilisateur à mettre à jour
     * @param userDetails les nouveaux détails de l'utilisateur
     * @return l'utilisateur mis à jour
     */
    @PutMapping("/utilisateurs/update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable("id") Long utilisateurId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return utilisateurService.updateUtilisateur(utilisateurId, userDetails);
    }

    /**
     * Récupère un utilisateur par son email.
     *
     * @param utilisateurEmail l'email de l'utilisateur
     * @return l'utilisateur trouvé
     */
    @GetMapping("/utilisateurs/email/{email}")
    public Utilisateur fetchUtilisateurByEmail(@PathVariable("email") String utilisateurEmail) {
        return utilisateurService.fetchUtilisateurByEmail(utilisateurEmail);
    }

    /**
     * Récupère le mot de passe d'un utilisateur par son email.
     *
     * @param utilisateurEmail l'email de l'utilisateur
     * @return le mot de passe de l'utilisateur, ou null si non trouvé
     */
    @GetMapping("/utilisateurs/mdp/{email}")
    public String fetchMdpByEmail(@PathVariable("email") String utilisateurEmail) {
        return utilisateurService.fetchMdpByEmail(utilisateurEmail);
    }
}
