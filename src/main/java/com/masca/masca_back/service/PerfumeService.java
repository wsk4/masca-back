package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Perfume;
import com.masca.masca_back.repository.PerfumeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private MarcaService marcaService;

    public List<Perfume> findAll() {
        return perfumeRepository.findAll();
    }

    public Perfume findById(Integer id) {
        return perfumeRepository.findById(id).orElse(null);
    }

    public Perfume save(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    public Perfume partialUpdate(Perfume perfume) {
        Perfume existing = perfumeRepository.findById(perfume.getId()).orElse(null);
        if (existing != null) {
            if (perfume.getNombre() != null) {
                existing.setNombre(perfume.getNombre());
            }
            if (perfume.getDescripcion() != null) {
                existing.setDescripcion(perfume.getDescripcion());
            }
            if (perfume.getPrecio() != null) {
                existing.setPrecio(perfume.getPrecio());
            }
            if (perfume.getStock() != null) {
                existing.setStock(perfume.getStock());
            }
            if (perfume.getMarca() != null) {
                existing.setMarca(perfume.getMarca());
            }
            return perfumeRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        marcaService.deleteByMarcaId(id);
        perfumeRepository.deleteById(id);
    }
}
