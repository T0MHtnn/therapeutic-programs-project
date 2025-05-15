/**
 * Contrôleur principal pour gérer les opérations générales de l'application.
 * Ce contrôleur gère les fonctionnalités de connexion, d'inscription, de déconnexion,
 * ainsi que la gestion des programmes thérapeutiques et des activités.
 *
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppProgrammeSport.MoveYou.entity.Activite;
import com.AppProgrammeSport.MoveYou.entity.CustomUserDetails;
import com.AppProgrammeSport.MoveYou.entity.Evaluation;
import com.AppProgrammeSport.MoveYou.entity.ProgrammeTherapeutique;
import com.AppProgrammeSport.MoveYou.entity.Utilisateur;
import com.AppProgrammeSport.MoveYou.service.ActiviteService;
import com.AppProgrammeSport.MoveYou.service.EvaluationService;
import com.AppProgrammeSport.MoveYou.service.ProgrammeTherapeutiqueService;
import com.AppProgrammeSport.MoveYou.service.UtilisateurService;

@Controller
public class ControllerGeneral {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProgrammeTherapeutiqueService programmeTherapeutiqueService;

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Redirige l'utilisateur vers la page de connexion ou l'index s'il est déjà connecté.
     * Empêche d'aller sur la page de connexion si déja connecté
     *
     * @param userDetails les détails de l'utilisateur connecté
     * @return la redirection vers la page appropriée
     */
    @GetMapping("/")
    public String redirectionSiConnecte(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails != null) {
            return "redirect:/index";
        }
        return "redirect:/connexion";
    }

    /**
     * Affiche la page de connexion.
     *
     * @param model le modèle pour ajouter des attributs
     * @param error le message d'erreur s'il y a lieu
     * @param userDetails les détails de l'utilisateur connecté
     * @return la vue de la page de connexion
     */
    @GetMapping("/connexion")
    public String login(Model model, @RequestParam(value = "error", required = false) String error, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails != null) {
            return "redirect:/index";
        }
        if (error != null && error.equals("true")) {
            model.addAttribute("error", "Identifiants incorrects !");
        }
        return "pageConnexion";
    }

    /**
     * Gère la déconnexion de l'utilisateur et affiche un message de déconnexion réussie.
     *
     * @param model le modèle pour ajouter des attributs
     * @param userDetails les détails de l'utilisateur connecté
     * @return la vue de la page de connexion avec un message de déconnexion réussie
     */
    @GetMapping("/deco")
    public String deconnexion(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("decoReussie", "Déconnexion réussie !");
        return "pageConnexion";
    }

    /**
     * Affiche la page d'inscription.
     *
     * @return la vue de la page d'inscription
     */
    @GetMapping("/inscription")
    public String affichePageInscription() {
        return "pageInscription";
    }

    /**
     * Gère l'inscription d'un nouvel utilisateur.
     *
     * @param emailInsc l'email de l'utilisateur
     * @param passwordInsc le mot de passe de l'utilisateur
     * @param nomInsc le nom de l'utilisateur
     * @param prenomInsc le prénom de l'utilisateur
     * @param ageInsc l'âge de l'utilisateur
     * @param genreInsc le genre de l'utilisateur
     * @param pathologieInsc la pathologie de l'utilisateur
     * @param model le modèle pour ajouter des attributs
     * @return la vue appropriée en fonction du résultat de l'inscription
     */
    @PostMapping("/inscription")
    public String register(@RequestParam String emailInsc, @RequestParam String passwordInsc, @RequestParam String nomInsc, @RequestParam String prenomInsc, @RequestParam String ageInsc, @RequestParam String genreInsc, @RequestParam String pathologieInsc, Model model) {
        if (utilisateurService.fetchUtilisateurByEmail(emailInsc) instanceof Utilisateur) {
            model.addAttribute("EmailAlreadyUsed", "Email déjà inscrit, veuillez vous connecter");
            return "pageInscription";
        }

        Utilisateur user = new Utilisateur();
        user.setEmail(emailInsc);
        String encodedPassword = passwordEncoder.encode(passwordInsc);
        user.setMdp(encodedPassword);
        user.setNom(nomInsc.isEmpty() ? null : nomInsc);
        user.setPrenom(prenomInsc.isEmpty() ? null : prenomInsc);
        user.setAge(ageInsc.isEmpty() ? null : Integer.parseInt(ageInsc));
        user.setGenre(genreInsc.isEmpty() ? null : genreInsc);
        user.setPathologie(pathologieInsc.isEmpty() ? null : pathologieInsc);

        utilisateurService.saveUtilisateur(user);

        if (utilisateurService.fetchUtilisateurByEmail(emailInsc) != null) {
            model.addAttribute("inscriptionReussie", "Inscription réussie");
            return "pageConnexion";
        } else {
            model.addAttribute("inscriptionFail", "Erreur lors de l'inscription");
            return "pageInscription";
        }
    }

    /**
     * Affiche la page d'accueil après connexion.
     *
     * @param model le modèle pour ajouter des attributs
     * @param userDetails les détails de l'utilisateur connecté
     * @return la vue de la page d'accueil
     */
    @GetMapping("/index")
    public String goToIndex(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("reussi", "Bienvenue " + userDetails.getPrenom() + " " + userDetails.getNom());
        userDetails.setUtilisateur(utilisateurService.fetchUtilisateurById(userDetails.getUserId()));
        List<ProgrammeTherapeutique> programmesTherapeutiques = userDetails.getUtilisateur().getProgrammes();
        programmesTherapeutiques.sort(Comparator.comparing(ProgrammeTherapeutique::getNom));
        model.addAttribute("ListeProgrammesTherapeutiques", programmesTherapeutiques);

        String url2 = "http://localhost:4444/activites/recommandations/" + userDetails.getUserId();
        List<Activite> activites = Arrays.asList(restTemplate.getForObject(url2, Activite[].class));
        model.addAttribute("ListeActivitesRecommandées", activites);

        return "index";
    }

    /**
     * Affiche la page de compte de l'utilisateur.
     *
     * @param model le modèle pour ajouter des attributs
     * @param userDetails les détails de l'utilisateur connecté
     * @return la vue de la page de compte
     */
    @GetMapping("/compte")
    public String pageCompte(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("email", userDetails.getUsername());
        model.addAttribute("mdp", userDetails.getPassword());
        model.addAttribute("nom", userDetails.getNom());
        model.addAttribute("prenom", userDetails.getPrenom());
        model.addAttribute("age", userDetails.getAge());
        model.addAttribute("genre", userDetails.getGenre());
        model.addAttribute("pathologie", userDetails.getPathologie());
        return "pageCompte";
    }

    /**
     * Valide les modifications apportées au compte de l'utilisateur.
     *
     * @param modifEmail le nouvel email de l'utilisateur
     * @param modifPassword le nouveau mot de passe de l'utilisateur
     * @param modifNom le nouveau nom de l'utilisateur
     * @param modifPrenom le nouveau prénom de l'utilisateur
     * @param modifAge le nouvel âge de l'utilisateur
     * @param modifGenre le nouveau genre de l'utilisateur
     * @param modifPathologie la nouvelle pathologie de l'utilisateur
     * @param model le modèle pour ajouter des attributs
     * @param userDetails les détails de l'utilisateur connecté
     * @return la vue de la page d'accueil
     */
    @PostMapping("/validModifCompte")
    public String validModifCompte(@RequestParam String modifEmail, @RequestParam String modifPassword, @RequestParam String modifNom, @RequestParam String modifPrenom, @RequestParam String modifAge, @RequestParam String modifGenre, @RequestParam String modifPathologie, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        userDetails.setUsername(modifEmail);
        if (!modifPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(modifPassword);
            userDetails.setPassword(encodedPassword);
        }
        userDetails.setNom(modifNom.isEmpty() ? null : modifNom);
        userDetails.setPrenom(modifPrenom.isEmpty() ? null : modifPrenom);
        userDetails.setAge(modifAge.isEmpty() ? null : Integer.parseInt(modifAge));
        userDetails.setGenre(modifGenre);
        userDetails.setPathologie(modifPathologie.isEmpty() ? null : modifPathologie);
        utilisateurService.updateUtilisateur(userDetails.getUserId(), userDetails);

        return goToIndex(model, userDetails);
    }

    /**
     * Affiche toutes les activités disponibles.
     *
     * @param motCle le mot-clé pour filtrer les activités
     * @param progId l'ID du programme thérapeutique
     * @param model le modèle pour ajouter des attributs
     * @param userDetails les détails de l'utilisateur connecté
     * @return la vue de la page de gestion des activités
     */
    @GetMapping("/pageGestionActivites")
    public String displayAllActivities(@RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="progId", defaultValue="") Long progId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
    	List<Activite> activities = null;
    	if(motCle.isEmpty()) {
    		activities = activiteService.fetchActiviteList();
    	} else {
    		activities = activiteService.chercherActivite(motCle);
    	}
        Map<Activite, Evaluation> dicoActivitesEvaluation = matchActiviteToNoteByUserId(activities, userDetails.getUserId());
        model.addAttribute("dicoActivitesEvaluation", dicoActivitesEvaluation);
        model.addAttribute("progId", progId);
        return "pageGestionActivites";
    }

    /**
     * Affiche la page d'un programme thérapeutique spécifique.
     *
     * @param progId l'ID du programme thérapeutique
     * @param model le modèle pour ajouter des attributs
     * @param userDetails les détails de l'utilisateur connecté
     * @return la vue de la page du programme thérapeutique
     */
    @GetMapping("/pageProgrammeTherapeutique")
    public String goToProgrammeTherapeutique(@RequestParam(name="progId", defaultValue="") Long progId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        ProgrammeTherapeutique programme = programmeTherapeutiqueService.fetchProgrammeTherapeutiqueById(progId);
        List<Activite> activities = activiteService.fetchActiviteByProgrammeTherapeutiqueId(progId);
        Map<Activite, Evaluation> dicoActivitesEvaluation = matchActiviteToNoteByUserId(activities, userDetails.getUserId());
        
        Double note = programmeTherapeutiqueService.fetchProgrammeTherapeutiqueNote(progId, userDetails.getUserId());
        
        model.addAttribute("dicoActivitesEvaluation", dicoActivitesEvaluation);
        model.addAttribute("programme", programme);
        model.addAttribute("noteProgrammeTherapeutique", note);
        return "pageProgrammeTherapeutique";
    }

    /**
     * Modifie le nom d'un programme thérapeutique.
     *
     * @param progId l'ID du programme thérapeutique
     * @param newName le nouveau nom du programme
     * @param redirectAttributes les attributs de redirection
     * @return la redirection vers la page du programme thérapeutique
     */
    @PostMapping("/pageProgrammeTherapeutique/edit")
    public String editProgrammeTherapeutique(@RequestParam(name="progId", defaultValue="") Long progId, @RequestParam(name="nom", defaultValue="") String newName, RedirectAttributes redirectAttributes) {
        ProgrammeTherapeutique newProgramme = programmeTherapeutiqueService.fetchProgrammeTherapeutiqueById(progId);
        newProgramme.setNom(newName);
        programmeTherapeutiqueService.updateProgrammeTherapeutique(newProgramme);
        redirectAttributes.addAttribute("progId", newProgramme.getTherapeuticId());
        return "redirect:/pageProgrammeTherapeutique";
    }

    /**
     * Supprime un programme thérapeutique.
     *
     * @param progId l'ID du programme thérapeutique
     * @param redirectAttributes les attributs de redirection
     * @return la redirection vers la page d'accueil
     */
    @PostMapping("/pageProgrammeTherapeutique/delete")
    public String deleteProgrammeTherapeutique(@RequestParam(name="progId", defaultValue="") Long progId, RedirectAttributes redirectAttributes) {
        programmeTherapeutiqueService.deleteProgrammeTherapeutiqueById(progId);
        redirectAttributes.addAttribute("suppressionReussie", "true");
        return "redirect:/index";
    }

    /**
     * Ajoute une activité à un programme thérapeutique.
     *
     * @param progId l'ID du programme thérapeutique
     * @param activityId l'ID de l'activité
     * @param redirectAttributes les attributs de redirection
     * @return la redirection vers la page d'accueil
     */
    @PostMapping("/index/ajoutActivite")
    public String ajoutActiviteToProgrammeTherapeutique(@RequestParam(name = "progId", defaultValue = "") Long progId, @RequestParam(name = "activityId", defaultValue = "") Long activityId, RedirectAttributes redirectAttributes) {
        if (programmeTherapeutiqueService.isActiviteRelatedToProgramme(progId, activityId)) {
            redirectAttributes.addFlashAttribute("alreadyAdd", "Activité déjà ajoutée au programme thérapeutique");
            redirectAttributes.addFlashAttribute("activityIdAlreadyAdd", activityId);
            redirectAttributes.addAttribute("progId", progId);
        } else {
            programmeTherapeutiqueService.ajoutActiviteToProgrammeTherapeutique(progId, activityId);
        }
        return "redirect:/index";
    }

    /**
     * Supprime une relation entre une activité et un programme thérapeutique.
     *
     * @param progId l'ID du programme thérapeutique
     * @param activityId l'ID de l'activité
     * @param redirectAttributes les attributs de redirection
     * @return la redirection vers la page du programme thérapeutique
     */
    @PostMapping("/activites/deleteByProgrammeTherapeutique")
    public String deleteRelationActivityAndProgrammeTherapeutique(@RequestParam(name="progId", defaultValue="") Long progId, @RequestParam(name="activityId", defaultValue="") Long activityId, RedirectAttributes redirectAttributes) {
        activiteService.deleteRelationActiviteByProgrammeTherapeutique(activityId, progId);
        redirectAttributes.addAttribute("suppressionReussie", "true");
        redirectAttributes.addAttribute("progId", progId);
        return "redirect:/pageProgrammeTherapeutique";
    }

    /**
     * Note une activité.
     *
     * @param progId l'ID du programme thérapeutique
     * @param rating la note attribuée
     * @param activityId l'ID de l'activité
     * @param userDetails les détails de l'utilisateur connecté
     * @param redirectAttributes les attributs de redirection
     * @return la redirection vers la page du programme thérapeutique
     */
    @PostMapping("/activites/noter")
    public String noterActivite(@RequestParam(name="progId", defaultValue="") Long progId, @RequestParam(name="rating", defaultValue="") int rating, @RequestParam(name="activityId", defaultValue="") Long activityId, @AuthenticationPrincipal CustomUserDetails userDetails, RedirectAttributes redirectAttributes) {
    	evaluationService.noterActivite(activityId, rating, userDetails.getUserId());
        redirectAttributes.addAttribute("suppressionReussie", "true");
        redirectAttributes.addAttribute("progId", progId);
        return "redirect:/pageProgrammeTherapeutique";
    }

    /**
     * Ajoute un nouveau programme thérapeutique pour l'utilisateur.
     *
     * @param nomProgramme le nom du programme thérapeutique
     * @param redirectAttributes les attributs de redirection
     * @param userDetails les détails de l'utilisateur connecté
     * @return la redirection vers la page d'accueil
     */
    @PostMapping("/utilisateur/addProgrammeTherapeutique")
    public String ajoutProgrammeTherapeutique(@RequestParam(name = "nomProgramme", defaultValue = "") String nomProgramme, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CustomUserDetails userDetails) {
        ProgrammeTherapeutique newProgramme = new ProgrammeTherapeutique();
        newProgramme.setNom(nomProgramme);
        newProgramme.setUtilisateur(userDetails.getUtilisateur());
        programmeTherapeutiqueService.saveProgrammeTherapeutique(newProgramme);
        return "redirect:/index";
    }

    /**
     * Ajoute une activité à un programme thérapeutique.
     *
     * @param progId l'ID du programme thérapeutique
     * @param activityId l'ID de l'activité
     * @param redirectAttributes les attributs de redirection
     * @return la redirection vers la page du programme thérapeutique
     */
    @PostMapping("/programmeTherapeutique/ajoutActivite")
    public String ajoutActiviteToProgramme(@RequestParam(name = "progId", defaultValue = "") Long progId, @RequestParam(name = "activityId", defaultValue = "") Long activityId, RedirectAttributes redirectAttributes) {
        if (programmeTherapeutiqueService.isActiviteRelatedToProgramme(progId, activityId)) {
            redirectAttributes.addFlashAttribute("alreadyAdd", "Activité déjà ajoutée au programme thérapeutique");
            redirectAttributes.addFlashAttribute("activityIdAlreadyAdd", activityId);
            return "redirect:/pageGestionActivites?progId=" + progId;
        } else {
            programmeTherapeutiqueService.ajoutActiviteToProgrammeTherapeutique(progId, activityId);
        }
        return "redirect:/pageProgrammeTherapeutique?progId=" + progId;
    }

    /**
     * Associe les activités à leurs évaluations pour un utilisateur donné.
     *
     * @param activites la liste des activités
     * @param userId l'ID de l'utilisateur
     * @return un dictionnaire associant chaque activité à son évaluation
     */
    public Map<Activite, Evaluation> matchActiviteToNoteByUserId(List<Activite> activites, Long userId){
        activites.sort(Comparator.comparing(Activite::getName));
        Map<Activite, Evaluation> dico = new LinkedHashMap<>();

        for (Activite activite : activites) {
        	boolean ajout = false;
            for (Evaluation evaluation : activite.getEvaluations()) {
            	System.out.println(evaluation.getId().getUserId());
                if (evaluation.getId().getUserId().equals(userId)) {
                	ajout=true;
                    dico.put(activite, evaluation);
                    break;
                }
            }
            if(ajout==false) {
                dico.put(activite, null);
            }
        }

        return dico;
    }
}
