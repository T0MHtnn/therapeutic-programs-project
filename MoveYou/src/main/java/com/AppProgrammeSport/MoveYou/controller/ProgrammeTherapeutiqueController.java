/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AppProgrammeSport.MoveYou.entity.ProgrammeTherapeutique;
import com.AppProgrammeSport.MoveYou.service.ProgrammeTherapeutiqueService;

import jakarta.validation.Valid;

/**
 * Contrôleur REST pour gérer les opérations liées à l'entité ProgrammeTherapeutique.
 */
@RestController
public class ProgrammeTherapeutiqueController {

    @Autowired
    private ProgrammeTherapeutiqueService programmeTherapeutiqueService;

    private final Logger LOGGER = LoggerFactory.getLogger(ProgrammeTherapeutiqueController.class);

    /**
     * Crée un programme thérapeutique à partir d'un fichier JSON.
     *
     * @param prog le programme thérapeutique à créer
     * @return le programme thérapeutique créé
     */
    @PostMapping("/programmesTherapeutiques/saveProgrammeTherapeutique")
    public ProgrammeTherapeutique saveProgrammeTherapeutique(@Valid @RequestBody ProgrammeTherapeutique prog) {
        LOGGER.info("Inside saveProgrammeTherapeutique");
        return programmeTherapeutiqueService.saveProgrammeTherapeutique(prog);
    }

    /**
     * Crée plusieurs programmes thérapeutiques à partir d'un fichier JSON.
     *
     * @param prog la liste de programmes thérapeutiques à créer
     * @return la liste des programmes thérapeutiques créés
     */
    @PostMapping("/programmesTherapeutiques/saveMultipleProgrammesTherapeutiques")
    public List<ProgrammeTherapeutique> saveMultipleProgrammeTherapeutique(@RequestBody List<ProgrammeTherapeutique> prog) {
        return programmeTherapeutiqueService.saveMultipleProgrammeTherapeutique(prog);
    }

    /**
     * Récupère la liste de tous les programmes thérapeutiques.
     *
     * @return la liste des programmes thérapeutiques
     */
    @GetMapping("/programmesTherapeutiques")
    public List<ProgrammeTherapeutique> fetchProgrammeTherapeutiqueList() {
        LOGGER.info("Inside fetchProgrammeTherapeutiqueList of ProgrammeTherapeutiqueController");
        return programmeTherapeutiqueService.fetchProgrammeTherapeutiqueList();
    }

    /**
     * Récupère un programme thérapeutique par son identifiant.
     *
     * @param programmeTherapeutiqueId l'identifiant du programme thérapeutique
     * @return le programme thérapeutique trouvé
     */
    @GetMapping("/programmesTherapeutiques/{id}")
    public ProgrammeTherapeutique fetchProgrammeTherapeutiqueById(@PathVariable("id") Long programmeTherapeutiqueId) {
        return programmeTherapeutiqueService.fetchProgrammeTherapeutiqueById(programmeTherapeutiqueId);
    }

    /**
     * Supprime un programme thérapeutique par son identifiant.
     *
     * @param programmeTherapeutiqueId l'identifiant du programme thérapeutique à supprimer
     * @return un message confirmant la suppression du programme thérapeutique
     */
    @DeleteMapping("/programmesTherapeutiques/delete/{id}")
    public String deleteProgrammeTherapeutiqueById(@PathVariable("id") Long programmeTherapeutiqueId) {
        programmeTherapeutiqueService.deleteProgrammeTherapeutiqueById(programmeTherapeutiqueId);
        return "ProgrammeTherapeutique " + programmeTherapeutiqueId + " delete";
    }

    /**
     * Met à jour le nom d'un programme thérapeutique existant.
     *
     * @param progId l'identifiant du programme thérapeutique
     * @param newName le nouveau nom du programme thérapeutique
     * @return le programme thérapeutique mis à jour
     */
    @GetMapping("/programmesTherapeutiques/update/{id}/name/{newName}")
    public ProgrammeTherapeutique updateProgrammeTherapeutique(@PathVariable("id") Long progId, @PathVariable("newName") String newName) {
        ProgrammeTherapeutique existingProgramme = programmeTherapeutiqueService.fetchProgrammeTherapeutiqueById(progId);
        if (existingProgramme == null) {
            throw new RuntimeException("Programme introuvable avec l'ID : " + progId);
        }

        // Mettre à jour le nom
        existingProgramme.setNom(newName);

        // Sauvegarder le programme mis à jour
        programmeTherapeutiqueService.updateProgrammeTherapeutiqueName(existingProgramme);
        return existingProgramme;
    }

    /**
     * Récupère un programme thérapeutique par son nom.
     *
     * @param nom le nom du programme thérapeutique
     * @return le programme thérapeutique trouvé
     */
    @GetMapping("/programmesTherapeutiques/name/{name}")
    public ProgrammeTherapeutique fetchProgrammeTherapeutiqueByNom(@PathVariable("name") String nom) {
        return programmeTherapeutiqueService.fetchProgrammeTherapeutiqueByNom(nom);
    }

    /**
     * Récupère les programmes thérapeutiques associés à un utilisateur spécifique.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return la liste des programmes thérapeutiques associés à l'utilisateur
     */
    @GetMapping("/programmesTherapeutiques/user/{userId}")
    public List<ProgrammeTherapeutique> fetchProgrammeTherapeutiqueByNom(@PathVariable("userId") Long userId) {
        return programmeTherapeutiqueService.fetchProgrammeTherapeutiqueByutilisateurId(userId);
    }

    /**
     * Ajoute une activité à un programme thérapeutique.
     *
     * @param activityId l'identifiant de l'activité
     * @param progId l'identifiant du programme thérapeutique
     * @return le nombre de relations ajoutées
     */
    @PostMapping("/programmesTherapeutiques/activite/{activityId}/{progId}")
    public Integer addActivityToProgramTherapeutique(@PathVariable("activityId") Long activityId, @PathVariable("progId") Long progId) {
        return programmeTherapeutiqueService.ajoutActiviteToProgrammeTherapeutique(progId, activityId);
    }
}
