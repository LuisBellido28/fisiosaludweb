package com.fisiosaludweb.service;

import com.fisiosaludweb.entity.Paciente;
import com.fisiosaludweb.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository repo;
    public PacienteService(PacienteRepository repo) { this.repo = repo; }

    public List<Paciente> listarActivos() { return repo.findByActivoTrue(); }
    public List<Paciente> buscar(String q) {
        return repo.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCaseOrDniContaining(q, q, q);
    }
    public Paciente findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Paciente guardar(Paciente p) { return repo.save(p); }
    public void darBaja(Long id) {
        Paciente p = findById(id);
        p.setActivo(false);
        repo.save(p);
    }
    public List<Paciente> listarTodos() { return repo.findAll(); }
}
