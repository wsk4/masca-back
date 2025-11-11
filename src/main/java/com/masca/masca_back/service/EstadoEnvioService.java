package com.masca.masca_back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masca.masca_back.model.EstadoEnvio;
import com.masca.masca_back.repository.EstadoEnvioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class EstadoEnvioService {

    @Autowired
    private EstadoEnvioRepository estadoEnvioRepository;

    public List<EstadoEnvio> findAll() {
        return estadoEnvioRepository.findAll();
    }

    public EstadoEnvio findById(Long id) {
        return estadoEnvioRepository.findById(id).orElse(null);
    }

    public EstadoEnvio save(EstadoEnvio estadoEnvio) {
        return estadoEnvioRepository.save(estadoEnvio);
    }

    public EstadoEnvio partialUpdate(EstadoEnvio estadoEnvio) {
        EstadoEnvio existing = estadoEnvioRepository.findById(estadoEnvio.getId()).orElse(null);
        if (existing != null) {
            if (estadoEnvio.getNombre() != null) {
                existing.setNombre(estadoEnvio.getNombre());
            }
            return estadoEnvioRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Long id) {
        estadoEnvioRepository.deleteById(id);
    }
}
