package com.fisiosaludweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos_insumos")
public class EquipoInsumo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo; // EQUIPO | INSUMO
    private int cantidad;
    private int cantidadMinima;
    private String estado; // DISPONIBLE | MANTENIMIENTO
    private double costoUso;
    private boolean activo = true;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String v) { this.nombre = v; }
    public String getTipo() { return tipo; } public void setTipo(String v) { this.tipo = v; }
    public int getCantidad() { return cantidad; } public void setCantidad(int v) { this.cantidad = v; }
    public int getCantidadMinima() { return cantidadMinima; } public void setCantidadMinima(int v) { this.cantidadMinima = v; }
    public String getEstado() { return estado; } public void setEstado(String v) { this.estado = v; }
    public double getCostoUso() { return costoUso; } public void setCostoUso(double v) { this.costoUso = v; }
    public boolean isActivo() { return activo; } public void setActivo(boolean v) { this.activo = v; }
    public boolean isStockBajo() { return cantidad <= cantidadMinima; }
}
