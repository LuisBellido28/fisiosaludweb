package com.fisiosaludweb.service;

import com.fisiosaludweb.entity.Fisioterapeuta;
import com.fisiosaludweb.repository.FisioterapeutaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FisioterapeutaService {
    private final FisioterapeutaRepository repo;
    public FisioterapeutaService(FisioterapeutaRepository repo) { this.repo = repo; }

    public List<Fisioterapeuta> listarActivos() { return repo.findByActivoTrue(); }
    public List<Fisioterapeuta> listarTodos() { return repo.findAll(); }
    public Fisioterapeuta findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Fisioterapeuta guardar(Fisioterapeuta f) { return repo.save(f); }
    public void darBaja(Long id) {
        Fisioterapeuta f = findById(id);
        f.setActivo(false);
        repo.save(f);
    }
}
