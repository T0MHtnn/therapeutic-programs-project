package com.AppProgrammeSport.MoveYou.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotNull;

@Entity
public class Evaluation {

    @EmbeddedId
    private EvaluationId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private Utilisateur utilisateur;

    @ManyToOne
    @MapsId("activityId")
    @JoinColumn(name = "activity_id", insertable = false, updatable = false)
    @JsonIgnore
    private Activite activite;

    @NotNull
    private String note;

    // Constructeurs
	public Evaluation(){}

	public Evaluation(EvaluationId id, Utilisateur utilisateur, Activite activite, @NotNull String note) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.activite = activite;
		this.note = note;
	}
    
    // Getters et Setters
	public EvaluationId getId() {
		return id;
	}

	public void setId(EvaluationId id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	// Fonctions usuelles
	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", utilisateur=" + utilisateur + ", activite=" + activite + ", note=" + note
				+ "]";
	}
}
