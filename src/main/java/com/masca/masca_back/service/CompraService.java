package com.masca.masca_back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.masca.masca_back.model.Compra;
import com.masca.masca_back.model.DetalleCompra;
import com.masca.masca_back.model.Perfume;
import com.masca.masca_back.repository.CompraRepository;
import com.masca.masca_back.repository.DetalleCompraRepository;
import com.masca.masca_back.repository.PerfumeRepository;

@Service
@Transactional
@SuppressWarnings("null")
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> findAll() {
        return compraRepository.findAll();
    }

    public Compra findById(Integer id) {
        return compraRepository.findById(id).orElse(null);
    }

    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    public Compra partialUpdate(Compra compra) {
        Compra existing = compraRepository.findById(compra.getId()).orElse(null);

        if (existing != null) {

            if (compra.getUsuario() != null && compra.getUsuario().getId() != null) {
                existing.setUsuario(compra.getUsuario());
            }

            if (compra.getEstadoCompra() != null && compra.getEstadoCompra().getId() != null) {
                existing.setEstadoCompra(compra.getEstadoCompra());
            }

            if (compra.getEstadoEnvio() != null && compra.getEstadoEnvio().getId() != null) {
                existing.setEstadoEnvio(compra.getEstadoEnvio());
            }

            if (compra.getFechaCompra() != null) {
                existing.setFechaCompra(compra.getFechaCompra());
            }

            return compraRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        compraRepository.deleteById(id);
    }
}
