package com.masca.masca_back.config;

import com.masca.masca_back.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authRequest ->
                authRequest
                    // 1. AUTENTICACIÓN: Permitir login y registro
                    .requestMatchers("/api/auth/**").permitAll()
                    
                    // 2. SWAGGER / OPENAPI: Permitir documentación sin token
                    .requestMatchers(
                        "/doc/**",                // Tu ruta personalizada
                        "/v3/api-docs/**",        // JSON de la API
                        "/swagger-ui/**",         // Recursos estáticos de Swagger
                        "/swagger-ui.html"        // Entrypoint por defecto
                    ).permitAll()
                    
                    // 3. RESTO DE LA API: Requiere Token
                    .anyRequest().authenticated()
            )
            .sessionManagement(sessionManager ->
                sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}