package com.example.demoConta.infra.adapters.security.config;

import com.example.demoConta.infra.adapters.security.converter.prop.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @author Dionízio Inácio
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    protected final JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/actuator/**")
            .permitAll()
            .antMatchers(
                    HttpMethod.OPTIONS,
                    "/",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "**/swagger-resources/**",
                    "/**/swagger-ui.html")
            .permitAll()
            .antMatchers("**/swagger-resources/**",
                    "/**/swagger-ui.html",
                    "/**/swagger-ui/**",
                    "/swagger-resources",
                    "/**/actuator/**",
                    "/**/swagger-resources/**",
                    "/v3/api-docs/**",
                    "/swagger-config/**",
                    "/**/webjars/springfox-swagger-ui/**").permitAll()
            .antMatchers(
                "/**/api/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable()
            .csrf()
            .disable()
            .cors()
            .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
            .and()
            .sessionManagement()
            .sessionCreationPolicy(STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(
                (req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
            .authorizeRequests();
    }

    //Configuracoes de recursos estaticos(js, css, imagens, etc.)
    @Override
    public void configure(final WebSecurity web) {
        web.ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**",
                        "/h2-console/**");
    }
}
