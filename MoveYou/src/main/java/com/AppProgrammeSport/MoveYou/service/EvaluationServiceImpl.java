/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppProgrammeSport.MoveYou.entity.Activite;
import com.AppProgrammeSport.MoveYou.entity.Evaluation;
import com.AppProgrammeSport.MoveYou.entity.EvaluationId;
import com.AppProgrammeSport.MoveYou.entity.Utilisateur;
import com.AppProgrammeSport.MoveYou.repository.ActiviteRepository;
import com.AppProgrammeSport.MoveYou.repository.EvaluationRepository;
import com.AppProgrammeSport.MoveYou.repository.UtilisateurRepository;

/**
 * Implémentation du service pour l'entité Evaluation.
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private ActiviteRepository activiteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Enregistre une évaluation.
     *
     * @param evaluation l'évaluation à enregistrer
     * @return l'évaluation enregistrée
     */
    @Override
    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    /**
     * Enregistre une liste d'évaluations.
     *
     * @param evaluations la liste d'évaluations à enregistrer
     * @return la liste des évaluations enregistrées
     */
    @Override
    public List<Evaluation> saveMultipleEvaluations(List<Evaluation> evaluations) {
        return evaluationRepository.saveAll(evaluations);
    }

    /**
     * Récupère la liste de toutes les évaluations.
     *
     * @return la liste des évaluations
     */
    @Override
    public List<Evaluation> fetchEvaluationList() {
        return evaluationRepository.findAll();
    }

    /**
     * Récupère une évaluation par son identifiant composite.
     *
     * @param evaluationId l'identifiant composite de l'évaluation
     * @return l'évaluation trouvée, ou null si non trouvée
     */
    @Override
    public Evaluation fetchEvaluationById(EvaluationId evaluationId) {
        return evaluationRepository.findById(evaluationId)
                .orElse(null);
    }

    /**
     * Supprime une évaluation par son identifiant composite.
     *
     * @param evaluationId l'identifiant composite de l'évaluation à supprimer
     */
    @Override
    public void deleteEvaluationById(EvaluationId evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }

    /**
     * Met à jour une évaluation existante.
     *
     * @param evaluationId l'identifiant composite de l'évaluation à mettre à jour
     * @param evaluation l'évaluation avec les nouvelles valeurs
     * @return l'évaluation mise à jour
     */
    @Override
    public Evaluation updateEvaluation(EvaluationId evaluationId, Evaluation evaluation) {
        Evaluation evaluationDB = evaluationRepository.findById(evaluationId).get();

        if (evaluation.getNote() != null) {
            evaluationDB.setNote(evaluation.getNote());
        }

        return evaluationRepository.save(evaluationDB);
    }

    /**
     * Note une activité pour un utilisateur donné.
     *
     * @param activityId l'identifiant de l'activité
     * @param rating la note à attribuer
     * @param userId l'identifiant de l'utilisateur
     */
    @Override
    public void noterActivite(Long activityId, int rating, Long userId) {
        EvaluationId evalId = new EvaluationId(userId, activityId);
        Evaluation eval = this.fetchEvaluationById(evalId);

        Activite activite = activiteRepository.findById(activityId)
                .orElseThrow(() -> new IllegalArgumentException("Activité non trouvée pour l'ID : " + activityId));

        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvée pour l'ID : " + userId));

        if (eval != null) {
            eval.setNote(rating + "");
            this.updateEvaluation(evalId, eval);
        } else {
            Evaluation newEval = new Evaluation(evalId, utilisateur, activite, rating + "");
            this.saveEvaluation(newEval);
        }
    }
}
