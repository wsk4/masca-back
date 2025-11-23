package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Comuna;
import com.masca.masca_back.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public Comuna findById(Integer id) {
        return comunaRepository.findById(id).orElse(null);
    }

    public Comuna save(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna partialUpdate(Comuna comuna) {
        Comuna existing = comunaRepository.findById(comuna.getId()).orElse(null);
        if (existing != null) {
            if (comuna.getNombre() != null) {
                existing.setNombre(comuna.getNombre());
            }
            if (comuna.getCodigoPostal() != null) {
                existing.setCodigoPostal(comuna.getCodigoPostal());
            }
            if (comuna.getRegion() != null) {
                existing.setRegion(comuna.getRegion());
            }
            return comunaRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        comunaRepository.deleteById(id);
    }
}
