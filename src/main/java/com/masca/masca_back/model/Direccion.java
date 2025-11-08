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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String calle;

    @Column(length = 10)
    private String numero;

    @Column(length = 50)
    private String ciudad;

    @Column(length = 50)
    private String provincia;

    @Column(length = 50)
    private String pais;

    @Column(length = 10)
    private String codigoPostal;
}
