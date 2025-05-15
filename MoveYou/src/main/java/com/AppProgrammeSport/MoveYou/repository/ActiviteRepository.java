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

import com.AppProgrammeSport.MoveYou.entity.Activite;

/**
 * Cette interface définit toutes les méthodes d'échange concrètes avec la base de données pour les activités
 */
@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    /**
     * Recherche une activité par son nom.
     *
     * @param name le nom de l'activité
     * @return l'activité trouvée
     */
    public Activite findByName(String name);

    /**
     * Recherche une activité par son nom sans tenir compte de la casse.
     *
     * @param name le nom de l'activité
     * @return l'activité trouvée
     */
    public Activite findByNameIgnoreCase(String name);

    /**
     * Recherche une activité par sa pathologie.
     *
     * @param name le nom de la pathologie associée à l'activité
     * @return l'activité trouvée
     */
    public Activite findByPathologies(String name);

    /**
     * Recherche des activités par un mot-clé présent dans la description, la discipline, le nom ou les pathologies.
     *
     * @param motCle le mot-clé de recherche
     * @return la liste des activités correspondantes
     */
    @Query("SELECT a FROM Activite a WHERE " +
           "a.description LIKE %:motCle% OR " +
           "a.discipline LIKE %:motCle% OR " +
           "a.name LIKE %:motCle% OR " +
           "a.pathologies LIKE %:motCle%")
    public List<Activite> chercherActivite(@Param("motCle") String motCle);

    /**
     * Recherche des activités recommandées en fonction d'une pathologie.
     *
     * @param pathologie la pathologie de recherche
     * @return la liste des activités correspondantes
     */
    @Query("SELECT a FROM Activite a WHERE a.pathologies LIKE %:pathologie%")
    public List<Activite> chercherActiviteRecommande(@Param("pathologie") String pathologie);

    /**
     * Recherche des activités associées à un programme thérapeutique spécifique.
     *
     * @param therapeuticId l'identifiant du programme thérapeutique
     * @return la liste des activités correspondantes
     */
    @Query("SELECT a FROM Activite a JOIN a.programmesTherapeutiques p WHERE p.therapeuticId = :therapeuticId")
    public List<Activite> findByProgrammeTherapeutiqueId(@Param("therapeuticId") Long therapeuticId);

    /**
     * Supprime les relations entre une activité et un programme thérapeutique spécifique.
     *
     * @param activityId l'identifiant de l'activité
     * @param therapeuticId l'identifiant du programme thérapeutique
     */
    @Modifying
    @Query(value = "DELETE FROM se_compose WHERE activity_id = :activityId AND therapeutic_id = :therapeuticId", nativeQuery = true)
    public void deleteRelationsWithProgrammeTherapeutique(@Param("activityId") Long activityId, @Param("therapeuticId") Long therapeuticId);
}
