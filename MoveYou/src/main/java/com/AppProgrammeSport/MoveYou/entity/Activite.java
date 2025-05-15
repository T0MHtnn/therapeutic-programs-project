package com.AppProgrammeSport.MoveYou.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Activite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long activityId;

	@Length(min=1, max = 50)
	private String name;

	@Length(min=1)
	private String description;

	@Length(min=8, max = 255)
	private String discipline;

	@Length(min=1, max = 255)
	private String pathologies;

    @ManyToMany(mappedBy = "activites")
    @JsonIgnore
    private Set<ProgrammeTherapeutique> programmesTherapeutiques = new HashSet<>();

    @OneToMany(mappedBy = "activite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluation> evaluations;


	// Constructeurs
	public Activite(){}

	public Activite(Long activityId, @Length(min = 1, max = 50) String name,
			@Length(min = 1, max = 50) String description, @Length(min = 8, max = 255) String discipline,
			@Length(min = 1, max = 255) String pathologies, Set<ProgrammeTherapeutique> programmesTherapeutiques,
			List<Evaluation> evaluations) {
		super();
		this.activityId = activityId;
		this.name = name;
		this.description = description;
		this.discipline = discipline;
		this.pathologies = pathologies;
		this.programmesTherapeutiques = programmesTherapeutiques;
		this.evaluations = evaluations;
	}


	// Getters et setters
	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getPathologies() {
		return pathologies;
	}

	public void setPathologies(String pathologies) {
		this.pathologies = pathologies;
	}

	public Set<ProgrammeTherapeutique> getProgrammesTherapeutiques() {
		return programmesTherapeutiques;
	}

	public void setProgrammesTherapeutiques(Set<ProgrammeTherapeutique> programmesTherapeutiques) {
		this.programmesTherapeutiques = programmesTherapeutiques;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	// Fonctions usuelles
	@Override
	public String toString() {
		return "activityId [activityId=" + activityId + ", name=" + name + ", description=" + description + ", discipline=" + discipline + ", pathologies="
				+ pathologies + "]";
	}


}
