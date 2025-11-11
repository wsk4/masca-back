package com.masca.masca_back.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.masca.masca_back.model.DetalleCompra;
import com.masca.masca_back.service.DetalleCompraService;

@RestController
@RequestMapping("/api/detalles-compra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @GetMapping
    public ResponseEntity<List<DetalleCompra>> getAllDetalles() {
        List<DetalleCompra> detalles = detalleCompraService.findAll();
        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompra> getDetalleById(@PathVariable Long id) {
        DetalleCompra detalle = detalleCompraService.findById(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<DetalleCompra> createDetalle(@RequestBody DetalleCompra detalle) {
        DetalleCompra createdDetalle = detalleCompraService.save(detalle);
        return ResponseEntity.status(201).body(createdDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompra> updateDetalle(@PathVariable Long id, @RequestBody DetalleCompra detalle) {
        detalle.setId(id);
        DetalleCompra updatedDetalle = detalleCompraService.save(detalle);
        if (updatedDetalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDetalle);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DetalleCompra> partialUpdateDetalle(@PathVariable Long id, @RequestBody DetalleCompra detalle) {
        detalle.setId(id);
        DetalleCompra updatedDetalle = detalleCompraService.partialUpdate(detalle);
        if (updatedDetalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDetalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id) {
        detalleCompraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
