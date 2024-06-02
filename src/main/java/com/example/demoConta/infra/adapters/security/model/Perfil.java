package com.example.demoConta.infra.adapters.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Dionízio Inácio
 */
@Getter
@Setter
@AllArgsConstructor
public class Perfil implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}