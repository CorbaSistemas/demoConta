package com.example.demoConta.infra.adapters.security.util;

import com.example.demoConta.infra.adapters.security.model.UsuarioContext;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author Dionízio Inácio on 28/05/2024
 */
@Slf4j
public class SecurityContextUtil {
    private SecurityContextUtil() {
    }

    @SuppressWarnings("unchecked")
    public static void setSecurityContext(@NotNull final Claims claims, final String token) {
        log.info("{} Definindo contexto de segurança do usuário [{}]", Util.LOG_PREFIX, claims.getSubject());
        try {

            final List<String> authorities = (List<String>) claims.get("authorities");
            final String email = claims.get("email", String.class);
            final String nome = claims.get("nome", String.class);
            final boolean adm = Optional.ofNullable(claims.get("adm", Boolean.class)).orElse(false);

            final var usuarioContext = UsuarioContext.builder()
                .login(claims.getSubject())
                .roles(String.join(",", authorities))
                .email(email)
                .nome(nome)
                .adm(adm)
                .build();

            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                usuarioContext,
                null, usuarioContext.getAuthorities());

            authentication.setDetails(token);

            SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
            SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        } catch (final Exception e) {
            log.error("{} Erro ao definir contexto de segurança", Util.LOG_PREFIX, e);
            SecurityContextHolder.clearContext();
        }
    }
}