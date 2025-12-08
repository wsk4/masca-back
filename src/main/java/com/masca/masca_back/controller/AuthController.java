package com.masca.masca_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masca.masca_back.config.JwtService;
import com.masca.masca_back.model.Usuario;
import com.masca.masca_back.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService; // Inyectamos el servicio de usuarios

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    // --- Endpoint para LOGIN (Ya lo tenías) ---
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // --- NUEVO Endpoint para REGISTRO (Sign Up) ---
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Usuario usuario) {
        // 1. Guardamos el usuario nuevo (UsuarioService se encarga de encriptar la password)
        usuarioService.save(usuario);

        // 2. Cargamos los detalles del usuario recién creado para el token
        // (Asumiendo que buscas por nombre, ajusta si buscas por correo)
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getNombre());

        // 3. Generamos el token inmediatamente para que quede logueado
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}

// Clases auxiliares (pueden ir en el mismo archivo o separados)
class LoginRequest {

    private String username;
    private String password;

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
