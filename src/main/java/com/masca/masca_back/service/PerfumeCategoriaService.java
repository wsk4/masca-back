package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.PerfumeCategoria;
import com.masca.masca_back.repository.PerfumeCategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class PerfumeCategoriaService {

    @Autowired
    private PerfumeCategoriaRepository perfumeCategoriaRepository;

    public List<PerfumeCategoria> findAll() {
        return perfumeCategoriaRepository.findAll();
    }

    public PerfumeCategoria findById(Integer id) {
        return perfumeCategoriaRepository.findById(id).orElse(null);
    }

    public PerfumeCategoria save(PerfumeCategoria perfumeCategoria) {
        return perfumeCategoriaRepository.save(perfumeCategoria);
    }

    public PerfumeCategoria partialUpdate(PerfumeCategoria perfumeCategoria) {
        PerfumeCategoria existing = perfumeCategoriaRepository.findById(perfumeCategoria.getId()).orElse(null);
        if (existing != null) {
            if (perfumeCategoria.getPerfume() != null) {
                existing.setPerfume(perfumeCategoria.getPerfume());
            }
            if (perfumeCategoria.getCategoria() != null) {
                existing.setCategoria(perfumeCategoria.getCategoria());
            }
            return perfumeCategoriaRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        perfumeCategoriaRepository.deleteById(id);
    }
}
