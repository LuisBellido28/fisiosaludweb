package com.fisiosaludweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fisioterapeutas")
public class Fisioterapeuta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String cmp;
    private String especialidad;
    private String turno;
    private String telefono;
    private boolean activo = true;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String v) { this.nombre = v; }
    public String getApellido() { return apellido; } public void setApellido(String v) { this.apellido = v; }
    public String getCmp() { return cmp; } public void setCmp(String v) { this.cmp = v; }
    public String getEspecialidad() { return especialidad; } public void setEspecialidad(String v) { this.especialidad = v; }
    public String getTurno() { return turno; } public void setTurno(String v) { this.turno = v; }
    public String getTelefono() { return telefono; } public void setTelefono(String v) { this.telefono = v; }
    public boolean isActivo() { return activo; } public void setActivo(boolean v) { this.activo = v; }
    public String getNombreCompleto() { return nombre + " " + apellido; }
}
