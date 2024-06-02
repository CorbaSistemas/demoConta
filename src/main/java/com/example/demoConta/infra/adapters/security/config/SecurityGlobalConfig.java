package com.example.demoConta.infra.adapters.security.config;

import com.example.demoConta.infra.adapters.security.service.UsuarioContextService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Dionízio Inácio
 */

public class SecurityGlobalConfig {

    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioContextService();
    }
}
