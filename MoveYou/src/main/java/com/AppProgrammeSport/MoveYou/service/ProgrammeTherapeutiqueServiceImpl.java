/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppProgrammeSport.MoveYou.entity.ProgrammeTherapeutique;
import com.AppProgrammeSport.MoveYou.repository.ProgrammeTherapeutiqueRepository;

import jakarta.transaction.Transactional;

/**
 * Implémentation du service pour l'entité ProgrammeTherapeutique.
 */
@Service
public class ProgrammeTherapeutiqueServiceImpl implements ProgrammeTherapeutiqueService {

    @Autowired
    private ProgrammeTherapeutiqueRepository programmeTherapeutiqueRepository;

    /**
     * Enregistre un programme thérapeutique.
     *
     * @param prog le programme thérapeutique à enregistrer
     * @return le programme thérapeutique enregistré
     */
    @Override
    public ProgrammeTherapeutique saveProgrammeTherapeutique(ProgrammeTherapeutique prog) {
        return programmeTherapeutiqueRepository.save(prog);
    }

    /**
     * Enregistre une liste de programmes thérapeutiques.
     *
     * @param progs la liste de programmes thérapeutiques à enregistrer
     * @return la liste des programmes thérapeutiques enregistrés
     */
    @Override
    public List<ProgrammeTherapeutique> saveMultipleProgrammeTherapeutique(List<ProgrammeTherapeutique> progs) {
        return programmeTherapeutiqueRepository.saveAll(progs);
    }

    /**
     * Récupère la liste de tous les programmes thérapeutiques.
     *
     * @return la liste des programmes thérapeutiques
     */
    @Override
    public List<ProgrammeTherapeutique> fetchProgrammeTherapeutiqueList() {
        return programmeTherapeutiqueRepository.findAll();
    }

    /**
     * Récupère un programme thérapeutique par son identifiant.
     *
     * @param therapeutiqueId l'identifiant du programme thérapeutique
     * @return le programme thérapeutique trouvé
     */
    @Override
    public ProgrammeTherapeutique fetchProgrammeTherapeutiqueById(Long therapeutiqueId) {
        return programmeTherapeutiqueRepository.findById(therapeutiqueId).get();
    }

    /**
     * Supprime un programme thérapeutique par son identifiant.
     *
     * @param therapeutiqueId l'identifiant du programme thérapeutique à supprimer
     */
    @Transactional
    @Override
    public void deleteProgrammeTherapeutiqueById(Long therapeutiqueId) {
        if (programmeTherapeutiqueRepository.existsById(therapeutiqueId)) {
            programmeTherapeutiqueRepository.deleteRelationsWithActivities(therapeutiqueId);
            programmeTherapeutiqueRepository.deleteById(therapeutiqueId);
            System.out.println("Programme supprimé avec succès : " + therapeutiqueId);
        } else {
            System.err.println("Erreur : Aucun programme trouvé avec l'ID " + therapeutiqueId);
        }
    }

    /**
     * Met à jour un programme thérapeutique existant.
     *
     * @param prog le programme thérapeutique avec les nouvelles valeurs
     * @return le programme thérapeutique mis à jour
     */
    @Override
    public ProgrammeTherapeutique updateProgrammeTherapeutique(ProgrammeTherapeutique prog) {
        ProgrammeTherapeutique progDB = programmeTherapeutiqueRepository.findById(prog.getTherapeuticId()).get();

        if (prog.getNom() != null && !"".equalsIgnoreCase(prog.getNom())) {
            progDB.setNom(prog.getNom());
        }

        if (prog.getActivites() != null) {
            progDB.setActivites(prog.getActivites());
        }

        programmeTherapeutiqueRepository.save(progDB);
        return programmeTherapeutiqueRepository.findById(prog.getTherapeuticId()).get();
    }

    /**
     * Met à jour le nom d'un programme thérapeutique.
     *
     * @param prog le programme thérapeutique avec le nouveau nom
     * @return le programme thérapeutique mis à jour
     */
    @Override
    public ProgrammeTherapeutique updateProgrammeTherapeutiqueName(ProgrammeTherapeutique prog) {
        ProgrammeTherapeutique progDB = programmeTherapeutiqueRepository.findById(prog.getTherapeuticId()).get();

        if (prog.getNom() != null && !"".equalsIgnoreCase(prog.getNom())) {
            progDB.setNom(prog.getNom());
        }

        if (prog.getActivites() != null) {
            progDB.setActivites(prog.getActivites());
        }

        programmeTherapeutiqueRepository.save(progDB);
        return programmeTherapeutiqueRepository.findById(prog.getTherapeuticId()).get();
    }

    /**
     * Récupère un programme thérapeutique par son nom, sans tenir compte de la casse.
     *
     * @param nom le nom du programme thérapeutique
     * @return le programme thérapeutique trouvé
     */
    @Override
    public ProgrammeTherapeutique fetchProgrammeTherapeutiqueByNom(String nom) {
        return programmeTherapeutiqueRepository.findByNomIgnoreCase(nom);
    }

    /**
     * Récupère les programmes thérapeutiques associés à un utilisateur spécifique.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return la liste des programmes thérapeutiques associés à l'utilisateur
     */
    @Override
    public List<ProgrammeTherapeutique> fetchProgrammeTherapeutiqueByutilisateurId(Long userId) {
        return programmeTherapeutiqueRepository.findAllByUtilisateurUserId(userId);
    }

    /**
     * Ajoute une activité à un programme thérapeutique.
     *
     * @param progId l'identifiant du programme thérapeutique
     * @param activityId l'identifiant de l'activité
     * @return le nombre de relations ajoutées
     */
    @Transactional
    @Override
    public Integer ajoutActiviteToProgrammeTherapeutique(Long progId, Long activityId) {
        return programmeTherapeutiqueRepository.ajoutRelationActiviteAndProgrammeTherapeutique(progId, activityId);
    }

    /**
     * Vérifie si une activité est liée à un programme thérapeutique.
     *
     * @param progId l'identifiant du programme thérapeutique
     * @param activiteId l'identifiant de l'activité
     * @return true si l'activité est liée au programme, sinon false
     */
    @Override
    public boolean isActiviteRelatedToProgramme(Long progId, Long activiteId) {
        ProgrammeTherapeutique programme = programmeTherapeutiqueRepository.findById(progId)
            .orElseThrow(() -> new RuntimeException("Programme introuvable avec l'ID : " + progId));

        return programme.getActivites().stream()
            .anyMatch(activite -> activite.getActivityId().equals(activiteId));
    }

    /**
     * Récupère la note moyenne d'un programme thérapeutique pour un utilisateur spécifique.
     *
     * @param progId l'identifiant du programme thérapeutique
     * @param userId l'identifiant de l'utilisateur
     * @return la note moyenne du programme thérapeutique pour l'utilisateur
     */
    @Override
    public Double fetchProgrammeTherapeutiqueNote(Long progId, Long userId) {
    	Double note = programmeTherapeutiqueRepository.recupAvgNoteProgrammeTherapeutique(progId, userId);
    	if(note == null) {
    		return null;
    	} else {
            return (Math.round(programmeTherapeutiqueRepository.recupAvgNoteProgrammeTherapeutique(progId, userId) * 2.0)) / 2.0;
    	}
    }
}
