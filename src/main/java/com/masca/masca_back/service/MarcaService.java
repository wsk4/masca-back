package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Marca;
import com.masca.masca_back.model.Perfume;
import com.masca.masca_back.repository.MarcaRepository;
import com.masca.masca_back.repository.PerfumeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca findById(Integer id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca partialUpdate(Marca marca) {
        Marca existing = marcaRepository.findById(marca.getId()).orElse(null);
        if (existing != null) {
            if (marca.getNombre() != null) {
                existing.setNombre(marca.getNombre());
            }
            return marcaRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        marcaRepository.deleteById(id);
    }

    // Eliminación que borra primero los perfumes asociados a una marca, luego la marca
    public void deleteByMarcaId(Integer marcaId) {
        List<Perfume> perfumes = perfumeRepository.findAll();
        for (Perfume perfume : perfumes) {
            if (perfume.getMarca() != null && perfume.getMarca().getId().equals(marcaId)) {
                perfumeRepository.deleteById(perfume.getId());
            }
        }
        marcaRepository.deleteById(marcaId);
    }
}
