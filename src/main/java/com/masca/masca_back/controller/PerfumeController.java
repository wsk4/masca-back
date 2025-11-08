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

import com.masca.masca_back.model.Perfume;
import com.masca.masca_back.service.PerfumeService;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    public ResponseEntity<List<Perfume>> getAllPerfumes() {
        List<Perfume> perfumes = perfumeService.findAll();
        if (perfumes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(perfumes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfume> getPerfumeById(@PathVariable Integer id) {
        Perfume perfume = perfumeService.findById(id);
        if (perfume == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(perfume);
    }

    @PostMapping
    public ResponseEntity<Perfume> createPerfume(@RequestBody Perfume perfume) {
        Perfume createdPerfume = perfumeService.save(perfume);
        return ResponseEntity.status(201).body(createdPerfume);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfume> updatePerfume(@PathVariable Integer id, @RequestBody Perfume perfume) {
        perfume.setId(id);
        Perfume updatedPerfume = perfumeService.save(perfume);
        if (updatedPerfume == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPerfume);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Perfume> partialUpdatePerfume(@PathVariable Integer id, @RequestBody Perfume perfume) {
        Perfume existingPerfume = perfumeService.findById(id);
        if (existingPerfume == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(perfumeService.partialUpdate(perfume));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfume(@PathVariable Integer id) {
        perfumeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
