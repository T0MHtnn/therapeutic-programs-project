//package com.AppProgrammeSport.MoveYou.entity;
//
//import jakarta.persistence.EmbeddedId;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.MapsId;
//import jakarta.persistence.Table;
//
//
//@Entity
//@Table(name = "seCompose")
//public class SeCompose {
//
//    @EmbeddedId
//    private SeComposeId id;
//
//    @ManyToOne
//    @MapsId("therapeuticId")
//    @JoinColumn(name = "therapeuticId")
//    private ProgrammeTherapeutique programmeTherapeutique;
//
//    @ManyToOne
//    @MapsId("activityId")
//    @JoinColumn(name = "activityId")
//    private Activite activite;
//
//	// Constructeurs
//	public SeCompose() {}
//	
//    public SeCompose(SeComposeId id, ProgrammeTherapeutique programmeTherapeutique, Activite activite) {
//		super();
//		this.id = id;
//		this.programmeTherapeutique = programmeTherapeutique;
//		this.activite = activite;
//	}
//
//	// Getters and Setters
//    
//	public SeComposeId getId() {
//		return id;
//	}
//	
//	public void setId(SeComposeId id) {
//		this.id = id;
//	}
//
//	public ProgrammeTherapeutique getProgrammeTherapeutique() {
//		return programmeTherapeutique;
//	}
//
//	public void setProgrammeTherapeutique(ProgrammeTherapeutique programmeTherapeutique) {
//		this.programmeTherapeutique = programmeTherapeutique;
//	}
//
//	public Activite getActivite() {
//		return activite;
//	}
//
//	public void setActivite(Activite activite) {
//		this.activite = activite;
//	}
//    
//}
