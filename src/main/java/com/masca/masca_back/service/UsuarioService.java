package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Usuario;
import com.masca.masca_back.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectar el encoder

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        // Encriptar contraseña al crear o editar si viene en el body
        if (usuario.getContra() != null && !usuario.getContra().isBlank()) {
            usuario.setContra(passwordEncoder.encode(usuario.getContra()));
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario partialUpdate(Usuario usuario) {
        Usuario existing = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (existing != null) {
            if (usuario.getNombre() != null) {
                existing.setNombre(usuario.getNombre());
            }
            if (usuario.getCorreo() != null) {
                existing.setCorreo(usuario.getCorreo());
            }

            // Encriptar también en el Patch si se actualiza la contraseña
            if (usuario.getContra() != null && !usuario.getContra().isBlank()) {
                existing.setContra(passwordEncoder.encode(usuario.getContra()));
            }

            if (usuario.getTelefono() != null) {
                existing.setTelefono(usuario.getTelefono());
            }
            if (usuario.getRol() != null) {
                existing.setRol(usuario.getRol());
            }
            if (usuario.getDireccion() != null) {
                existing.setDireccion(usuario.getDireccion());
            }

            return usuarioRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
