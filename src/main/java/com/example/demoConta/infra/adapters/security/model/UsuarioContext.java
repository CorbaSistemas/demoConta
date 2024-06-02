package com.example.demoConta.infra.adapters.security.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dionízio Inácio
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UsuarioContext implements UserDetails {

    private String nome;
    private String login;
    private String email;
    private String roles;
    private boolean adm;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRolesMontados();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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

    private List<Perfil> getRolesMontados() {

        final ArrayList<Perfil> rolesMontados = new ArrayList<>();

        if (getRoles() == null) {
            rolesMontados.add(new Perfil("ROLE_USER"));
            return rolesMontados;
        }

        for (final String role : getRoles().split(",")) {
            rolesMontados.add(new Perfil("ROLE_" + role.trim().toUpperCase()));
        }

        return rolesMontados;
    }
}
