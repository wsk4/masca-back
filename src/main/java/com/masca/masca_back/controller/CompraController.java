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

import com.masca.masca_back.model.Compra;
import com.masca.masca_back.service.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.findAll();
        if (compras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable Long id) {
        Compra compra = compraService.findById(id);
        if (compra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(compra);
    }

    @PostMapping
    public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {

        Compra createdCompra = compraService.save(compra);
        return ResponseEntity.status(201).body(createdCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable Long id, @RequestBody Compra compra) {
        compra.setId(id);
        Compra updatedCompra = compraService.save(compra);

        if (updatedCompra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCompra);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Compra> partialUpdateCompra(@PathVariable Long id, @RequestBody Compra compra) {
        Compra existingCompra = compraService.findById(id);
        if (existingCompra == null) {
            return ResponseEntity.notFound().build();
        }

        compra.setId(id);

        Compra updatedCompra = compraService.partialUpdate(compra);

        return ResponseEntity.ok(updatedCompra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable Long id) {
        compraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
