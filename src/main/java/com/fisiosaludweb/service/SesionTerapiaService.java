package com.fisiosaludweb.service;

import com.fisiosaludweb.entity.*;
import com.fisiosaludweb.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class SesionTerapiaService {
    private final SesionTerapiaRepository sesionRepo;
    private final ReservaRepository reservaRepo;
    private final EquipoInsumoRepository equipoRepo;

    public SesionTerapiaService(SesionTerapiaRepository sesionRepo,
                                 ReservaRepository reservaRepo,
                                 EquipoInsumoRepository equipoRepo) {
        this.sesionRepo = sesionRepo;
        this.reservaRepo = reservaRepo;
        this.equipoRepo = equipoRepo;
    }

    @Transactional
    public SesionTerapia registrarSesion(SesionTerapia sesion, List<Long> equipoIds, List<Integer> cantidades) {
        // Descuenta inventario de insumos y guarda detalles
        for (int i = 0; i < equipoIds.size(); i++) {
            EquipoInsumo eq = equipoRepo.findById(equipoIds.get(i)).orElseThrow();
            int cant = cantidades.get(i);
            if (eq.getTipo().equals("INSUMO")) {
                if (eq.getCantidad() < cant) throw new RuntimeException("Stock insuficiente: " + eq.getNombre());
                eq.setCantidad(eq.getCantidad() - cant);
                equipoRepo.save(eq);
            }
            DetalleSesion detalle = new DetalleSesion();
            detalle.setSesion(sesion);
            detalle.setEquipoInsumo(eq);
            detalle.setCantidadUsada(cant);
            sesion.getDetalles().add(detalle);
        }
        SesionTerapia saved = sesionRepo.save(sesion);
        // Marca la reserva como completada
        Reserva reserva = sesion.getReserva();
        reserva.setEstado("COMPLETADA");
        reservaRepo.save(reserva);
        return saved;
    }

    public List<SesionTerapia> historialPorPaciente(Long pacienteId) {
        return sesionRepo.findByReservaPacienteIdOrderByFechaSesionDesc(pacienteId);
    }

    public List<SesionTerapia> porPeriodo(LocalDate inicio, LocalDate fin) {
        return sesionRepo.findByFechaSesionBetweenOrderByFechaSesionAsc(inicio, fin);
    }

    public List<SesionTerapia> listarTodas() { return sesionRepo.findAll(); }
}
