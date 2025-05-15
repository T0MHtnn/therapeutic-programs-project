/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AppProgrammeSport.MoveYou.entity.Utilisateur;

/**
 * Cette interface définit toutes les méthodes d'échange concrètes avec la base de données
 * pour l'entité Utilisateur.
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     * Recherche un utilisateur par son adresse email.
     *
     * @param email l'adresse email de l'utilisateur
     * @return l'utilisateur trouvé
     */
    public Utilisateur findByEmail(String email);

    /**
     * Recherche un utilisateur par son adresse email sans tenir compte de la casse.
     *
     * @param email l'adresse email de l'utilisateur
     * @return l'utilisateur trouvé
     */
    public Utilisateur findByEmailIgnoreCase(String email);

	//Exemple utilisation :
	//@Query : utilise les noms d'entités et d'attributs Java (ici utilisateur et mdp)
	//nativeQuery : Utilise les noms réels des tables et colonnes SQL (utilisateur et mdp).
	/*
	@Query(value="SELECT mdp FROM utilisateur WHERE user_id = :user_id", nativeQuery = true)
	public String findMdpById(@Param("user_id") Long utilisateurId);//@Param("id") : fait ref au :user_id
	*/
}

