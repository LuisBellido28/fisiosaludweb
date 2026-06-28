package com.fisiosaludweb.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fisioterapeuta_id")
    private Fisioterapeuta fisioterapeuta;
    private LocalDateTime fechaHora;
    private String tipoTerapia;
    @Column(length = 500)
    private String motivo;
    private String estado = "PENDIENTE"; // PENDIENTE | EN_ATENCION | COMPLETADA | CANCELADA
    private LocalDateTime creadoEn = LocalDateTime.now();

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Paciente getPaciente() { return paciente; } public void setPaciente(Paciente v) { this.paciente = v; }
    public Fisioterapeuta getFisioterapeuta() { return fisioterapeuta; } public void setFisioterapeuta(Fisioterapeuta v) { this.fisioterapeuta = v; }
    public LocalDateTime getFechaHora() { return fechaHora; } public void setFechaHora(LocalDateTime v) { this.fechaHora = v; }
    public String getTipoTerapia() { return tipoTerapia; } public void setTipoTerapia(String v) { this.tipoTerapia = v; }
    public String getMotivo() { return motivo; } public void setMotivo(String v) { this.motivo = v; }
    public String getEstado() { return estado; } public void setEstado(String v) { this.estado = v; }
    public LocalDateTime getCreadoEn() { return creadoEn; } public void setCreadoEn(LocalDateTime v) { this.creadoEn = v; }
}
