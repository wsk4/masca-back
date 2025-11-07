package com.masca.masca_back.model;






import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreP", length = 50, nullable = false)
    private String nombre;

   

    @Column(name = "imgPerfume", nullable = false)
    private String url;

    @Column(name = "descripcionP", nullable = false)
    private String descripcion;

    @Column
    @JoinColumn(name = "precioP")
    private double  precio;

   
    @ManyToOne
    @JoinColumn(name = "categoriaP", nullable = false )
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "marcaP", nullable = false)
    private Marca marca;
    
    //@Column(name = "StockPerfume")
    //private int stock;
}
