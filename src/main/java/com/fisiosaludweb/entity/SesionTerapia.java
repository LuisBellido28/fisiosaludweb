package com.fisiosaludweb.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sesiones_terapia")
public class SesionTerapia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
    @Column(length = 1000)
    private String observaciones;
    @Column(length = 1000)
    private String evolucion;
    private double montoCobrado;
    private LocalDate fechaSesion = LocalDate.now();
    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleSesion> detalles = new ArrayList<>();

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Reserva getReserva() { return reserva; } public void setReserva(Reserva v) { this.reserva = v; }
    public String getObservaciones() { return observaciones; } public void setObservaciones(String v) { this.observaciones = v; }
    public String getEvolucion() { return evolucion; } public void setEvolucion(String v) { this.evolucion = v; }
    public double getMontoCobrado() { return montoCobrado; } public void setMontoCobrado(double v) { this.montoCobrado = v; }
    public LocalDate getFechaSesion() { return fechaSesion; } public void setFechaSesion(LocalDate v) { this.fechaSesion = v; }
    public List<DetalleSesion> getDetalles() { return detalles; } public void setDetalles(List<DetalleSesion> v) { this.detalles = v; }
}
