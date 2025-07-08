package com.techlab.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String categoria;
    private Double precio;
    private Integer cantidadEnStock; 
    private String pathFoto; 
    
    public Articulo(){
        
    }

    public Articulo(Integer id, String nombre, String categoria, Double precio, Integer cantidadEnStock, String pathFoto) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
        this.pathFoto = pathFoto;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
    public String getPathFoto() {
        return pathFoto;
    }
    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }
}