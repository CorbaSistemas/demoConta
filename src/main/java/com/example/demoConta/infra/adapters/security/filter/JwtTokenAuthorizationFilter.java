package com.example.demoConta.infra.adapters.security.filter;

import com.example.demoConta.infra.adapters.security.converter.TokenConverter;
import com.example.demoConta.infra.adapters.security.converter.prop.JwtConfiguration;
import com.example.demoConta.infra.adapters.security.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dionízio Inácio
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    protected final JwtConfiguration jwtConfiguration;
    protected final TokenConverter tokenConverter;

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response, @NonNull final FilterChain filterChain)
        throws ServletException, IOException {

        final var token = recuperarToken(request);

        if (StringUtils.isNotBlank(token)) {
            final boolean valido = tokenConverter.isTokenValido(token);
            if (valido) {
                autenticarCliente(token);
            }
        }

        filterChain.doFilter(request, response);
    }

    public void autenticarCliente(final String token) {

        final var claims = tokenConverter.getClaimsToken(token);

        SecurityContextUtil.setSecurityContext(claims, token);
    }

    private String recuperarToken(final HttpServletRequest request) {
        final String token = request.getHeader(jwtConfiguration.getHeader().getName());

        if (StringUtils.isBlank(token) || !token.startsWith(jwtConfiguration.getHeader().getPrefix())) {
            return null;
        }

        return token.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();
    }
}