//package com.AppProgrammeSport.MoveYou.entity;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//
//@Embeddable
//public class SeComposeId implements Serializable {
//
//    @Column(name = "therapeuticId")
//    private Long therapeuticId;
//    
//    @Column(name = "activityId")
//    private Long activityId;
//
//    public SeComposeId() {}
//    
//    public SeComposeId(Long therapeuticId, Long activityId) {
//		super();
//		this.therapeuticId = therapeuticId;
//		this.activityId = activityId;
//	}
//
//	// Getters and Setters
//	public Long getTherapeuticId() {
//		return therapeuticId;
//	}
//
//	public void setTherapeuticId(Long therapeuticId) {
//		this.therapeuticId = therapeuticId;
//	}
//
//	public Long getActivityId() {
//		return activityId;
//	}
//
//	public void setActivityId(Long activityId) {
//		this.activityId = activityId;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(activityId, therapeuticId);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		SeComposeId other = (SeComposeId) obj;
//		return Objects.equals(activityId, other.activityId) && Objects.equals(therapeuticId, other.therapeuticId);
//	}
//
//	@Override
//	public String toString() {
//		return "SeComposeId [therapeuticId=" + therapeuticId + ", activityId=" + activityId + "]";
//	}
//	
//}
