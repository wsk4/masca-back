package com.masca.masca_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masca.masca_back.model.EstadoEnvio;

@Repository
public interface EstadoEnvioRepository extends JpaRepository<EstadoEnvio, Long> {
}
