package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Direccion;
import com.masca.masca_back.repository.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    public Direccion findById(Integer id) {
        return direccionRepository.findById(id).orElse(null);
    }

    public Direccion save(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    public Direccion partialUpdate(Direccion direccion) {
        Direccion existing = direccionRepository.findById(direccion.getId()).orElse(null);
        if (existing != null) {
            if (direccion.getCalle() != null) {
                existing.setCalle(direccion.getCalle());
            }
            if (direccion.getNumero() != null) {
                existing.setNumero(direccion.getNumero());
            }
            if (direccion.getComuna() != null) {
                existing.setComuna(direccion.getComuna());
            }
            if (direccion.getUsuario() != null) {
                existing.setUsuario(direccion.getUsuario());
            }

            return direccionRepository.save(existing);
        }

        return null;
    }

    public void deleteById(Integer id) {
        direccionRepository.deleteById(id);
    }

    public List<Direccion> findByRegionId(Integer regionId) {
        return direccionRepository.findByRegionId(regionId);
    }
}
