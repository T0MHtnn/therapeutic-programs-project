package com.AppProgrammeSport.MoveYou.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Utilisateur utilisateur;

    public CustomUserDetails(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Ajoutez les rôles ou autorisations si nécessaire
    }

    @Override
    public String getPassword() {
        return utilisateur.getMdp();
    }
    

    public void setPassword(String mdp) {
    	utilisateur.setMdp(mdp);
    }

    @Override
    public String getUsername() {
        return utilisateur.getEmail();
    }
    
    public void setUsername(String mail) {
        utilisateur.setEmail(mail);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNom() {
        return utilisateur.getNom();
    }
    
    public void setNom(String nom) {
        utilisateur.setNom(nom);
    }

    public String getPrenom() {
        return utilisateur.getPrenom();
    }
    
    public void setPrenom(String prenom) {
        utilisateur.setPrenom(prenom);
    }

    public String getEmail() {
        return utilisateur.getEmail();
    }
    
    public void setEmail(String mail) {
        utilisateur.setEmail(mail);
    }
    
    public Integer getAge() {
        return utilisateur.getAge();
    }
    
    public void setAge(Integer age) {
        utilisateur.setAge(age);
    }
    
    public String getGenre() {
        return utilisateur.getGenre();
    }
    
    public void setGenre(String genre) {
        utilisateur.setGenre(genre);
    }
    
    public String getPathologie() {
        return utilisateur.getPathologie();
    }
    
    public void setPathologie(String Pathologie) {
        utilisateur.setPathologie(Pathologie);
    }
    
    public Long getUserId() {
    	return utilisateur.getUserId();
    }
    
    public void setUserId(Long id) {
        utilisateur.setUserId(id);
    }
    
    public Utilisateur getUtilisateur() {
    	return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
    	this.utilisateur = utilisateur;
    }
    
    public String toString() {
        return utilisateur.toString();
    }
}
