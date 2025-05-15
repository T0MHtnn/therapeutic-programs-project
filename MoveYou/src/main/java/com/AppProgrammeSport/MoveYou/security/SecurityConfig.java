/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.AppProgrammeSport.MoveYou.service.UtilisateurServiceImpl;

/**
 * Configuration de la sécurité pour l'application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UtilisateurServiceImpl utilisateurServiceImpl;

    /**
     * Configure l'AuthenticationManager avec le service utilisateur et le codeur de mots de passe.
     *
     * @param http l'objet HttpSecurity
     * @return l'AuthenticationManager configuré
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(utilisateurServiceImpl)
                                   .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    /**
     * Définit le bean PasswordEncoder pour utiliser BCrypt.
     *
     * @return un PasswordEncoder utilisant BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure le filtre de sécurité pour gérer les autorisations et l'authentification.
     *
     * @param http l'objet HttpSecurity
     * @return le SecurityFilterChain configuré
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                .requestMatchers("/index").authenticated()
                .requestMatchers("/connexion", "/inscription", "/stylesPageConnexion.css", "/stylesPageInscription.css").anonymous() // autorisations avant connexion
                .requestMatchers("/programmesTherapeutiques/**").permitAll()
                .requestMatchers("/activites/**").permitAll()
                .requestMatchers("/utilisateurs/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/connexion") // page de connexion
                .loginProcessingUrl("/connexion")  // Correspond à l'action du formulaire
                .defaultSuccessUrl("/index", true)  // Redirige vers la page d'accueil après une connexion réussie
                .failureUrl("/connexion?error=true")  // Redirige vers la page de connexion en cas d'échec
                .permitAll()
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                    .accessDeniedPage("/index")  // Redirection vers la page d'accueil en cas de tentative d'accès à /connexion par un utilisateur connecté
                )
            .rememberMe(rememberMe -> rememberMe
                    .key("uniqueAndSecret") // Clé pour sécuriser les cookies
                    .tokenValiditySeconds(3600) // Durée de 1h (en secondes)
                )
            .logout(logout -> logout
                    .logoutUrl("/logout") // URL pour la déconnexion
                    .logoutSuccessUrl("/deco") // Redirection après déconnexion
                    .invalidateHttpSession(true) // Invalide la session
                    .deleteCookies("JSESSIONID") // Supprime les cookies de session
                    .permitAll()
            );
        return http.build();
    }
}
