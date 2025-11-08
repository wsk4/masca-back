package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Categoria;
import com.masca.masca_back.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria partialUpdate(Categoria categoria) {
        Categoria existing = categoriaRepository.findById(categoria.getId()).orElse(null);
        if (existing != null) {
            if (categoria.getNombre() != null) {
                existing.setNombre(categoria.getNombre());
            }
            return categoriaRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
