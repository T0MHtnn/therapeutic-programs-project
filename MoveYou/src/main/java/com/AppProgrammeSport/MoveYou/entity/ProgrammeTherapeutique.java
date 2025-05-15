package com.AppProgrammeSport.MoveYou.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Évite les problèmes avec Hibernate
public class ProgrammeTherapeutique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long therapeuticId;

	@Length(min=1, max = 50)
	private String nom;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private Utilisateur utilisateur;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
        name = "se_compose",
        joinColumns = @JoinColumn(name = "therapeutic_id"),
        inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activite> activites;


	// Constructeurs
	public ProgrammeTherapeutique(){}

	public ProgrammeTherapeutique(Long therapeuticId, @Length(min = 1, max = 50) String nom, Utilisateur utilisateur,
			List<Activite> activites) {
		super();
		this.therapeuticId = therapeuticId;
		this.nom = nom;
		this.utilisateur = utilisateur;
		this.activites = activites;
	}


	// Getters et setters
	public Long getTherapeuticId() {
		return therapeuticId;
	}

	public void setTherapeuticId(Long therapeuticId) {
		this.therapeuticId = therapeuticId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Activite> getActivites() {
		return activites;
	}

	public void setActivites(List<Activite> activites) {
		this.activites = activites;
	}

	@Override
	public String toString() {
		return "ProgrammeTherapeutique [therapeuticId=" + therapeuticId + ", nom=" + nom + ", utilisateur="
				+ utilisateur + ", activites=" + activites + "]";
	}
}
