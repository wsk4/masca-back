package com.masca.masca_back.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreMarca", length = 30, nullable = false)
    private String nombre;

    @Column(name = "descripcionMarca", length = 250, nullable = false)
    private String descripcion;

    @Column(name = "logoMarca", nullable = false)
    private String logo;
}
