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

import com.masca.masca_back.model.EstadoEnvio;
import com.masca.masca_back.service.EstadoEnvioService;

@RestController
@RequestMapping("/api/estados-envio")
public class EstadoEnvioController {

    @Autowired
    private EstadoEnvioService estadoEnvioService;

    @GetMapping
    public ResponseEntity<List<EstadoEnvio>> getAllEstados() {
        List<EstadoEnvio> estados = estadoEnvioService.findAll();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoEnvio> getEstadoById(@PathVariable Long id) {
        EstadoEnvio estado = estadoEnvioService.findById(id);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<EstadoEnvio> createEstado(@RequestBody EstadoEnvio estado) {
        EstadoEnvio createdEstado = estadoEnvioService.save(estado);
        return ResponseEntity.status(201).body(createdEstado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoEnvio> updateEstado(@PathVariable Long id, @RequestBody EstadoEnvio estado) {
        estado.setId(id);
        EstadoEnvio updatedEstado = estadoEnvioService.save(estado);
        if (updatedEstado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEstado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstadoEnvio> partialUpdateEstado(@PathVariable Long id, @RequestBody EstadoEnvio estado) {
        estado.setId(id);
        EstadoEnvio updatedEstado = estadoEnvioService.partialUpdate(estado);
        if (updatedEstado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEstado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        estadoEnvioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
