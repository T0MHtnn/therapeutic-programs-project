package com.AppProgrammeSport.MoveYou.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@NotNull(message = "Ajoutez un email")
	@Length(min=1, max = 50)
    @Column(unique = true)
	@Email
	private String email;

	@NotNull(message = "Ajoutez un mdp")
	@Length(min=8, max = 255)
	private String mdp;

	@NotNull(message = "Ajoutez un genre")
	@Length(min=1, max = 20)
	private String genre;

	@Length(min=1, max = 255)
	private String pathologie;

	@Length(min=1, max = 50)
	private String nom;

	@Length(min=1, max = 50)
	private String prenom;

	@Column
	private Integer age;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProgrammeTherapeutique> programmes;
    
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluation> evaluations;

	public Utilisateur(){}

	public Utilisateur(Long userId,
			@NotNull(message = "Ajoutez un email") @Length(min = 1, max = 50) @Email String email,
			@NotNull(message = "Ajoutez un mdp") @Length(min = 8, max = 255) String mdp,
			@Length(min = 1, max = 20) String genre, @Length(min = 1, max = 255) String pathologie,
			@Length(min = 1, max = 50) String nom, @Length(min = 1, max = 50) String prenom, Integer age,
			List<ProgrammeTherapeutique> programmes, List<Evaluation> evaluations) {
		super();
		this.userId = userId;
		this.email = email;
		this.mdp = mdp;
		this.genre = genre;
		this.pathologie = pathologie;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.programmes = programmes;
		this.evaluations = evaluations;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPathologie() {
		return pathologie;
	}

	public void setPathologie(String pathologie) {
		this.pathologie = pathologie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<ProgrammeTherapeutique> getProgrammes() {
		return programmes;
	}

	public void setProgrammes(List<ProgrammeTherapeutique> programmes) {
		this.programmes = programmes;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
	

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", email=" + email + ", mdp=" + mdp + ", genre=" + genre + ", pathologie="
				+ pathologie + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + "]";
	}


}
