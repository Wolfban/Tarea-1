package com.project.demo.logic.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.demo.logic.entity.Categoria.Categoria;
import jakarta.persistence.*;

@Table(name = "Producto")
@Entity

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer cantidadEnStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","productos"})
    private Categoria categoria;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(Integer cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }



    public Producto() {
    }
}