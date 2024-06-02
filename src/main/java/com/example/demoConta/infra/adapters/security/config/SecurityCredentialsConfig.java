package com.example.demoConta.infra.adapters.security.config;

import com.example.demoConta.infra.adapters.security.converter.TokenConverter;
import com.example.demoConta.infra.adapters.security.converter.prop.JwtConfiguration;
import com.example.demoConta.infra.adapters.security.filter.JwtTokenAuthorizationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Dionízio Inácio
 */
@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {
    private final TokenConverter tokenConverter;

    public SecurityCredentialsConfig(final JwtConfiguration jwtConfiguration, final TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
            UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
}