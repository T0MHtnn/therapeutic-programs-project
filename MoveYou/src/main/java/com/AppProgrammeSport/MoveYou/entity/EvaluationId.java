package com.AppProgrammeSport.MoveYou.entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EvaluationId implements Serializable {

    @Column(name = "userId")
    private Long userId;

    @Column(name = "activityId")
    private Long activityId;

    // Constructeurs, getters, setters, equals et hashCode

    public EvaluationId() {}

    public EvaluationId(Long userId, Long activityId) {
        this.userId = userId;
        this.activityId = activityId;
    }

    
    // Getters et setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationId that = (EvaluationId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(activityId, that.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, activityId);
    }
}
