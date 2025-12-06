package com.masca.masca_back.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masca.masca_back.model.Usuario;
import com.masca.masca_back.repository.UsuarioRepository;
import com.masca.masca_back.security.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        // 1. Autenticar (Spring valida contra base de datos automáticamente)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContra()));

        // 2. Si pasa la autenticación, buscamos al usuario para obtener sus datos completos
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getCorreo());
        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo()).orElseThrow();

        // 3. Generar token
        String token = jwtService.getToken(userDetails);

        // 4. Responder con Token + Datos del usuario
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", usuario);

        return ResponseEntity.ok(response);
    }
}

// Clase auxiliar para recibir el JSON del frontend
class LoginRequest {

    private String correo;
    private String contra;

    // Getters y Setters necesarios para Jackson
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
