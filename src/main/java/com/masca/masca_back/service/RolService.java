package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Rol;
import com.masca.masca_back.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol partialUpdate(Rol rol) {
        Rol existing = rolRepository.findById(rol.getId()).orElse(null);
        if (existing != null) {
            if (rol.getNombre() != null) {
                existing.setNombre(rol.getNombre());
            }
            return rolRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        rolRepository.deleteById(id);
    }
}
