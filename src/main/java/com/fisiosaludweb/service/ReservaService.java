package com.fisiosaludweb.service;

import com.fisiosaludweb.entity.Reserva;
import com.fisiosaludweb.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository repo;
    public ReservaService(ReservaRepository repo) { this.repo = repo; }

    public List<Reserva> listarPendientes() { return repo.findByEstadoOrderByFechaHoraAsc("PENDIENTE"); }
    public List<Reserva> listarTodas() { return repo.findAll(); }
    public List<Reserva> buscarPorFecha(LocalDate fecha) { return repo.findByFecha(fecha); }
    public List<Reserva> porPaciente(Long pacienteId) { return repo.findByPacienteIdOrderByFechaHoraDesc(pacienteId); }
    public Reserva findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Reserva guardar(Reserva r) { return repo.save(r); }
    public void cancelar(Long id) {
        Reserva r = findById(id);
        r.setEstado("CANCELADA");
        repo.save(r);
    }
}
