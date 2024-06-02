package com.example.demoConta.infra.adapters.security.service;

import com.example.demoConta.infra.adapters.security.model.UsuarioContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Dionízio Inácio on 28/05/2024
 */

public class UsuarioContextService implements UserDetailsService {

    @Override public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return UsuarioContext.builder()
            .login(username)
            .build();
    }
}
