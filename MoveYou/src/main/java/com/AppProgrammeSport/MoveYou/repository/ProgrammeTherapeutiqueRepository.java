/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AppProgrammeSport.MoveYou.entity.ProgrammeTherapeutique;

/**
 * Cette interface définit toutes les méthodes d'échange concrètes avec la base de données
 * pour l'entité ProgrammeTherapeutique.
 */
@Repository
public interface ProgrammeTherapeutiqueRepository extends JpaRepository<ProgrammeTherapeutique, Long> {

    /**
     * Recherche un programme thérapeutique par son nom sans tenir compte de la casse.
     *
     * @param nom le nom du programme thérapeutique
     * @return le programme thérapeutique trouvé
     */
    public ProgrammeTherapeutique findByNomIgnoreCase(String nom);

    /**
     * Recherche tous les programmes thérapeutiques associés à un utilisateur spécifique.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return la liste des programmes thérapeutiques associés à l'utilisateur
     */
    public List<ProgrammeTherapeutique> findAllByUtilisateurUserId(Long userId);

    /**
     * Met à jour le nom d'un programme thérapeutique par son identifiant.
     *
     * @param nom le nouveau nom du programme thérapeutique
     * @param id l'identifiant du programme thérapeutique
     * @return le programme thérapeutique mis à jour
     */
    @Query("UPDATE ProgrammeTherapeutique p SET p.nom = :nom WHERE p.therapeuticId = :id")
    public ProgrammeTherapeutique updateNomProgrammeById(@Param("newName") String nom, @Param("therapeuticId") Long id);

    /**
     * Supprime les relations entre un programme thérapeutique et ses activités.
     *
     * @param id l'identifiant du programme thérapeutique
     */
    @Modifying
    @Query(value = "DELETE FROM se_compose WHERE therapeutic_id = :id", nativeQuery = true)
    public void deleteRelationsWithActivities(@Param("id") Long id);

    /**
     * Supprime un programme thérapeutique par son identifiant.
     *
     * @param id l'identifiant du programme thérapeutique
     */
    @Modifying
    @Override
    @Query(value = "DELETE FROM programme_therapeutique WHERE therapeutic_id = :id", nativeQuery = true)
    public void deleteById(@Param("id") Long id);

    /**
     * Ajoute une relation entre une activité et un programme thérapeutique.
     *
     * @param progId l'identifiant du programme thérapeutique
     * @param activityId l'identifiant de l'activité
     * @return le nombre de relations ajoutées
     */
    @Modifying
    @Query(value = "INSERT INTO se_compose (activity_id, therapeutic_id) VALUES (:activityId, :progId)", nativeQuery = true)
    public Integer ajoutRelationActiviteAndProgrammeTherapeutique(@Param("progId") Long progId, @Param("activityId") Long activityId);

    /**
     * Récupère la note moyenne d'un programme thérapeutique
     *
     * @param progId l'identifiant du programme thérapeutique
     * @param userId l'identifiant de l'utilisateur
     * @return la note moyenne du programme thérapeutique pour l'utilisateur
     */
    @Query(value = "SELECT AVG(note) FROM evaluation " +
            "INNER JOIN Activite ON Activite.activity_id = evaluation.activity_id " +
            "INNER JOIN se_compose ON Activite.activity_id = se_compose.activity_id " +
            "INNER JOIN programme_therapeutique pt ON pt.therapeutic_id = se_compose.therapeutic_id " +
            "WHERE pt.therapeutic_id = :progId AND evaluation.user_id = :userId", nativeQuery = true)
    public Double recupAvgNoteProgrammeTherapeutique(@Param("progId") Long progId, @Param("userId") Long userId);
}
