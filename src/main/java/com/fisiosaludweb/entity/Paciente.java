package com.fisiosaludweb.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true, length = 8)
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String correo;
    @Column(length = 500)
    private String diagnosticoMedico;
    private boolean activo = true;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String v) { this.nombre = v; }
    public String getApellido() { return apellido; } public void setApellido(String v) { this.apellido = v; }
    public String getDni() { return dni; } public void setDni(String v) { this.dni = v; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; } public void setFechaNacimiento(LocalDate v) { this.fechaNacimiento = v; }
    public String getTelefono() { return telefono; } public void setTelefono(String v) { this.telefono = v; }
    public String getCorreo() { return correo; } public void setCorreo(String v) { this.correo = v; }
    public String getDiagnosticoMedico() { return diagnosticoMedico; } public void setDiagnosticoMedico(String v) { this.diagnosticoMedico = v; }
    public boolean isActivo() { return activo; } public void setActivo(boolean v) { this.activo = v; }
    public String getNombreCompleto() { return nombre + " " + apellido; }
}
