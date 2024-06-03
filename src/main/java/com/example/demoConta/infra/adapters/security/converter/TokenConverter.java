package com.example.demoConta.infra.adapters.security.converter;

import com.example.demoConta.infra.adapters.security.converter.prop.JwtConfiguration;
import com.example.demoConta.infra.adapters.security.util.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

/**
 * @author Dionízio Inácio on 28/05/2024
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenConverter {

    private final JwtConfiguration jwtConfiguration;

    public boolean isTokenValido(final String token) {
        try {
            Jwts.parser().setSigningKey(jwtConfiguration.getPrivateKey()).parseClaimsJws(token);
            return true;
        } catch (final Exception e) {
            log.error("{} Token inválido! TOKEN: {}, Erro: {}", Utils.LOG_PREFIX, token, e.getMessage());
            return false;
        }
    }

    public Claims getClaimsToken(@NotBlank final String token) {
        return Jwts.parser()
            .setSigningKey(jwtConfiguration.getPrivateKey())
            .parseClaimsJws(token)
            .getBody();
    }
}