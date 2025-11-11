package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.DetalleCompra;
import com.masca.masca_back.repository.DetalleCompraRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    public List<DetalleCompra> findAll() {
        return detalleCompraRepository.findAll();
    }

    public DetalleCompra findById(Long id) {
        return detalleCompraRepository.findById(id).orElse(null);
    }

    public DetalleCompra save(DetalleCompra detalle) {
        return detalleCompraRepository.save(detalle);
    }

    public DetalleCompra partialUpdate(DetalleCompra detalle) {
        DetalleCompra existing = detalleCompraRepository.findById(detalle.getId()).orElse(null);
        if (existing != null) {
            if (detalle.getCantidad() != null) {
                existing.setCantidad(detalle.getCantidad());
            }
            if (detalle.getPrecioUnitario() != null) {
                existing.setPrecioUnitario(detalle.getPrecioUnitario());
            }
            return detalleCompraRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Long id) {
        detalleCompraRepository.deleteById(id);
    }
}
