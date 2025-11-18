package com.masca.masca_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masca.masca_back.model.EstadoCompra;
import com.masca.masca_back.service.EstadoCompraService;

@RestController
@RequestMapping("/api/estados-compra")
public class EstadoCompraController {

    @Autowired
    private EstadoCompraService estadoCompraService;

    @GetMapping
    public ResponseEntity<List<EstadoCompra>> getAllEstados() {
        List<EstadoCompra> estados = estadoCompraService.findAll();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoCompra> getEstadoById(@PathVariable Integer id) {
        EstadoCompra estado = estadoCompraService.findById(id);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<EstadoCompra> createEstado(@RequestBody EstadoCompra estado) {
        EstadoCompra createdEstado = estadoCompraService.save(estado);
        return ResponseEntity.status(201).body(createdEstado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoCompra> updateEstado(@PathVariable Integer id, @RequestBody EstadoCompra estado) {
        estado.setId(id);
        EstadoCompra updatedEstado = estadoCompraService.save(estado);
        if (updatedEstado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEstado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstadoCompra> partialUpdateEstado(@PathVariable Integer id, @RequestBody EstadoCompra estado) {
        estado.setId(id);
        EstadoCompra updatedEstado = estadoCompraService.partialUpdate(estado);
        if (updatedEstado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEstado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Integer id) {
        estadoCompraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
