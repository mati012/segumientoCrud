package com.example.segumiento.model;
import org.springframework.lang.NonNullFields;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="envio")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message ="No se puede ingresar el nombre del Producto vacio")
    @Column(name= "nombreProducto")
    private String nombreProducto;

    @NotBlank(message ="No se puede ingresar el estado vacio")
    @Column(name= "estado")
    private String estado;

    @NotBlank(message="No se puede ingresar la ubicacion actual vacia ")
    @Column(name= "ubicacionActual")
    private String ubicacionActual;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getUbicacionActual() {
        return ubicacionActual;
    }
    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    
}
