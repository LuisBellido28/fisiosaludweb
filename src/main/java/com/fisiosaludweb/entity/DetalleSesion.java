package com.fisiosaludweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_sesion")
public class DetalleSesion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "sesion_id")
    private SesionTerapia sesion;
    @ManyToOne(optional = false)
    @JoinColumn(name = "equipo_insumo_id")
    private EquipoInsumo equipoInsumo;
    private int cantidadUsada;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public SesionTerapia getSesion() { return sesion; } public void setSesion(SesionTerapia v) { this.sesion = v; }
    public EquipoInsumo getEquipoInsumo() { return equipoInsumo; } public void setEquipoInsumo(EquipoInsumo v) { this.equipoInsumo = v; }
    public int getCantidadUsada() { return cantidadUsada; } public void setCantidadUsada(int v) { this.cantidadUsada = v; }
}
