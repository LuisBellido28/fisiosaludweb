package com.fisiosaludweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "especialidades")
public class Especialidad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Especialidad() {}
    public Especialidad(Long id, String nombre) { this.id = id; this.nombre = nombre; }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String v) { this.nombre = v; }
}
