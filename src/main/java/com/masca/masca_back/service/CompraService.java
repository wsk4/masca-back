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
    @Autowired
    private PerfumeRepository perfumeRepository;
    @Autowired 
    private DetalleCompraRepository detalleCompraRepository;

    public List<Compra> findAll() {
        return compraRepository.findAll();
    }

    public Compra findById(Integer id) {
        return compraRepository.findById(id).orElse(null);
    }

    public Compra save(Compra compra) {
    Compra savedCompra = compraRepository.save(compra);

    if (compra.getDetalleCompras() != null) {
        for (DetalleCompra detalle : compra.getDetalleCompras()) {
            detalle.setCompra(savedCompra);

            // === COMIENZO DE LA CORRECCIÓN ===
            // 1. Obtener el ID del perfume enviado desde el frontend
            if (detalle.getPerfume() == null || detalle.getPerfume().getId() == null) {
                // Manejar error si falta el ID
                throw new IllegalArgumentException("El ID del perfume es requerido en el detalle de la compra.");
            }
            Long perfumeId = detalle.getPerfume().getId();

            // 2. Cargar la entidad Perfume completa desde la base de datos
            Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new RuntimeException("Perfume no encontrado con ID: " + perfumeId));

            // 3. Establecer la entidad Perfume gestionada por JPA en el detalle
            detalle.setPerfume(perfume);
            // === FIN DE LA CORRECCIÓN ===

            // 4. Guardar el detalle de la compra
            detalleCompraRepository.save(detalle);
        }
    }
    return savedCompra;
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
