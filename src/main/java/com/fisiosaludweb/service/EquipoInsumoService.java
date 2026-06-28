package com.fisiosaludweb.service;

import com.fisiosaludweb.entity.EquipoInsumo;
import com.fisiosaludweb.repository.EquipoInsumoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipoInsumoService {
    private final EquipoInsumoRepository repo;
    public EquipoInsumoService(EquipoInsumoRepository repo) { this.repo = repo; }

    public List<EquipoInsumo> listarActivos() { return repo.findByActivoTrue(); }
    public List<EquipoInsumo> listarDisponibles() { return repo.findByActivoTrueAndCantidadGreaterThan(0); }
    public List<EquipoInsumo> listarTodos() { return repo.findAll(); }
    public EquipoInsumo findById(Long id) { return repo.findById(id).orElseThrow(); }
    public EquipoInsumo guardar(EquipoInsumo e) { return repo.save(e); }
    public void darBaja(Long id) {
        EquipoInsumo e = findById(id);
        e.setActivo(false);
        repo.save(e);
    }
}
