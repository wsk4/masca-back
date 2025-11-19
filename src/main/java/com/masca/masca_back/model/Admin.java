package com.masca.masca_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name= "administrador")
public class Admin {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable= false)
    private String nombre;

    @Column(nullable= false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public Admin(){}
    
    public Admin(String nombre,String email,)
}
