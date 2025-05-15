/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AppProgrammeSport.MoveYou.entity.Evaluation;
import com.AppProgrammeSport.MoveYou.entity.EvaluationId;
import com.AppProgrammeSport.MoveYou.service.EvaluationService;

import jakarta.validation.Valid;

/**
 * Contrôleur REST pour gérer les opérations liées à l'entité Evaluation.
 */
@RestController
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * Crée une nouvelle évaluation à partir d'un fichier JSON.
     *
     * @param evaluation l'évaluation à créer
     * @return l'évaluation créée
     */
    @PostMapping("/evaluations")
    public Evaluation saveEvaluation(@Valid @RequestBody Evaluation evaluation) {
        return evaluationService.saveEvaluation(evaluation);
    }

    /**
     * Crée plusieurs évaluations à partir d'un fichier JSON.
     *
     * @param evaluations la liste d'évaluations à créer
     * @return la liste des évaluations créées
     */
    @PostMapping("/evaluations/multipleEvaluations")
    public List<Evaluation> saveMultipleEvaluations(@RequestBody List<Evaluation> evaluations) {
        return evaluationService.saveMultipleEvaluations(evaluations);
    }

    /**
     * Récupère la liste de toutes les évaluations.
     *
     * @return la liste des évaluations
     */
    @GetMapping("/evaluations")
    public List<Evaluation> fetchEvaluationList() {
        return evaluationService.fetchEvaluationList();
    }

    /**
     * Récupère une évaluation par son identifiant composite.
     *
     * @param evaluationId l'identifiant composite de l'évaluation
     * @return l'évaluation trouvée
     */
    @GetMapping("/evaluations/{id}")
    public Evaluation fetchEvaluationById(@PathVariable("id") EvaluationId evaluationId) {
        return evaluationService.fetchEvaluationById(evaluationId);
    }

    /**
     * Supprime une évaluation par son identifiant composite.
     *
     * @param evaluationId l'identifiant composite de l'évaluation à supprimer
     * @return un message confirmant la suppression de l'évaluation
     */
    @DeleteMapping("/evaluations/{id}")
    public String deleteEvaluationById(@PathVariable("id") EvaluationId evaluationId) {
        evaluationService.deleteEvaluationById(evaluationId);
        return "Evaluation " + evaluationId + " supprimée";
    }

    /**
     * Met à jour une évaluation existante.
     *
     * @param evaluationId l'identifiant composite de l'évaluation à mettre à jour
     * @param evaluation l'évaluation avec les nouvelles valeurs
     * @return l'évaluation mise à jour
     */
    @PutMapping("/evaluations/{id}")
    public Evaluation updateEvaluation(@PathVariable("id") EvaluationId evaluationId, @RequestBody Evaluation evaluation) {
        return evaluationService.updateEvaluation(evaluationId, evaluation);
    }
}
