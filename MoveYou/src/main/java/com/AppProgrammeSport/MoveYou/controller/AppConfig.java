/**
 * @author Forest Jules
 * @author Haton Tom
 */
package com.AppProgrammeSport.MoveYou.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration de l'application pour définir des beans.
 */
@Configuration
public class AppConfig {

    /**
     * Définit le bean RestTemplate pour effectuer des appels REST.
     *
     * @return une instance de RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
