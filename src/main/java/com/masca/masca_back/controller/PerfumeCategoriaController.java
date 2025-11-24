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

import com.masca.masca_back.model.PerfumeCategoria;
import com.masca.masca_back.service.PerfumeCategoriaService;

@RestController
@RequestMapping("/api/perfume-categorias")
public class PerfumeCategoriaController {

    @Autowired
    private PerfumeCategoriaService perfumeCategoriaService;

    @GetMapping
    public ResponseEntity<List<PerfumeCategoria>> getAllPerfumeCategorias() {
        List<PerfumeCategoria> relaciones = perfumeCategoriaService.findAll();
        if (relaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(relaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfumeCategoria> getPerfumeCategoriaById(@PathVariable Integer id) {
        PerfumeCategoria relacion = perfumeCategoriaService.findById(id);
        if (relacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relacion);
    }

    @PostMapping
    public ResponseEntity<PerfumeCategoria> createPerfumeCategoria(@RequestBody PerfumeCategoria relacion) {
        PerfumeCategoria createdRelacion = perfumeCategoriaService.save(relacion);
        return ResponseEntity.status(201).body(createdRelacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfumeCategoria> updatePerfumeCategoria(@PathVariable Integer id, @RequestBody PerfumeCategoria relacion) {
        relacion.setId(id);
        PerfumeCategoria updatedRelacion = perfumeCategoriaService.save(relacion);
        if (updatedRelacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRelacion);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PerfumeCategoria> partialUpdatePerfumeCategoria(@PathVariable Integer id, @RequestBody PerfumeCategoria relacion) {
        relacion.setId(id);
        PerfumeCategoria existingRelacion = perfumeCategoriaService.findById(id);
        if (existingRelacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(perfumeCategoriaService.partialUpdate(relacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfumeCategoria(@PathVariable Integer id) {
        perfumeCategoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
