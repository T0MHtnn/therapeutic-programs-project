/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AppProgrammeSport.MoveYou.entity.CustomUserDetails;
import com.AppProgrammeSport.MoveYou.entity.Utilisateur;
import com.AppProgrammeSport.MoveYou.repository.UtilisateurRepository;

/**
 * Implémentation du service pour l'entité Utilisateur.
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService, org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    @Lazy // Permet d'éviter la dépendance circulaire (éviter que UtilisateurServiceImpl et SecurityConfig s'appellent mutuellement)
    private PasswordEncoder passwordEncoder; // Le PasswordEncoder

    /**
     * Charge un utilisateur par son nom d'utilisateur (email).
     *
     * @param email l'email de l'utilisateur
     * @return les détails de l'utilisateur
     * @throws UsernameNotFoundException si l'utilisateur n'est pas trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmailIgnoreCase(email);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email);
        }

        // Si tu as des rôles ou des permissions dans ta base, tu peux les ajouter ici
        // Par exemple : new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        return new CustomUserDetails(utilisateur);
    }

    /**
     * Enregistre un utilisateur.
     *
     * @param utilisateur l'utilisateur à enregistrer
     * @return l'utilisateur enregistré
     */
    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        // String encodedPassword = passwordEncoder.encode(utilisateur.getMdp());  // Encode le mot de passe
        // utilisateur.setMdp(encodedPassword);
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Enregistre une liste d'utilisateurs.
     *
     * @param utilisateurs la liste d'utilisateurs à enregistrer
     * @return la liste des utilisateurs enregistrés
     */
    @Override
    public List<Utilisateur> saveMultipleUtilisateurs(List<Utilisateur> utilisateurs) {
        return utilisateurRepository.saveAll(utilisateurs);
    }

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return la liste des utilisateurs
     */
    @Override
    public List<Utilisateur> fetchUtilisateurList() {
        return utilisateurRepository.findAll();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param utilisateurId l'identifiant de l'utilisateur
     * @return l'utilisateur trouvé
     */
    @Override
    public Utilisateur fetchUtilisateurById(Long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId).get();
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param utilisateurId l'identifiant de l'utilisateur à supprimer
     */
    @Override
    public void deleteUtilisateurById(Long utilisateurId) {
        utilisateurRepository.deleteById(utilisateurId);
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param utilisateurId l'identifiant de l'utilisateur à mettre à jour
     * @param userDetails les nouveaux détails de l'utilisateur
     * @return l'utilisateur mis à jour
     */
    @Override
    public Utilisateur updateUtilisateur(Long utilisateurId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Utilisateur userDB = utilisateurRepository.findById(utilisateurId).get();
        userDB.setEmail(userDetails.getUsername());
        userDB.setMdp(userDetails.getPassword());
        userDB.setGenre(userDetails.getGenre());
        userDB.setPathologie(userDetails.getPathologie());
        userDB.setNom(userDetails.getNom());
        userDB.setPrenom(userDetails.getPrenom());
        userDB.setAge(userDetails.getAge());
        return utilisateurRepository.save(userDB);
    }

    /**
     * Récupère un utilisateur par son email.
     *
     * @param utilisateurEmail l'email de l'utilisateur
     * @return l'utilisateur trouvé
     */
    @Override
    public Utilisateur fetchUtilisateurByEmail(String utilisateurEmail) {
        return utilisateurRepository.findByEmailIgnoreCase(utilisateurEmail);
    }

    /**
     * Récupère le mot de passe d'un utilisateur par son email.
     *
     * @param utilisateurEmail l'email de l'utilisateur
     * @return le mot de passe de l'utilisateur, ou null si non trouvé
     */
    @Override
    public String fetchMdpByEmail(String utilisateurEmail) {
        Utilisateur utilisateur = utilisateurRepository.findByEmailIgnoreCase(utilisateurEmail);
        if (utilisateur != null) {
            return utilisateur.getMdp();
        }
        return null;
    }
}
