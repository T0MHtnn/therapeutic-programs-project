/**
 * @author Forest Jules
 * @author Haton Tom
 */

package com.AppProgrammeSport.MoveYou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AppProgrammeSport.MoveYou.entity.Evaluation;
import com.AppProgrammeSport.MoveYou.entity.EvaluationId;

/**
 * Cette interface définit toutes les méthodes d'échange concrètes avec la base de données pour les évaluations
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, EvaluationId>{

}
