package com.masca.masca_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masca.masca_back.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
