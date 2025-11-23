package com.masca.masca_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masca.masca_back.model.Region;
import com.masca.masca_back.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public Region partialUpdate(Region region) {
        Region existing = regionRepository.findById(region.getId()).orElse(null);
        if (existing != null) {
            if (region.getNombre() != null) {
                existing.setNombre(region.getNombre());
            }
            return regionRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        regionRepository.deleteById(id);
    }
}