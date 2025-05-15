/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppProgrammeSport.MoveYou.entity.Activite;
import com.AppProgrammeSport.MoveYou.entity.Utilisateur;
import com.AppProgrammeSport.MoveYou.repository.ActiviteRepository;

import jakarta.transaction.Transactional;

/**
 * Implémentation du service pour l'entité Activite.
 */
@Service
public class ActiviteServiceImpl implements ActiviteService {

    @Autowired
    private ActiviteRepository ActiviteRepository;

    /**
     * Enregistre une activité.
     *
     * @param activite l'activité à enregistrer
     * @return l'activité enregistrée
     */
    @Override
    public Activite saveActivite(Activite activite) {
        return ActiviteRepository.save(activite);
    }

    /**
     * Enregistre une liste d'activités.
     *
     * @param activites la liste d'activités à enregistrer
     * @return la liste des activités enregistrées
     */
    @Override
    public List<Activite> saveMultipleActivites(List<Activite> activites) {
        return ActiviteRepository.saveAll(activites);
    }

    /**
     * Récupère la liste de toutes les activités.
     *
     * @return la liste des activités
     */
    @Override
    public List<Activite> fetchActiviteList() {
        return ActiviteRepository.findAll();
    }

    /**
     * Récupère une activité par son identifiant.
     *
     * @param activiteId l'identifiant de l'activité
     * @return l'activité trouvée
     */
    @Override
    public Activite fetchActiviteById(Long activiteId) {
        return ActiviteRepository.findById(activiteId).get();
    }

    /**
     * Supprime une activité par son identifiant.
     *
     * @param activiteId l'identifiant de l'activité à supprimer
     */
    @Override
    public void deleteActiviteById(Long activiteId) {
        ActiviteRepository.deleteById(activiteId);
    }

    /**
     * Met à jour une activité existante.
     *
     * @param activiteId l'identifiant de l'activité à mettre à jour
     * @param activite l'activité avec les nouvelles valeurs
     * @return l'activité mise à jour
     */
    @Override
    public Activite updateActivite(Long activiteId, Activite activite) {
        Activite activiDB = ActiviteRepository.findById(activiteId).get();

        if (activite.getName() != null && !"".equalsIgnoreCase(activite.getName())) {
            activiDB.setName(activite.getName());
        }

        if (activite.getDescription() != null && !"".equalsIgnoreCase(activite.getDescription())) {
            activiDB.setDescription(activite.getDescription());
        }

        if (activite.getDiscipline() != null && !"".equalsIgnoreCase(activite.getDiscipline())) {
            activiDB.setDiscipline(activite.getDiscipline());
        }

        if (activite.getPathologies() != null && !"".equalsIgnoreCase(activite.getPathologies())) {
            activiDB.setPathologies(activite.getPathologies());
        }

        if (activite.getProgrammesTherapeutiques() != null) {
            activiDB.setProgrammesTherapeutiques(activite.getProgrammesTherapeutiques());
        }

        return ActiviteRepository.save(activiDB);
    }

    /**
     * Récupère une activité par son nom, sans tenir compte de la casse.
     *
     * @param activiteName le nom de l'activité
     * @return l'activité trouvée
     */
    public Activite fetchActiviteByNameIgnoreCase(String activiteName) {
        return ActiviteRepository.findByNameIgnoreCase(activiteName);
    }

    /**
     * Récupère une activité par pathologies.
     *
     * @param activitePathologies nom des pathologies associées à l'activité
     * @return l'activité trouvée
     */
    @Override
    public Activite fetchActiviteByPathologies(String activitePathologies) {
        return ActiviteRepository.findByPathologies(activitePathologies);
    }

    /**
     * Récupère une activité par son nom.
     *
     * @param activiteName le nom de l'activité
     * @return l'activité trouvée
     */
    @Override
    public Activite fetchActiviteByName(String activiteName) {
        return ActiviteRepository.findByName(activiteName);
    }

    /**
     * Recherche des activités par mot-clé.
     *
     * @param motCle le mot-clé de recherche
     * @return la liste des activités correspondantes
     */
    @Override
    public List<Activite> chercherActivite(String motCle) {
        return ActiviteRepository.chercherActivite(motCle);
    }

    /**
     * Recherche des activités recommandées en fonction des pathologies d'un utilisateur.
     *
     * @param utilisateur l'utilisateur avec ses pathologies
     * @return la liste des activités recommandées
     */
    @Override
    public List<Activite> chercherActiviteRecommande(Utilisateur utilisateur) {
        return ActiviteRepository.chercherActiviteRecommande(utilisateur.getPathologie());
    }

    /**
     * Récupère les activités associées à un programme thérapeutique.
     *
     * @param therapeuticId l'identifiant du programme thérapeutique
     * @return la liste des activités correspondantes
     */
    @Override
    public List<Activite> fetchActiviteByProgrammeTherapeutiqueId(Long therapeuticId) {
        return ActiviteRepository.findByProgrammeTherapeutiqueId(therapeuticId);
    }

    /**
     * Supprime les relations entre une activité et un programme thérapeutique.
     *
     * @param activiteId l'identifiant de l'activité
     * @param therapeuticId l'identifiant du programme thérapeutique
     */
    @Transactional
    @Override
    public void deleteRelationActiviteByProgrammeTherapeutique(Long activiteId, Long therapeuticId) {
        ActiviteRepository.deleteRelationsWithProgrammeTherapeutique(activiteId, therapeuticId);
    }
}
