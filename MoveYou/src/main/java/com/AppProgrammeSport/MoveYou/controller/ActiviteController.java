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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AppProgrammeSport.MoveYou.entity.Activite;
import com.AppProgrammeSport.MoveYou.entity.Utilisateur;
import com.AppProgrammeSport.MoveYou.service.ActiviteService;
import com.AppProgrammeSport.MoveYou.service.UtilisateurService;

import jakarta.validation.Valid;

/**
 * Contrôleur REST pour gérer les opérations liées à l'entité Activite.
 */
@RestController
public class ActiviteController {

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private UtilisateurService utilisateurService;

    private final Logger LOGGER = LoggerFactory.getLogger(ActiviteController.class);

    /**
     * Crée une nouvelle activité à partir d'un fichier JSON.
     *
     * @param Activite l'activité à créer
     * @return l'activité créée
     */
    @PostMapping("/activites")
    public Activite saveActivite(@Valid @RequestBody Activite Activite) {
        LOGGER.info("Inside saveActivite");
        return activiteService.saveActivite(Activite);
    }

    /**
     * Crée plusieurs activités à partir d'un fichier JSON.
     *
     * @param Activites la liste d'activités à créer
     * @return la liste des activités créées
     */
    @PostMapping("/activites/multipleActivites")
    public List<Activite> saveMultipleActivites(@RequestBody List<Activite> Activites) {
        return activiteService.saveMultipleActivites(Activites);
    }

    /**
     * Récupère la liste de toutes les activités.
     *
     * @return la liste des activités
     */
    @GetMapping("/activites/all")
    public List<Activite> fetchActiviteList() {
        LOGGER.info("Inside fetchActiviteList of ActiviteController");
        return activiteService.fetchActiviteList();
    }

    /**
     * Recherche des activités par mot-clé.
     *
     * @param motCle le mot-clé de recherche
     * @return la liste des activités correspondantes
     */
    @GetMapping("/activites/withSearch/{motCle}")
    public List<Activite> fetchActiviteListWithSearch(@PathVariable("motCle") String motCle) {
        LOGGER.info("Inside fetchActiviteListWithSearch of ActiviteController");
        return activiteService.chercherActivite(motCle);
    }

    /**
     * Récupère la liste des activités recommandées pour un utilisateur spécifique.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return la liste des activités recommandées
     */
    @GetMapping("/activites/recommandations/{id}")
    public List<Activite> fetchActiviteListForRecommandations(@PathVariable("id") Long userId) {
        Utilisateur utilisateur = utilisateurService.fetchUtilisateurById(userId);
        return activiteService.chercherActiviteRecommande(utilisateur);
    }

    /**
     * Récupère la liste des activités associées à un programme thérapeutique spécifique.
     *
     * @param progId l'identifiant du programme thérapeutique
     * @return la liste des activités correspondantes
     */
    @GetMapping("/activites/activitiesOfProgramme/{id}")
    public List<Activite> fetchActiviteListByProgrammeTherapeutiqueId(@PathVariable("id") Long progId) {
        return activiteService.fetchActiviteByProgrammeTherapeutiqueId(progId);
    }

    /**
     * Récupère une activité par son identifiant.
     *
     * @param ActiviteId l'identifiant de l'activité
     * @return l'activité trouvée
     */
    @GetMapping("/activites/{id}")
    public Activite fetchActiviteById(@PathVariable("id") Long ActiviteId) {
        return activiteService.fetchActiviteById(ActiviteId);
    }

    /**
     * Supprime une activité par son identifiant.
     *
     * @param ActiviteId l'identifiant de l'activité à supprimer
     * @return un message confirmant la suppression de l'activité
     */
    @DeleteMapping("/activites/{id}")
    public String deleteActiviteById(@PathVariable("id") Long ActiviteId) {
        activiteService.deleteActiviteById(ActiviteId);
        return "Activite " + ActiviteId + " delete";
    }

    /**
     * Supprime la relation entre une activité et un programme thérapeutique spécifique.
     *
     * @param programmeTherapeutiqueId l'identifiant du programme thérapeutique
     * @param activityId l'identifiant de l'activité
     */
    @PostMapping("/activites/delete/{activityId}/relations/{programmeTherapeuticId}")
    public void deleteActivityByProgrammeTherapeutique(@PathVariable("programmeTherapeuticId") Long programmeTherapeutiqueId, @PathVariable("activityId") Long activityId) {
        activiteService.deleteRelationActiviteByProgrammeTherapeutique(activityId, programmeTherapeutiqueId);
    }

    /**
     * Met à jour une activité existante.
     *
     * @param ActiviteId l'identifiant de l'activité à mettre à jour
     * @param Activite l'activité avec les nouvelles valeurs
     * @return l'activité mise à jour
     */
    @PutMapping("/activites/{id}")
    public Activite updateActivite(@PathVariable("id") Long ActiviteId, @RequestBody Activite Activite) {
        return activiteService.updateActivite(ActiviteId, Activite);
    }

    /**
     * Récupère une activité par son nom.
     *
     * @param ActiviteName le nom de l'activité
     * @return l'activité trouvée
     */
    @GetMapping("/activites/name/{name}")
    public Activite fetchActiviteByName(@PathVariable("name") String ActiviteName) {
        return activiteService.fetchActiviteByName(ActiviteName);
    }

    /**
     * Récupère une activité par ses pathologies.
     *
     * @param ActivitePathologies les pathologies associées à l'activité
     * @return l'activité trouvée
     */
    @GetMapping("/activites/pathologies/{pathologies}")
    public Activite fetchActiviteByPathologies(@PathVariable("pathologies") String ActivitePathologies) {
        return activiteService.fetchActiviteByPathologies(ActivitePathologies);
    }
}
