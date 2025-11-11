package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.EstadoCompra;
import com.masca.masca_back.repository.EstadoCompraRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class EstadoCompraService {

    @Autowired
    private EstadoCompraRepository estadoCompraRepository;

    public List<EstadoCompra> findAll() {
        return estadoCompraRepository.findAll();
    }

    public EstadoCompra findById(Long id) {
        return estadoCompraRepository.findById(id).orElse(null);
    }

    public EstadoCompra save(EstadoCompra estadoCompra) {
        return estadoCompraRepository.save(estadoCompra);
    }

    public EstadoCompra partialUpdate(EstadoCompra estadoCompra) {
        EstadoCompra existing = estadoCompraRepository.findById(estadoCompra.getId()).orElse(null);
        if (existing != null) {
            if (estadoCompra.getNombre() != null) {
                existing.setNombre(estadoCompra.getNombre());
            }
            return estadoCompraRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Long id) {
        estadoCompraRepository.deleteById(id);
    }
}
