package com.masca.masca_back.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
<<<<<<< HEAD
=======

>>>>>>> 77e7eb7cc7ac3e412801dae8018a2c0c4140870d
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

<<<<<<< HEAD
    @Column(name = "nombreUsuario", length = 50, nullable = false)
    private String nombre;

    @Column(name = "correoUsuario", length = 50, nullable = false)
    private String correo;

    @Column(name = "contraUsuario", length = 25, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contra;

    @ManyToOne
    @JoinColumn(name = "codigo_rol")
    private Rol rol;
=======
    @Column(name = "Usuario", length = 50, nullable = false)
    private String nombre;

>>>>>>> 77e7eb7cc7ac3e412801dae8018a2c0c4140870d
}
