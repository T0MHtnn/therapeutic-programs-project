package com.AppProgrammeSport.MoveYou.service;

import java.util.List;

import com.AppProgrammeSport.MoveYou.entity.Evaluation;
import com.AppProgrammeSport.MoveYou.entity.EvaluationId;

public interface EvaluationService {
	public Evaluation saveEvaluation(Evaluation evaluation);
	
	public List<Evaluation> saveMultipleEvaluations(List<Evaluation> evaluations);

	public List<Evaluation> fetchEvaluationList();

	public Evaluation fetchEvaluationById(EvaluationId evaluationId);

	public void deleteEvaluationById(EvaluationId evaluationId);

	public Evaluation updateEvaluation(EvaluationId ActiviteId, Evaluation evaluation);

	public void noterActivite(Long activityId, int rating, Long userId);
}
