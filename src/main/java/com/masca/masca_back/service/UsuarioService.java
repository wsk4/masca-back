package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Usuario;
import com.masca.masca_back.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
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
            if (usuario.getContra() != null) {
                existing.setContra(usuario.getContra());
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
